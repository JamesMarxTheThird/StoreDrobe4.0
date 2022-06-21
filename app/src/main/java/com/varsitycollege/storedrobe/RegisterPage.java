package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth registerAuthentication;
    private Button registerBTN;
    private EditText userName, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerAuthentication = FirebaseAuth.getInstance();

        userName = findViewById(R.id.usernameTB_RP);
        password = findViewById(R.id.passwordTB_RP);
        confirmPassword = findViewById(R.id.confirmPasswordTB_RP);
        registerBTN = findViewById(R.id.registerButton);


        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerAccount();
            }
        });
    }

    public void goToLoginPage (View v){
        Intent intent = new Intent (this, LoginPage.class);
        startActivity(intent);
    }

    public void goToHome (View v){
        Intent intent = new Intent (this, HomePage.class);
        startActivity(intent);
    }


    public void registerAccount() {

        // Take the value of two edit texts in Strings
        String UN, PW, CPW;
        UN = userName.getText().toString();
        PW = password.getText().toString();
        CPW = confirmPassword.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(UN)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter an email address!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(PW)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(CPW) && !(CPW == PW)) {
            Toast.makeText(getApplicationContext(),
                    "Please confirm your password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        try {


            // create new user or register new user
            registerAuthentication
                    .createUserWithEmailAndPassword(UN, PW)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "Registration successful!",
                                        Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                //progressBar.setVisibility(View.GONE);

                                // if the user created intent to login activity
                                Intent intent
                                        = new Intent(RegisterPage.this,
                                        LoginPage.class);
                                startActivity(intent);
                            } else {

                                // Registration failed
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Registration failed!!"
                                                + " Please try again later",
                                        Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                // progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
        catch (Exception e) {

            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    }


