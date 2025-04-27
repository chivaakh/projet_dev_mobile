package com.example.projet.model;

import java.util.List;

public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<Object> rendezVous; // Tu peux mettre List<Object> pour l'instant, si tu n'utilises pas encore rendezVous

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Object> getRendezVous() { return rendezVous; }
    public void setRendezVous(List<Object> rendezVous) { this.rendezVous = rendezVous; }
}
