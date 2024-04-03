package com.helha.java.q2.cinephile.Models;

public class Film {
    private String titre;
    private String image;
    private String texte;
    private String duree;


    public Film(String titre, String image, String texte, String duree) {
        this.titre = titre;
        this.image = image;
        this.texte = texte;
        this.duree = duree;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public String getTexte() {
        return texte;
    }
    public String getDuree() {
        return duree;
    }
}