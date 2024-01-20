package com.example.movies.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.movies.data.User
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    private lateinit var database: DatabaseReference

    private lateinit var newUserRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),  // Add Email provider

        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)

        val usersRef = database.child("users")

//        // Получение уникального ключа для нового пользователя
//        newUserRef = usersRef.push()

    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                val uid = it.uid
                val email = it.email.toString()
                val firebaseUser = User(uid, email)
                // database.child("users").setValue(firebaseUser)
                database.child("users").child(uid).setValue(firebaseUser)
                val intent = Intent(this, MoviesActivity::class.java)
                startActivity(intent)

            }
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

 }




//package com.example.movies
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import com.firebase.ui.auth.AuthUI
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class MainActivity : AppCompatActivity() {
//
//    private val signInLauncher = registerForActivityResult(
//        FirebaseAuthUIActivityResultContract(),
//    ) { res ->
//        this.onSignInResult(res)
//    }
//
//    private lateinit var database: DatabaseReference
//    private lateinit var usersRef: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        database = Firebase.database.reference
//        usersRef = database.child("users")
//
//        val user = FirebaseAuth.getInstance().currentUser
//        user?.let {
//            val uid = it.uid
//
//            usersRef.child(uid).get().addOnSuccessListener { snapshot ->
//                if (snapshot.exists()) {
//                    // User already exists, continue with sign-in
//                    val providers = arrayListOf(
//                        AuthUI.IdpConfig.EmailBuilder().build()  // Add Email provider
//                    )
//
//                    val signInIntent = AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build()
//                    signInLauncher.launch(signInIntent)
//                } else {
//                    // User does not exist, Firebase AuthUI will handle the sign-up
//                    val providers = arrayListOf(
//                        AuthUI.IdpConfig.EmailBuilder().build()  // Add Email provider
//                    )
//
//                    val signInIntent = AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build()
//                    signInLauncher.launch(signInIntent)
//                }
//            }.addOnFailureListener { exception ->
//                // Handle the failure scenario
//                // Example: Toast.makeText(this, "Failed to check user existence: ${exception.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
//        val response = result.idpResponse
//        if (result.resultCode == RESULT_OK) {
//            val user = FirebaseAuth.getInstance().currentUser
//            user?.let {
//                val uid = it.uid
//                val email = it.email.toString()
//                val firebaseUser = User(uid, email)
//                usersRef.child(uid).setValue(firebaseUser)
//            }}}}


