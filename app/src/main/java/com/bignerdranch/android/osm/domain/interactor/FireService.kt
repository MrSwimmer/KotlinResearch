package com.bignerdranch.android.osm.domain.interactor

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseStorage
import java.io.File


class FireService {
    private var auth = FirebaseAuth.getInstance()
    private var storage = FirebaseStorage.getInstance().getReference()

    fun signIn(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
                .map({ authResult -> authResult.user != null })
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun signUp(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
                .map({ authResult -> authResult.user != null })
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun downloadData(callback: DownloadStorageCallBack) {
        val email = auth.currentUser!!.email
        val localFile = File.createTempFile("images", "jpg")
        RxFirebaseStorage.getFile(storage.child(email!!), localFile)
                .subscribe({ callback.onSuccess(it) }, { callback.onError(it) })
    }

    fun uploadData(db: Uri, callBack: UploadStorageCallBack) {
        val email = auth.currentUser!!.email
        storage.child("$email/data.db")
                .putFile(db)
                .addOnSuccessListener({callBack.onSuccess(it)})
                .addOnFailureListener({callBack.onError(it)})
    }

    interface AuthCallBack {
        fun onSuccess(success: Boolean)

        fun onError(e: Throwable)
    }

    interface DownloadStorageCallBack {
        fun onSuccess(taskSnapshot: FileDownloadTask.TaskSnapshot)

        fun onError(e: Throwable)
    }

    interface UploadStorageCallBack {
        fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot)

        fun onError(e: Throwable)
    }
}