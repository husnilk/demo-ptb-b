package dev.unand.app.demokelasb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("dates")
	val dates: Dates? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Movie?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class Dates(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
)

@Entity(tableName = "movies")
data class Movie(

	@ColumnInfo(name = "overview")
	@field:SerializedName("overview")
	var overview: String? = null,

	@ColumnInfo(name = "original_language")
	@field:SerializedName("original_language")
	var originalLanguage: String? = null,

	@ColumnInfo(name = "original_title")
	@field:SerializedName("original_title")
	var originalTitle: String? = null,

	@ColumnInfo(name = "video")
	@field:SerializedName("video")
	var video: Boolean? = null,

	@ColumnInfo(name = "title")
	@field:SerializedName("title")
	var title: String? = null,

	@Ignore
	@field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

	@ColumnInfo(name = "poster_path")
	@field:SerializedName("poster_path")
	var posterPath: String? = null,

	@ColumnInfo(name = "backdrop_path")
	@field:SerializedName("backdrop_path")
	var backdropPath: String? = null,

	@ColumnInfo(name = "release_date")
	@field:SerializedName("release_date")
	var releaseDate: String? = null,

	@ColumnInfo(name = "popularity")
	@field:SerializedName("popularity")
	var popularity: Double? = null,

	@ColumnInfo(name = "vote_average")
	@field:SerializedName("vote_average")
	var voteAverage: Double? = null,

	@PrimaryKey
	@ColumnInfo(name = "id")
	@field:SerializedName("id")
	var id: Int? = null,

	@ColumnInfo(name = "adult")
	@field:SerializedName("adult")
	var adult: Boolean? = null,

	@ColumnInfo(name = "vote_count")
	@field:SerializedName("vote_count")
	var voteCount: Int? = null
)
