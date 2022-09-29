package com.codejunior.inventoryapplication.di


import com.codejunior.inventoryapplication.model.db.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.FirestoreImp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideInstanceInject(auth:FirebaseAuth): FirebaseRepository {
        return FirestoreImp(auth)
    }
}