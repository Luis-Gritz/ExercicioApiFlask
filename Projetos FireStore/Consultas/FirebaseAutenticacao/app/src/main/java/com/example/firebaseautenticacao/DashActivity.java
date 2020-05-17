package com.example.firebaseautenticacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;
    private FirebaseFirestore db;
    private TextView textResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
        textResultado = findViewById(R.id.textResultado);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Bem vindo, "+user.getEmail());
        db = FirebaseFirestore.getInstance();
    }

    public void  sair (View view){
        mAuth.signOut();
        Intent inicio = new Intent(DashActivity.this,MainActivity.class);
        startActivity(inicio);
        finish();
    }

    public void registrarTarefa(View view) {
        Intent cadoneactivity = new Intent(DashActivity.this, CadOneActivity.class);
        startActivity(cadoneactivity);
    }

    public void registrardadosvenda(View view) {
        Intent cadtwoactivity = new Intent(DashActivity.this, CadTwoActivity.class);
        startActivity(cadtwoactivity);
    }

    public void gerardadosfirebase(View view) {
        List<Pessoa> pessoas = PopulateUtil.loadPessoas();


        for(Pessoa p : pessoas) {
            db.collection("exemplo").add(p);
        }

    }

    public void consultar(View view) {
        Intent consultaractivity = new Intent(DashActivity.this, ConsultasActivity.class);
        startActivity(consultaractivity);
    }



}
