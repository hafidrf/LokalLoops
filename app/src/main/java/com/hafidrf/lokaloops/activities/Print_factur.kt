package com.hafidrf.lokaloops.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hafidrf.lokaloops.R
import com.leerybit.escpos.DeviceCallbacks
import com.leerybit.escpos.PosPrinter60mm
import com.leerybit.escpos.Ticket
import com.leerybit.escpos.TicketBuilder
import com.leerybit.escpos.bluetooth.BTService
import com.leerybit.escpos.widgets.TicketPreview
import java.io.IOException
import java.util.*
import com.google.android.gms.analytics.HitBuilders
import com.hafidrf.lokaloops.utils.KeranjangSession
import com.hafidrf.lokaloops.utils.SharedPreference

class Print_factur : AppCompatActivity() {

    private val printer by lazy { PosPrinter60mm(this) }
    private val previewHeader by lazy { findViewById<TicketPreview>(R.id.ticketHeader) }
    private val previewIsi by lazy { findViewById<TicketPreview>(R.id.ticketIsi) }
    private val previewFooter by lazy { findViewById<TicketPreview>(R.id.ticketFooter) }
    private val messageView by lazy { findViewById<TextView>(R.id.tv_message) }
    private val stateView by lazy { findViewById<TextView>(R.id.tv_state) }

    val keranjangSession: KeranjangSession = KeranjangSession(this)
    val sharedPreference: SharedPreference = SharedPreference(this)

    private var ticketNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_factur)

        (application as App).tracker.apply {
            setScreenName("PrintFraktur")
            send(HitBuilders.ScreenViewBuilder().build())
        }

        val btnSearch = findViewById<Button>(R.id.btn_search)

        printer.setCharsetName("UTF-8")
        printer.setDeviceCallbacks(object : DeviceCallbacks {
            override fun onConnected() {
                btnSearch.text = "Disconnect"
            }

            override fun onFailure() {
                Toast.makeText(this@Print_factur, "Connection failed", Toast.LENGTH_SHORT).show()
            }

            override fun onDisconnected() {
                btnSearch.text = "Connect"
            }
        })

        printer.setStateChangedListener { state, msg ->
            when (state) {
                BTService.STATE_NONE -> setState("NONE", R.color.text)
                BTService.STATE_CONNECTED -> setState("CONNECTED", R.color.green)
                BTService.STATE_CONNECTING -> setState("CONNECTING", R.color.blue)
                BTService.STATE_LISTENING -> setState("LISTENING", R.color.amber)
            }

            when (msg.arg2) {
                BTService.MESSAGE_STATE_CHANGE -> setMessage("STATE CHANGED", R.color.text)
                BTService.MESSAGE_READ -> setMessage("READ (${msg.what})", R.color.green)
                BTService.MESSAGE_WRITE -> setMessage("WRITE (${msg.what})", R.color.green)
                BTService.MESSAGE_CONNECTION_LOST -> setMessage("CONNECTION LOST", R.color.red)
                BTService.MESSAGE_UNABLE_TO_CONNECT -> setMessage("UNABLE TO CONNECT", R.color.red)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        printer.onActivityResult(requestCode, resultCode, data)
    }

    fun handleButtonClick(view: View) {
        when (view.id) {
            R.id.btn_search -> if (printer.isConnected) printer.disconnect() else printer.connect()
//            R.id.btn_print_raw -> printRawTicket()
            R.id.btn_print_programmatically -> printTicket()
        }
    }

//    private fun printRawTicket() {
//        try {
//            val date = Date()
//            val ticket: Ticket
//
//            ticket = TicketBuilder(printer)
//                .isCyrillic(true)
//                .raw(this, R.raw.ticket,
//                    DateFormat.format("dd.MM.yyyy", date).toString(),
//                    DateFormat.format("HH:mm", date).toString(),
//                    (++ticketNumber).toString() + ""
//                )
//                .fiscalInt("ticket_no", ticketNumber)
//                .fiscalDouble("gift", 3.0, 2)
//                .fiscalDouble("price", 131.30, 2)
//                .fiscalDouble("out_price", 128.30, 2)
//                .build()
//
//            preview.setTicket(ticket)
//            printer.send(ticket)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }



    private fun printTicket() {
        try {
            val date = Date()
            val ticketHeader: Ticket
            var ticketIsi: Ticket
            val ticketFooter: Ticket

            val listProduk = keranjangSession.getKeranjangFull()!!

            var item = ""
            var price = 0
            var coba = 0
            var tot = 0


            val n = sharedPreference.getValueString("uang_bayar")!!
            val nm_pembeli = sharedPreference.getValueString("nama_pembeli")!!
            val uang_bayar = Integer.parseInt(n.toString())


            ticketHeader = TicketBuilder(printer)
                .isCyrillic(true)
                .header("Lokaloops")
                .divider()
                .text("Date: ${DateFormat.format("dd.MM.yyyy", date)}")
                .text("Time: ${DateFormat.format("HH:mm", date)}")
                .text("Ticket No: ${++ticketNumber}")
                .text("Nama Pelanggan: ${nm_pembeli}")
                .fiscalInt("ticket_no", ticketNumber)
                .dividerDouble()
                .build()
            previewHeader.setTicket(ticketHeader)
            printer.send(ticketHeader)

            for ((index, value) in listProduk.withIndex()) {
                println("the element at $index is $value")
            }

            var har= 0

            listProduk.forEach {
            item = it.item.name.toString()
            tot = it.total!!
                har = it.item.price!!
            price = it.total!! * it.item.price!!
            coba += it.total!! * it.item.price!!

            ticketIsi = TicketBuilder(printer)
                .isCyrillic(true)
                .menuLine("- ${item}(${har}) ", "")
                .menuLine(" x${tot}  ", "Rp ${price}")
                .build()

            previewIsi.setTicket(ticketIsi)
            printer.send(ticketIsi)
            }

            var kembali = uang_bayar - coba
            ticketFooter = TicketBuilder(printer)
                .isCyrillic(true)
                .dividerDouble()
                .menuLine("Total", "Rp ${coba}")
                .menuLine("Tunai", "Rp ${uang_bayar}")
                .menuLine("Kembali", "Rp ${kembali}")
                .dividerDouble()
                .stared("THANK YOU")
                .feedLine(4)
                .build()

            previewFooter.setTicket(ticketFooter)
            printer.send(ticketFooter)

            keranjangSession.clearSharedPreference()
            sharedPreference.clearSharedPreference()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setState(value: String, color: Int) {
        stateView.text = "State: $value"
        stateView.setTextColor(ContextCompat.getColor(this, color))
    }

    @SuppressLint("SetTextI18n")
    private fun setMessage(value: String, color: Int) {
        messageView.text = "State: $value"
        messageView.setTextColor(ContextCompat.getColor(this, color))
    }
}
