package dev.unand.app.demokelasb.model.network

import dev.unand.app.demokelasb.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmbdService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String): Call<Response>

}