package br.com.cleofase.loja

import android.arch.persistence.room.*
import java.sql.Blob

@Entity(tableName = "produto")
data class Produto (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "foto") var foto: String = "",
        @ColumnInfo(name = "nome") var nome: String = "",
        @ColumnInfo(name = "descricao") var descricao: String = "",
        @ColumnInfo(name = "preco") var preco: Float = 0.0f,
        @ColumnInfo(name = "naSacola") var naSacola: String = "false"
)

@Entity(tableName = "sacola")
data class Sacola (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "produtos") var produtos: Array<Produto>
)

@Dao
public interface ProdutoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertProduto(prod: Produto)

    @Update
fun updateProduto(prod: Produto)

    @Query("SELECT * FROM produto")
fun produtos(): Array<Produto>

    @Query("SELECT * FROM produto WHERE naSacola == 'true'")
    fun produtosNaSacola(): Array<Produto>

}

@Database(entities = [(Produto::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun romDao(): ProdutoDao
}
