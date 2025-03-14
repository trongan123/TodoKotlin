package com.example.todokotlin.data.di

import com.example.todokotlin.data.repositories.TodoRepositoryImpl
import com.example.todokotlin.domain.irepository.ITodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindTodoRepository(
        impl: TodoRepositoryImpl
    ): ITodoRepository
}
