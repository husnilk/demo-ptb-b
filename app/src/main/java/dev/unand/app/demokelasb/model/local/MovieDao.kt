package dev.unand.app.demokelasb.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.unand.app.demokelasb.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllUpcomingMovies(): List<Movie>

    @Query("DELETE FROM movies")
    fun deleteAllUpcomingMovies()

    @Insert
    fun insertUpcomingMovies(movie: Movie)

}