package com.dentag.pulsometer.di

import com.dentag.pulsometer.model.FirestoreDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideFirestore(): FirestoreDatabase {
        return FirestoreDatabase(Firebase.firestore)
    }
}