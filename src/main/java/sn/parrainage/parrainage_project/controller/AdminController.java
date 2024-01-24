package sn.parrainage.parrainage_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sn.parrainage.parrainage_project.tools.Outils;

import java.io.IOException;

public class AdminController {

    @FXML
    private Button ajoutUserBtn;

    @FXML
    private Button listerElect;

    @FXML
    void ajoutUser(ActionEvent event) throws IOException {
        // Charger le fichier FXML de la nouvelle page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/inscription.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Créer une nouvelle étape (stage) pour la nouvelle scène
        Stage stage = new Stage();
        stage.setTitle("Bienvenue"); // Titre de la nouvelle fenêtre
        stage.setScene(scene);

        // Afficher la nouvelle fenêtre
        stage.show();

    }
    @FXML
    void listerElect(ActionEvent event) throws IOException {
        // Charger le fichier FXML de la nouvelle page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/listeElecteur.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Créer une nouvelle étape (stage) pour la nouvelle scène
        Stage stage = new Stage();
        stage.setTitle("Liste Des Electeurs"); // Titre de la nouvelle fenêtre
        stage.setScene(scene);

        // Afficher la nouvelle fenêtre
        stage.show();
    }
    @FXML
    void listerCand(ActionEvent event) throws IOException {
        // Charger le fichier FXML de la nouvelle page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/listeCandidat.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Créer une nouvelle étape (stage) pour la nouvelle scène
        Stage stage = new Stage();
        stage.setTitle("Liste Des Electeurs"); // Titre de la nouvelle fenêtre
        stage.setScene(scene);

        // Afficher la nouvelle fenêtre
        stage.show();
    }
}
