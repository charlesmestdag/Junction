/**
 * Cette classe représente un film avec ses informations telles que le titre, l'image, le texte et la durée.
 */
package com.helha.java.q2.cinephile.Models;

public class Film {
    private String titre;
    private String image;
    private String texte;
    private String duree;

    /**
     * Constructeur de la classe Film.
     *
     * @param titre  Le titre du film.
     * @param image  Le chemin de l'image du film.
     * @param texte  Le synopsis ou le texte associé au film.
     * @param duree  La durée du film.
     */
    public Film(String titre, String image, String texte, String duree) {
        this.titre = titre;
        this.image = image;
        this.texte = texte;
        this.duree = duree;
    }

    /**
     * Obtient le titre du film.
     *
     * @return Le titre du film.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Obtient le chemin de l'image du film.
     *
     * @return Le chemin de l'image du film.
     */
    public String getImage() {
        return image;
    }

    /**
     * Obtient le synopsis ou le texte associé au film.
     *
     * @return Le synopsis ou le texte associé au film.
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Obtient la durée du film.
     *
     * @return La durée du film.
     */
    public String getDuree() {
        return duree;
    }
}

