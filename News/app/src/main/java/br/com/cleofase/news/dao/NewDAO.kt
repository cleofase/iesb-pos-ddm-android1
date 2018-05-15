package br.com.cleofase.news.dao

import android.arch.persistence.room.*
import br.com.cleofase.news.entity.New

@Dao
public interface NewDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew(new: New)

    @Update
    fun updateNew(new: New)

    @Query("SELECT * FROM new")
    fun news(): Array<New>

    @Query("SELECT * FROM new WHERE ownerId == :ownerId")
    fun news(ownerId: Int): Array<New>

}