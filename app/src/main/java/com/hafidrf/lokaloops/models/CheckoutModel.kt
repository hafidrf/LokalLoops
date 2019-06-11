package com.hafidrf.lokaloops.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CheckoutModel(val name: String, val quantity: String) : Parcelable