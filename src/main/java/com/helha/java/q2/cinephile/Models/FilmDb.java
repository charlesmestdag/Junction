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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Films");

            while (resultSet.next()) {
                String titre = resultSet.getString("Titre");
                String synopsis = resultSet.getString("Synopsis");
                String duree = resultSet.getString("Duree");
                String bandeAnnonce = resultSet.getString("BandeAnnonce");
                String image = resultSet.getString("Image");
                String dateSortie = resultSet.getString("DateSortie");
                String jourDisponible = resultSet.getString("JourDisponible");
                String heureDisponible = resultSet.getString("HeureDisponible");
                String debut = resultSet.getString("Debut");
                String fin = resultSet.getString("Fin");
                int tiquetsRestantsSalle1 = resultSet.getInt("TiquetsRestantsSalle1");
                int id = resultSet.getInt("id");
                int tiquetsRestantsSalle2 = resultSet.getInt("TiquetsRestantsSalle2");
                int tiquetsRestantsSalle3 = resultSet.getInt("TiquetsRestantsSalle3");

                films.add(new Film(titre, synopsis, duree, bandeAnnonce, image, dateSortie, jourDisponible,
                        heureDisponible, debut, fin, tiquetsRestantsSalle1, id, tiquetsRestantsSalle2, tiquetsRestantsSalle3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }


    public Film getFilmById(int id) throws SQLException {
        Film film = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM Films WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                film = extractFilmFromResultSet(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return film;
    }


    private Film extractFilmFromResultSet(ResultSet resultSet) throws SQLException {
        Film film = new Film(
                resultSet.getString("Titre"),
                resultSet.getString("Synopsis"),
                resultSet.getString("Duree"),
                resultSet.getString("BandeAnnonce"),
                resultSet.getString("Image"),
                resultSet.getString("DateSortie"),
                resultSet.getString("JourDisponible"),
                resultSet.getString("HeureDisponible"),
                resultSet.getString("Debut"),
                resultSet.getString("Fin"),
                resultSet.getInt("TiquetsRestantsSalle1"),
                resultSet.getInt("id"),
                resultSet.getInt("TiquetsRestantsSalle2"),
                resultSet.getInt("TiquetsRestantsSalle3")
        );
        return film;
    }


    public void updateFilm(Film film) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE Films SET Titre = ?, Synopsis = ?, Duree = ?, BandeAnnonce = ?, Image = ?, DateSortie = ?, JourDisponible = ?, HeureDisponible = ?, Debut = ?, Fin = ?, TiquetsRestantsSalle1 = ?, TiquetsRestantsSalle2 = ?, TiquetsRestantsSalle3 = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, film.getTitre());
            preparedStatement.setString(2, film.getTexte());
            preparedStatement.setString(3, film.getDuree());
            preparedStatement.setString(4, film.getBandeAnnonce());
            preparedStatement.setString(5, film.getImage());
            preparedStatement.setString(6, film.getDateSortie());
            preparedStatement.setString(7, film.getJourDisponible());
            preparedStatement.setString(8, film.getHeureDisponible());
            preparedStatement.setString(9, film.getDebut());
            preparedStatement.setString(10, film.getFin());
            preparedStatement.setInt(11, film.getTiquetsRestantsSalle1());
            preparedStatement.setInt(12, film.getTiquetsRestantsSalle2());
            preparedStatement.setInt(13, film.getTiquetsRestantsSalle3());
            preparedStatement.setInt(14, film.getId());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}

