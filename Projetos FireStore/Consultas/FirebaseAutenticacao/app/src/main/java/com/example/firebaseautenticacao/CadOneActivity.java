package com.example.firebaseautenticacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CadOneActivity extends AppCompatActivity {

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private EditText   editTitulo;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_one);


        radioGroup1 = (RadioGroup) findViewById(R.id.group1);
        radioGroup2 = findViewById(R.id.group2);
        editTitulo = findViewById(R.id.editTitulo);
    }

    // recebendo o botão selecionado

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }
    public void salvarNoFirebase1(View view) {

        int id1 = radioGroup1.getCheckedRadioButtonId();
        int id2 = radioGroup2.getCheckedRadioButtonId();
        String prioridade = "";
        String categoria =  "";

        if (id1 == R.id.radio_alta){
            prioridade = "Alta";
        }if (id1 == R.id.radio_media){
            prioridade = "Média";
        }if (id1 == R.id.radio_baixa){
            prioridade = "Baixa";
        }

        if (id2 == R.id.radio_trabalhar){
            categoria = "Trabalhar";
        }if (id2 == R.id.radio_jogar){
            categoria = "Jogar";
        }if (id2 == R.id.radio_dormir){
            categoria = "Dormir";
        }

        String Data = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String titulo = editTitulo.getText().toString();

        Map<String,Object> dadosTarefa = new HashMap<>();
        dadosTarefa.put("titulo", titulo);
        dadosTarefa.put("data", Data);
        dadosTarefa.put("prioridade", prioridade);
        dadosTarefa.put("categoria", categoria);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("tarefas").document(user.getUid())
                .set(dadosTarefa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String message = "Dados da tarefa salvos com sucesso";
                        Toast.makeText(CadOneActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = "Erro ao salvar os dados da tarefa";
                        Toast.makeText(CadOneActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
