package com.hafidrf.lokaloops.models

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.reflect.InvocationTargetException

abstract class Adapter<T, VH : RecyclerView.ViewHolder>(
    private val layoutId: Int,
    private val list: ArrayList<T>,
    private val VHClass: Class<VH>,
    private val TClass: Class<T>
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        val view = LayoutInflater.from(p0.context).inflate(layoutId, p0, false) as ViewGroup
        try {
            val constructor = VHClass.getConstructor(View::class.java)
            return constructor.newInstance(view)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException(e)
        }
    }

    override fun onBindViewHolder(p0: VH, p1: Int) {
        val model = getItem(p1)
        bindView(p0, model, p1)
    }

    fun updateList(list: ArrayList<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): T = list[position]

    protected abstract fun bindView(holder: VH, model: T, position: Int)

    override fun getItemCount(): Int = list.size

    fun addData(data: ArrayList<T>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun lastItemIndex(): Int = list.lastIndex

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

}