@file:OptIn(ExperimentalMaterial3Api::class)

package dev.unand.app.demokelasb.ui.screen

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.room.Room
import coil3.compose.rememberAsyncImagePainter
import dev.unand.app.demokelasb.MovieViewModel
import dev.unand.app.demokelasb.model.Movie
import dev.unand.app.demokelasb.model.local.AppDatabase

@Composable
fun ListUpcomingScreen(name: String, modifier: Modifier = Modifier, viewModel: MovieViewModel) {
    val context = LocalContext.current;

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("List Upcoming Movies")
                }
            )
        },
        floatingActionButton = {

            FloatingActionButton(onClick = {
                Log.d("ListUpcomingScreen", "FloatingActionButton clicked")
                showNotification(context)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = modifier) {
            MovieList(viewModel = viewModel, modifier = modifier)
        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {


    val photoUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"

    Column {
        Image(
            painter = rememberAsyncImagePainter(photoUrl),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
        Text(text = movie.originalTitle ?: " - ", modifier = modifier)
        Text(text = movie.overview ?: " - ", modifier = modifier)
    }
}


@Composable
fun MovieList(viewModel : MovieViewModel, modifier: Modifier = Modifier) {
    val moviesData = viewModel.moviesData.collectAsState().value

    //Database -------------------
    val db = Room.databaseBuilder(
        LocalContext.current,
        AppDatabase::class.java, "movies.db"
    ).allowMainThreadQueries().build()

    val movieDao = db.movieDao()
    movieDao.deleteAllUpcomingMovies()

    for (movie in moviesData.results ?: emptyList()) {
        if (movie != null) {
            movieDao.insertUpcomingMovies(movie)
        }
    }
    //================================================================

    val listMovie = movieDao.getAllUpcomingMovies();

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize())
    {
        items(count = listMovie.size) { index ->
            val movie = listMovie[index]
            MovieCard(movie, modifier = modifier)
        }
    }
}

@SuppressLint("ServiceCast")
fun showNotification(context: Context){

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createNotificationChannel(
            NotificationChannel("1", "Channel1", NotificationManager.IMPORTANCE_HIGH)
        )
    }

    var builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Judul Notifikasi")
        .setContentText("Isi Notifikasi")
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    val notification: Notification = builder.build()

    notificationManager.notify(1, notification)
}

