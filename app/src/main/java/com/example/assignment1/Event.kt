package com.example.assignment1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val location: String,
    val date: String,
    val time: String,
    val description: String
) : Parcelable
