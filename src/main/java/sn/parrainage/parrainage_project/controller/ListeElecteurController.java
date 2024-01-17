package sn.parrainage.parrainage_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.entities.Role;
import sn.parrainage.parrainage_project.entities.Utilisateur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListeElecteurController implements Initializable {
    private DBConnection db = new DBConnection();
    @FXML
    private TableView<Utilisateur> electeursTbl;

    @FXML
    private Button modifierBtn;

    @FXML
    private TableColumn<Utilisateur, String> nomelec;

    @FXML
    private TableColumn<Utilisateur, String> prenomElect;

    @FXML
    private TableColumn<Utilisateur, Role> profileElec;

    @FXML
    private Button supprimerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public ObservableList<Utilisateur> getElecteurs()
    {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user WHERE profil=3";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()){
                Utilisateur util = new Utilisateur();
                util.setNom(rs.getString("nom"));
                util.setPrenom(rs.getString("prenom"));
                Role role = new Role(rs.getInt("profil"));

                util.setProfile(role);
                utilisateurs.add(util);
            }
            System.out.println("Nombre d'utilisateurs récupérés : " + utilisateurs.size());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return utilisateurs;
    }
    public void loadTable()
    {
        ObservableList<Utilisateur> liste = getElecteurs();
        electeursTbl.setItems(liste);
        nomelec.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        prenomElect.setCellValueFactory(new  PropertyValueFactory<Utilisateur, String>("prenom"));
        profileElec.setCellValueFactory(new PropertyValueFactory<Utilisateur, Role>("profil"));
    }
}
