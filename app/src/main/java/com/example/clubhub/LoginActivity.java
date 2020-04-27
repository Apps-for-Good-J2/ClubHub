package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "Login";

    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.loginEmailText);
        passwordEditText = findViewById(R.id.loginPasswordText);

        mAuth = FirebaseAuth.getInstance();
    }

    private void signInWithEmailAndPassword(String email, String password){



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success");
                            onLoginSuccess();

                        } else {
                            Log.d("Login", "This user does not exist, or some error has occurred");
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    public void onClickToLogin(View v){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        signInWithEmailAndPassword(email, password);
    }

    private void onLoginSuccess(){

        String currentUserID = mAuth.getCurrentUser().getUid();

        // If this user is a student
        if(StudentManager.getStudent(currentUserID) != null){
            Log.d(TAG, StudentManager.getStudent(currentUserID).toString());
            Log.d(TAG, "This user is a student");
            Intent intent = new Intent(this, ClubHubStudent.class);
            startActivity(intent);
        }

        else if(TeacherManager.getTeacher(currentUserID) != null){
            // Go to teacher page
            Log.d(TAG, "This user is a teacher");
            Intent intent = new Intent(this, ClubHubTeacher.class);
            startActivity(intent);
        }

        else{
            Log.d(TAG, "This user does not exist in the database");
            Toast.makeText(getApplicationContext(),
                    "Problems retrieving your data. Contact ClubHub",
                    Toast.LENGTH_LONG).show();
        }

    }


}
