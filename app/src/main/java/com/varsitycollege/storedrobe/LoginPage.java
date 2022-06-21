package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity {

String emailLayout = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private EditText userNameLP;
    private EditText passwordLP;
    private Button loginBTN;
    private FirebaseAuth loginAuthentication;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("Users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userNameLP = findViewById(R.id.usernameTB);
        passwordLP = findViewById(R.id.passwordTB);
        loginBTN = findViewById(R.id.loginButton);

        loginAuthentication = FirebaseAuth.getInstance();

        //userNameLP = findViewById(R.id.usernameTB_LP);

        //

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });




    }
/*
    Button btn = (Button)findViewById(R.id.loginButton);

    View.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginPage.this, HomePage.class));
        }
    });
*/

    public void loginUser(){

        // Take the value of two edit texts in Strings
        String LP_UN, LP_PW;
        LP_UN = userNameLP.getText().toString();
        LP_PW = passwordLP.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(LP_UN)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(LP_PW)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        loginAuthentication.signInWithEmailAndPassword(LP_UN, LP_PW)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    //progressBar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(LoginPage.this,
                                            HomePage.class);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    //progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }




    public void goToHome (View v){
        Intent intent = new Intent (this, HomePage.class);
        startActivity(intent);
    }

    public void goToRegister (View v){
        Intent intent = new Intent (this, RegisterPage.class);
        startActivity(intent);
    }

    public void goToForgotPassword (View v){
        Intent intent = new Intent (this, ForgotPasswordPage.class);
        startActivity(intent);
    }

    /*
    public void textboxAuthentication() {

        String email=inputEmail.getText().toString();
        String username=inputUserName.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmPassword=inputConfirmPassword.getText().toString();


        if(email.matches())
        {

        }
        else if()
        {

        }
    }
*/
}