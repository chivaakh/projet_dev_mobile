package com.example.projet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.api.ApiService;
import com.example.projet.api.RetrofitClient;
import com.example.projet.model.Patient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterPatientActivity extends AppCompatActivity {

    private EditText editTextNom, editTextPrenom, editTextEmail;
    private Button btnSavePatient;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_patient);

        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnSavePatient = findViewById(R.id.btnSavePatient);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        btnSavePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatient();
            }
        });
    }

    private void savePatient() {
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Patient newPatient = new Patient();
        newPatient.setNom(nom);
        newPatient.setPrenom(prenom);
        newPatient.setEmail(email);

        apiService.createPatient(newPatient).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AjouterPatientActivity.this, "Patient ajouté avec succès", Toast.LENGTH_SHORT).show();
                    finish(); // Fermer cette activité et revenir
                } else {
                    Toast.makeText(AjouterPatientActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText(AjouterPatientActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
