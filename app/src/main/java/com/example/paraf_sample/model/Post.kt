package com.example.paraf_sample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "body")
    val body: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}
