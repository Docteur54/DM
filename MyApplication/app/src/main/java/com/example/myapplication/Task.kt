package com.example.myapplication

import com.squareup.moshi.Json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Task(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String
)