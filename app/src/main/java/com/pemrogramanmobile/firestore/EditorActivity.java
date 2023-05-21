package com.pemrogramanmobile.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditorActivity extends AppCompatActivity {

    private EditText editNamaPakaian, editCuciBasah, editCuciKering, editSetrika;
    private Button btnSimpan;
    private ProgressDialog progressDialog;
    private String id = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editNamaPakaian = findViewById(R.id.et_namapakaian);
        editCuciBasah = findViewById(R.id.et_cucibasah);
        editCuciKering = findViewById(R.id.et_cucikering);
        editSetrika = findViewById(R.id.et_setrika);
        btnSimpan = findViewById(R.id.btn_simpan);

        progressDialog = new ProgressDialog(EditorActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        btnSimpan.setOnClickListener(v ->{
            if (editNamaPakaian.getText().length()>0 && editCuciBasah.getText().length()>0 && editCuciKering.getText().length()>0 && editSetrika.getText().length()>0){
                simpanData(editNamaPakaian.getText().toString(), editCuciBasah.getText().toString(), editCuciKering.getText().toString(), editSetrika.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Silakan Isi semua data pakaian!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra("id");
            editNamaPakaian.setText(intent.getStringExtra("nama pakaian"));
            editCuciBasah.setText(intent.getStringExtra("cuci basah"));
            editCuciKering.setText(intent.getStringExtra("cuci kering"));
            editSetrika.setText(intent.getStringExtra("setrika"));
        }
    }

    private void simpanData(String namaPakaian, String cuciBasah, String cuciKering, String setrika){
        Map<String, Object> pakaian = new HashMap<>();
        pakaian.put("nama pakaian", namaPakaian);
        pakaian.put("cuci basah", cuciBasah);
        pakaian.put("cuci kering", cuciKering);
        pakaian.put("setrika", setrika);

        progressDialog.show();

        if (id!=null){
            db.collection("pakaian").document(id)
                    .set(pakaian)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            db.collection("pakaian")
                    .add(pakaian)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}