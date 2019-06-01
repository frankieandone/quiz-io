package one.and.frankie.quizio.di

import dagger.Module
import dagger.Provides
import one.and.frankie.quizio.model.QAsApi
import one.and.frankie.quizio.model.QAsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com"
    }

    @Provides fun provideQAsApi(): QAsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(QAsApi::class.java)

    @Provides fun provideQAService(): QAsService = QAsService()
}