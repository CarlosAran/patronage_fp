package com.patronage.patronage.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
//https://api.chucknorris.io/
interface ChuckNorrisService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): RandomJokeResponse
}

/*
{"categories":[],"created_at":"2020-01-05 13:42:26.447675","icon_url":"https://api.chucknorris.io/img/avatar/chuck-norris.png","id":"R_Xx9Dc_RIuyBkb-ztVW6A","updated_at":"2020-01-05 13:42:26.447675","url":"https://api.chucknorris.io/jokes/R_Xx9Dc_RIuyBkb-ztVW6A","value":"When Chuck Norris goes to a strip club, the strippers pay him before their performances."}
 */
@Serializable
data class RandomJokeResponse(
    val categories: List<String>,
    @SerialName("created_at") val createdAt: String,        //SerialName permite dar el nombre que queramos, pero que lo saque del jsonobject "created_at"
    val value: String,
)