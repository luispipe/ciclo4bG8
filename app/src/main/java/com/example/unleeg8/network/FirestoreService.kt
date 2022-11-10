package com.example.unleeg8.network

import com.example.unleeg8.Model.Books
import com.google.firebase.firestore.FirebaseFirestore

const val BOOKS_COLLECTION_NAME= "books"
const val SALES_COLLECTION_NAME= "sales"
const val USER_COLLECTION_NAME= "user"
class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()

    fun getBooks(callBack: CallBack<List<Books>>){
        firebaseFirestore.collection(BOOKS_COLLECTION_NAME)
            .get()
            .addOnSuccessListener {
                result-> for (doc in result){
                    val list= result.toObjects(Books::class.java)
                    callBack.onSuccess(list)
                    break
                }
            }
    }


}