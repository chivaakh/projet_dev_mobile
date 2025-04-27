package com.example.projet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.Patient;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<Patient> patientList;

    // Constructeur correct
    public PatientAdapter(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.textNom.setText(patient.getNom() + " " + patient.getPrenom());
        holder.textEmail.setText(patient.getEmail());
    }

    @Override
    public int getItemCount() {
        return (patientList != null) ? patientList.size() : 0;
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView textNom, textEmail;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            textNom = itemView.findViewById(R.id.textNom);
            textEmail = itemView.findViewById(R.id.textEmail);
        }
    }
}
