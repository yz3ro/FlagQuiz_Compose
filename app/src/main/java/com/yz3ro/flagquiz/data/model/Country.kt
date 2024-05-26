package com.yz3ro.flagquiz.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "flags")
data class Country(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("flag_id") val flag_id: Int,
    @ColumnInfo("flag_name") var flag_name: String,
    @ColumnInfo("flag_url") var flag_url: String,
    @ColumnInfo("flag_region") var flag_region: String,
)