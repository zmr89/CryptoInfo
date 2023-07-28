package com.example.cryptoinfo.di

import com.example.cryptoinfo.data.worker.ChildWorkerFactory
import com.example.cryptoinfo.data.worker.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindsRefreshDataWorkerFactory(refreshDataWorker: RefreshDataWorker.Factory): ChildWorkerFactory

}