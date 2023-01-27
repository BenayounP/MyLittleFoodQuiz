package eu.benayoun.mylittlefoodquiz.data.source.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mylittlefoodquiz.data.source.network.FakeFromFileFoodQuestionsSource
import eu.benayoun.mylittlefoodquiz.data.source.network.FoodQuestionsNetworkSource
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class FoodQuestionsNetworkSourceProvider


@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {
    @FoodQuestionsNetworkSourceProvider
    @Singleton
    @Provides
    internal fun providesFoodQuestionsNetworkSourceProvider(@ApplicationContext context: Context): FoodQuestionsNetworkSource {
        return FakeFromFileFoodQuestionsSource(context)
    }
}