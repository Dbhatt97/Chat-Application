package com.example.chatprototype;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    public Button log;
    public EditText email,password;
    public TextView tv1;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null)
        {
            Intent in = new Intent(Login.this,Main_Page.class);
            startActivity(in);
            finish();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        tv1 = (TextView)findViewById(R.id.JMPreg);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Logging in",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(Login.this,Main_Page.class);
                            startActivity(in);
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Logging Failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
tv1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent in = new Intent(Login.this,Register.class);
        startActivity(in);
    }
});

    }
}
