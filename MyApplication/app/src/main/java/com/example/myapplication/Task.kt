package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    val id: String,
    val title: String,
    val description: String = ""
) : Parcelable