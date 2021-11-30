package com.dentag.pulsometer.datasources

import com.dentag.pulsometer.model.FirestoreDatabase
import com.dentag.pulsometer.model.RecyclerData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val fireStore: FirestoreDatabase
) : MeasureRemoteDataSource {
    override suspend fun getMeasures(): Flow<List<RecyclerData.PressureAndPulse>> = callbackFlow {
        val collection = fireStore.db.collection(COLLECTION_PATH)
        val subscription = collection.addSnapshotListener { snapshot, _ ->
            if (!snapshot!!.isEmpty) {
                trySend(snapshot.toObjects(RecyclerData.PressureAndPulse::class.java))
            }
        }
        awaitClose { subscription.remove() }
    }

    override suspend fun postMeasure(measure: RecyclerData.PressureAndPulse) {
        fireStore.db.collection(COLLECTION_PATH)
            .add(measure)
    }

    companion object {
        private const val COLLECTION_PATH = "measures"
    }
}