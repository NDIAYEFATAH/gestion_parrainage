package sn.parrainage.parrainage_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.entities.Role;
import sn.parrainage.parrainage_project.entities.Utilisateur;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class NbPController implements Initializable {
    private DBConnection db = new DBConnection();
    @FXML
    private TableColumn<Utilisateur, Integer> nbPar;

    @FXML
    private TableColumn<Utilisateur, String> nomcandi;

    @FXML
    private TableView<Utilisateur> parrainTbl1;
    @FXML
    private TableColumn<Utilisateur, String> prenomCandid;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTab();
    }
    public ObservableList<Utilisateur> getCandidat()
    {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        String sql = "SELECT u.id AS candidat_id, u.nom AS candidat_nom, u.prenom AS candidat_prenom, COUNT(p.id_parrain) AS nombre_parrains " +
                "FROM user u LEFT JOIN parrainer p " +
                "ON u.id = p.candidat_id WHERE u.profil = 2 " +
                "GROUP BY u.id, u.nom, u.prenom";

        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()){
                Utilisateur util = new Utilisateur();
                util.setNom(rs.getString("nom"));
                util.setPrenom(rs.getString("prenom"));
                int nombreParrains = rs.getInt("nombre_parrains");
                utilisateurs.add(util);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public void loadTab()
    {
        ObservableList<Utilisateur> liste = getCandidat();
        parrainTbl1.setItems(liste);
        nomcandi.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCandid.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nbPar.setCellValueFactory(new PropertyValueFactory<>("nombreParrains"));
    }
}
