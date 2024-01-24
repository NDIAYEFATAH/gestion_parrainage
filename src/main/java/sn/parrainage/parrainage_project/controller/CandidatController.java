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

public class CandidatController implements Initializable {
    private DBConnection db = new DBConnection();
    @FXML
    private TableView<Utilisateur> candidatTbl;

    @FXML
    private TableColumn<Utilisateur, Integer> id;

    @FXML
    private TableColumn<Utilisateur, String> nom;

    @FXML
    private TableColumn<Utilisateur, String> prenom;

    @FXML
    private TableColumn<Utilisateur, Role> profil;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTab();
    }
    public ObservableList<Utilisateur> getCandidat()
    {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user WHERE profil=2";

        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()){
                Utilisateur util = new Utilisateur();
                util.setNom(rs.getString("nom"));
                util.setPrenom(rs.getString("prenom"));
                Role role = new Role(rs.getInt("profil"));

                util.setProfile(role);
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
        candidatTbl.setItems(liste);
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        profil.setCellValueFactory(new PropertyValueFactory<>("profil"));
    }
}
