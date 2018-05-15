package br.com.cleofase.news.entity

import android.arch.persistence.room.*

@Entity(tableName = "new")
data class New (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "photoData") var photoData: String = "",
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "description") var description: String = "",
        @ColumnInfo(name = "date") var date: String = "",
        @ColumnInfo(name = "ownerId") var ownerId: Int = 0
)