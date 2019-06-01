package one.and.frankie.quizio.model

import io.reactivex.Single
import retrofit2.http.GET

interface QAsApi {
    @GET("frankieandone/quiz-io-api/master/qas.json")
    fun getQAs(): Single<List<QA>>
}