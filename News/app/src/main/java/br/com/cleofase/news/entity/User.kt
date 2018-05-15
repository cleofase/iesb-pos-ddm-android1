package br.com.cleofase.news.entity

import android.arch.persistence.room.*

@Entity(tableName = "user")
data class User (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "name") var name: String = "",
        @ColumnInfo(name = "email") var email: String = "",
        @ColumnInfo(name = "password") var password: String = "",
        @ColumnInfo(name = "enrolling") var enrolling: String = "",
        @ColumnInfo(name = "phone") var phone: String = "",
        @ColumnInfo(name = "current") var current: Int = 0
)