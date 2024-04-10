package com.helha.java.q2.cinephile.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDb {
    private Connection conn;

    public FilmDb() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:resources/films.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Titre, Image, Synopsis, Duree  FROM Films");

            while (resultSet.next()) {
                String titre = resultSet.getString("Titre");
                String image = resultSet.getString("Image");
                String texte = resultSet.getString("Synopsis");
                String duree = resultSet.getString("Duree");
                films.add(new Film(titre, image, texte, duree));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}
