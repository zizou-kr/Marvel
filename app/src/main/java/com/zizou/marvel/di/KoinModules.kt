package com.zizou.marvel.di

import android.content.Context
import com.zizou.marvel.BuildConfig
import com.zizou.marvel.R
import com.zizou.marvel.data.database.AppDatabase
import com.zizou.marvel.data.database.FavoriteCharacterDao
import com.zizou.marvel.data.datasource.*
import com.zizou.marvel.data.datasource.device.ImageDeviceDataSourceImpl
import com.zizou.marvel.data.datasource.local.FavoriteCharacterLocalDataSourceImpl
import com.zizou.marvel.data.datasource.remote.*
import com.zizou.marvel.data.network.*
import com.zizou.marvel.data.repository.*
import com.zizou.marvel.domain.repository.*
import com.zizou.marvel.domain.usecase.*
import com.zizou.marvel.presentation.viewmodel.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModules = module {
    viewModel<CharactersViewModel> {
        CharactersViewModel(
            get<GetCharactersAndFavoritesUseCase>(),
            get<AddFavoriteCharacterUseCase>(),
            get<RemoveFavoriteCharacterUseCase>()
        )
    }
    viewModel<FavoriteCharactersViewModel> {
        FavoriteCharactersViewModel(
            get<GetFavoriteCharactersUseCase>(),
            get<RemoveFavoriteCharacterUseCase>()
        )
    }
    viewModel<CharacterEventsViewModel> { CharacterEventsViewModel(get<GetCharacterEventsUseCase>()) }
    viewModel<CharacterStoriesViewModel> { CharacterStoriesViewModel(get<GetCharacterStoriesUseCase>()) }
    viewModel<CharacterComicsViewModel> { CharacterComicsViewModel(get<GetCharacterComicsUseCase>()) }
    viewModel<CharacterSeriesViewModel> { CharacterSeriesViewModel(get<GetCharacterSeriesUseCase>()) }
    viewModel<CharacterItemViewModel> { CharacterItemViewModel(get<DownloadImageUseCase>()) }
    viewModel<EventDetailViewModel> { EventDetailViewModel() }
    viewModel<StoryDetailViewModel> { StoryDetailViewModel() }
    viewModel<SeriesDetailViewModel> { SeriesDetailViewModel() }
    viewModel<ComicDetailViewModel> { ComicDetailViewModel() }
    viewModel<WebsiteViewModel> { WebsiteViewModel() }
}

private val useCaseModules = module {
    single<GetCharactersAndFavoritesUseCase> {
        GetCharactersAndFavoritesUseCase(get<GetCharactersUseCase>(), get<GetFavoriteCharactersUseCase>())
    }
    single<GetCharactersUseCase> { GetCharactersUseCase(get<CharacterRepository>()) }
    single<GetCharacterEventsUseCase> { GetCharacterEventsUseCase(get<EventRepository>()) }
    single<GetCharacterSeriesUseCase> { GetCharacterSeriesUseCase(get<SeriesRepository>()) }
    single<GetCharacterStoriesUseCase> { GetCharacterStoriesUseCase(get<StoryRepository>()) }
    single<GetCharacterComicsUseCase> { GetCharacterComicsUseCase(get<ComicRepository>()) }
    single<GetFavoriteCharactersUseCase> { GetFavoriteCharactersUseCase(get<FavoriteCharacterRepository>()) }
    single<AddFavoriteCharacterUseCase> { AddFavoriteCharacterUseCase(get<FavoriteCharacterRepository>()) }
    single<RemoveFavoriteCharacterUseCase> { RemoveFavoriteCharacterUseCase(get<FavoriteCharacterRepository>()) }
    single<DownloadImageUseCase> { DownloadImageUseCase(get<ImageRepository>()) }
}

private val repositoryModules = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get<CharacterRemoteDataSource>()) }
    single<EventRepository> { EventRepositoryImpl(get<EventRemoteDataSource>()) }
    single<ComicRepository> { ComicRepositoryImpl(get<ComicRemoteDataSource>()) }
    single<StoryRepository> { StoryRepositoryImpl(get<StoryRemoteDataSource>()) }
    single<SeriesRepository> { SeriesRepositoryImpl(get<SeriesRemoteDataSource>()) }
    single<FavoriteCharacterRepository> { FavoriteCharacterRepositoryImpl(get<FavoriteCharacterLocalDataSource>()) }
    single<ImageRepository> { ImageRepositoryImpl(get<ImageDeviceDataSource>()) }
}

private val dataSourceModules = module {
    single<CharacterRemoteDataSource> { CharacterRemoteDataSourceImpl(get<CharacterApi>()) }
    single<EventRemoteDataSource> { EventRemoteDataSourceImpl(get<EventApi>()) }
    single<StoryRemoteDataSource> { StoryRemoteDataSourceImpl(get<StoryApi>()) }
    single<SeriesRemoteDataSource> { SeriesRemoteDataSourceImpl(get<SeriesApi>()) }
    single<ComicRemoteDataSource> { ComicRemoteDataSourceImpl(get<ComicApi>()) }
    single<FavoriteCharacterLocalDataSource> { FavoriteCharacterLocalDataSourceImpl(get<FavoriteCharacterDao>()) }
    single<ImageDeviceDataSource> { ImageDeviceDataSourceImpl(androidContext()) }
}

private val retrofitApiModules = module {
    single<CharacterApi> { get<Retrofit>().create(CharacterApi::class.java) }
    single<ComicApi> { get<Retrofit>().create(ComicApi::class.java) }
    single<StoryApi> { get<Retrofit>().create(StoryApi::class.java) }
    single<SeriesApi> { get<Retrofit>().create(SeriesApi::class.java) }
    single<EventApi> { get<Retrofit>().create(EventApi::class.java) }
}

private val networkModules = module {
    single<OkHttpClient> { getOkhttpClient(androidContext()) }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.marvel_base_url))
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}

private val databaseModule = module {
    single<FavoriteCharacterDao> { get<AppDatabase>().favoriteCharacterDao() }
    single<AppDatabase> { AppDatabase.getInstance(androidContext()) }
}

private fun getOkhttpClient(context: Context): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(NetworkConnectionInterceptor(context))
        .addInterceptor(
            MarvelInterceptor(
                context.getString(R.string.marvel_public_key),
                context.getString(R.string.marvel_private_key)
            )
        )

    if (BuildConfig.DEBUG) builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

    return builder.build()
}

val runTimeModules = listOf(
    databaseModule,
    networkModules,
    dataSourceModules,
    retrofitApiModules,
    repositoryModules,
    useCaseModules,
    viewModelModules
)