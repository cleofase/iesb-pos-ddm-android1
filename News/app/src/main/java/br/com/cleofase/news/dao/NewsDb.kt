package br.com.cleofase.news.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.cleofase.news.entity.New
import br.com.cleofase.news.entity.User

@Database(entities = [(New::class), (User::class)], version = 1)
abstract class NewsDB: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun newDAO(): NewDAO
}
