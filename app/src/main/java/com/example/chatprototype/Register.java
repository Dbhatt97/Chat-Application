package com.example.chatprototype;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {
    public Button reg;
    public EditText email,password,name;
    //added last AL
    public DatabaseReference mdatabaseref;
    public TextView tv1_reg;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg = (Button)findViewById(R.id.button_reg);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email_reg);
        password = (EditText)findViewById(R.id.pass_reg);
        tv1_reg = (TextView)findViewById(R.id.JMPlog);
        mAuth = FirebaseAuth.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String E = email.getText().toString().trim();
                String P = password.getText().toString().trim();
                final String N = name.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(E,P).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){

                                   //  User user = new User(E,N);
//                                         FirebaseDatabase.getInstance().getReference("Users")
//                                                 .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
//                                                 .setValue(user);
                                     Toast.makeText(Register.this,"Registered"+ task.getException(),Toast.LENGTH_LONG).show();
                                     Intent in = new Intent(Register.this,Login.class);
                                     startActivity(in);
                                 }
                                 else
                                     {
                                         Toast.makeText(Register.this,"Registeration Failed",Toast.LENGTH_LONG).show();
                                 }
                    }
                    });
            }
        });
        tv1_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
    }
}
