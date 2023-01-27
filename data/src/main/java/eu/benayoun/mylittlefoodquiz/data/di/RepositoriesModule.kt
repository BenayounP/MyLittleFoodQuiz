package eu.benayoun.mylittlefoodquiz.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mylittlefoodquiz.data.repository.DefaultQuestionsRepository
import eu.benayoun.mylittlefoodquiz.data.repository.QuestionsRepository
import eu.benayoun.mylittlefoodquiz.data.source.network.FoodQuestionsNetworkSource
import eu.benayoun.mylittlefoodquiz.data.source.network.di.FoodQuestionsNetworkSourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class QuestionsRepositoryProvider

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @QuestionsRepositoryProvider
    @Singleton
    @Provides
    internal fun providesQuestionsRepositoryProvider(
        @FoodQuestionsNetworkSourceProvider foodQuestionsNetworkSource: FoodQuestionsNetworkSource,
    ): QuestionsRepository {
        return DefaultQuestionsRepository(
            foodQuestionsNetworkSource,
            externalScope = MainScope(),
            dispatcher = Dispatchers.IO
        )
    }
}