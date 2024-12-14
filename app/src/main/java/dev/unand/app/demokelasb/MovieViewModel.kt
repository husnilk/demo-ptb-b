package dev.unand.app.demokelasb

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import dev.unand.app.demokelasb.model.Response
import dev.unand.app.demokelasb.model.local.AppDatabase
import dev.unand.app.demokelasb.model.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class MovieViewModel : ViewModel() {

    private val _moviesData : MutableStateFlow<Response> = MutableStateFlow(Response())
    val moviesData : StateFlow<Response> = _moviesData

    init {
        getNowPlayingMovies("cf51c94af17e64e7a0b2fdf107a3dbc6")
    }

    private fun getNowPlayingMovies(apiKey: String) {

        viewModelScope.launch {


            val call : Call<Response> = RetrofitInstance.apiService.getNowPlayingMovies(apiKey)
            call.enqueue(object : Callback<Response> {

                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                    if (response.isSuccessful) {
                        _moviesData.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}