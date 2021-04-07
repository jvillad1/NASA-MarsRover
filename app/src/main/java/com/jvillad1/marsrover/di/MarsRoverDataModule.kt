package com.jvillad1.marsrover.di

import com.jvillad1.marsrover.data.RoversRepositoryImpl
import com.jvillad1.marsrover.domain.RoversRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MarsRoverDataModule {

    @Binds
    internal abstract fun bindsRoversRepository(
        roversRepositoryImpl: RoversRepositoryImpl
    ): RoversRepository
}
