package br.com.cleofase.news.dao

import android.arch.persistence.room.*
import br.com.cleofase.news.entity.User

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user")
    fun users(): Array<User>

    @Query("SELECT * FROM user WHERE email LIKE :email")
    fun users(email: String): Array<User>

    @Query("SELECT * FROM user WHERE current == 1")
    fun currentUsers(): Array<User>
}