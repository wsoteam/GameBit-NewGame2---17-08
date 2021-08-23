package com.dat.android.gamebit.utils.fb

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object DBWorker {
    fun requestPercent(path : String, dbCallbacks: DBCallbacks) {


        FirebaseDatabase
            .getInstance("https://gamebit-aeabc-default-rtdb.firebaseio.com/")
            .reference
            .child(path)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                    dbCallbacks.onError()
                    Log.e("LOL", "onCancelled")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.e("LOL", "onDataChange")
                    if (p0.getValue(String::class.java) != null) {
                        val url : String = p0.getValue(String::class.java) ?: ""
                        Log.e("LOL", url)
                        if (url == ""){
                            Log.e("LOL", "Error")
                            dbCallbacks.onError()
                        }else{
                            Log.e("LOL", "Success")
                            dbCallbacks.onSuccess(url)
                        }
                    } else {
                        Log.e("LOL", "Error")
                        dbCallbacks.onError()
                    }
                }
            })
    }
}