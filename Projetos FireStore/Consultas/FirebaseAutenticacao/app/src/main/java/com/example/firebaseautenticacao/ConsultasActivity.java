package com.example.firebaseautenticacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ConsultasActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView textResultado;
    private EditText editNome,editSalario,editPet, editSalario2, editFilhos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        editNome = findViewById(R.id.editNome);
        editSalario = findViewById(R.id.editSalario);
        editPet = findViewById(R.id.editPet);
        editSalario2 = findViewById(R.id.editSalario2);
        editFilhos = findViewById(R.id.editFilhos);
        textResultado = findViewById(R.id.textResultado);
    }

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }
    public void carregardadospelonome(View view) {
        String nome = editNome.getText().toString();

        CollectionReference pessoas = db.collection("exemplo");
        Query query = pessoas.whereEqualTo("nome", nome);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                resultado += "ID: "+ document.getId()+'\n'+document.getData().toString() + '\n';

                                listPessoas.add(document.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for (Pessoa p: listPessoas){
                                resultado += p.toString() + "\n";
                            }

                            textResultado.setText(resultado);
                        }
                    }
                });
    }

    public void carregardadospelosalario(View view) {
        double salario = Double.parseDouble(editSalario.getText().toString());

        CollectionReference pessoas = db.collection("exemplo");
        Query query = pessoas.whereGreaterThan("salario", salario);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                resultado += "ID: "+ document.getId()+'\n'+document.getData().toString() + '\n';

                                listPessoas.add(document.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for (Pessoa p: listPessoas){
                                resultado += p.toString() + "\n";
                            }

                            textResultado.setText(resultado);
                        }
                    }
                });
    }

    public void carregardadospelopet(View view) {
        String pet = editPet.getText().toString();

        CollectionReference pessoas = db.collection("exemplo");
        Query query = pessoas.whereArrayContains("pets", pet);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                resultado += "ID: "+ document.getId()+'\n'+document.getData().toString() + '\n';

                                listPessoas.add(document.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for (Pessoa p: listPessoas){
                                resultado += p.toString() + "\n";
                            }

                            textResultado.setText(resultado);
                        }
                    }
                });
    }

    public void carregardadospeloSalarioAndFilhos(View view) {
        double salario = Double.parseDouble(editSalario2.getText().toString());

        CollectionReference pessoas = db.collection("exemplo");
        Query query = pessoas.whereGreaterThan("salario", salario);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                resultado += "ID: "+ document.getId()+'\n'+document.getData().toString() + '\n';

                                listPessoas.add(document.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for (Pessoa p: listPessoas){
                                int filhos = Integer.parseInt(editFilhos.getText().toString());
                                if(p.qtde_filhos > filhos){
                                    resultado += p.toString() + "\n";
                                }

                            }

                            textResultado.setText(resultado);
                        }
                    }
                });
    }
}
