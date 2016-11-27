package com.moviles.workinonit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {

    public EditText nameSign, emailSign, passwordSign;

    //Authentication
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Instantiate Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Users");

        nameSign = (EditText)findViewById(R.id.editTextNameSign);
        emailSign = (EditText)findViewById(R.id.editTextEmailSign);
        passwordSign = (EditText)findViewById(R.id.editTextPasswordSign);
    }

    public void addUser(View v) {
        mAuth.createUserWithEmailAndPassword(emailSign.getText().toString(), passwordSign.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = task.getResult().getUser();
                        Usuario usuario = new Usuario(nameSign.getText().toString(),emailSign.getText().toString(), "");
                        dbRef.child(user.getUid()).setValue(usuario);
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "No Se Registro",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG).cancel();
        finish();

    }

}
