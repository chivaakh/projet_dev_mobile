package com.example.projet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.adapter.PatientAdapter;
import com.example.projet.api.ApiService;
import com.example.projet.api.RetrofitClient;
import com.example.projet.model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private Button btnAjouterPatient;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        recyclerView = findViewById(R.id.recyclerViewPatients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAjouterPatient = findViewById(R.id.btnAjouterPatient); // bouton pour ajouter un patient

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Charger les patients au démarrage
        loadPatients();

        // Action bouton Ajouter
        btnAjouterPatient.setOnClickListener(v -> {
            // Aller vers AjouterPatientActivity
            Intent intent = new Intent(PatientActivity.this, AjouterPatientActivity.class);
            startActivity(intent);
        });

    }

    // Fonction pour charger les patients
    private void loadPatients() {
        apiService.getPatients().enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Patient> patients = response.body();
                    adapter = new PatientAdapter(patients);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PatientActivity.this, "Erreur de chargement des patients", Toast.LENGTH_SHORT).show();
                    Log.e("PatientActivity", "Erreur réponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Toast.makeText(PatientActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("PatientActivity", "Échec connexion API", t);
            }
        });

    }

    // Fonction pour créer un nouveau patient
    private void createNewPatient() {
        Patient newPatient = new Patient();
        newPatient.setNom("Nouveau");
        newPatient.setPrenom("Patient");
        newPatient.setEmail("nouveau@gmail.com");

        apiService.createPatient(newPatient).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PatientActivity.this, "Patient ajouté", Toast.LENGTH_SHORT).show();
                    loadPatients(); // recharger la liste
                } else {
                    Toast.makeText(PatientActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText(PatientActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
