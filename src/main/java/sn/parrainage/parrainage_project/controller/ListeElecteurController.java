package sn.parrainage.parrainage_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.entities.Role;
import sn.parrainage.parrainage_project.entities.Utilisateur;
import sn.parrainage.parrainage_project.tools.Notification;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ListeElecteurController implements Initializable {

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
    @FXML
    private Button DesactiverBtn;
    private DBConnection db = new DBConnection();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
    public ObservableList<Utilisateur> getElecteurs()
    {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        String sql = "SELECT u.nom, u.prenom, r.id, r.name FROM user u JOIN role r ON u.profil = r.id AND profil=3";

        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()){
                Utilisateur util = new Utilisateur();
                util.setNom(rs.getString("nom").toUpperCase());
                util.setPrenom(rs.getString("prenom").toUpperCase());
//                Role role = new Role(rs.getInt("profil"));
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                util.setProfile(role);
                utilisateurs.add(util);
            }
//            System.out.println("Nombre Utilisateurs: " + utilisateurs.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return utilisateurs;
    }
    public void loadTable()
    {
        ObservableList<Utilisateur> liste = getElecteurs();
        electeursTbl.setItems(liste);
        nomelec.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomElect.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        profileElec.setCellValueFactory(new PropertyValueFactory<>("profil"));
    }
    @FXML
    void desactiver(ActionEvent event) {
        Utilisateur electSelected = electeursTbl.getSelectionModel().getSelectedItem();

        if (electSelected != null){
            String sql = "UPDATE user SET actived = 0 WHERE nom = ? AND prenom = ?";
            try {
                db.initPrepar(sql);
                db.getPstm().setString(1,electSelected.getNom());
                db.getPstm().setString(2,electSelected.getPrenom());
                int ligneSelect = db.executeMaj();
                if (ligneSelect > 0){
                    loadTable();
                }else {
                    Notification.NotifError("Erreur !", "Echec de la desactivation du compte !");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}