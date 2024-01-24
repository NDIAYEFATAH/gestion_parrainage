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
import sn.parrainage.parrainage_project.entities.SessionManager;
import sn.parrainage.parrainage_project.entities.UtilisateurImpl;
import sn.parrainage.parrainage_project.repositories.IUser;
import sn.parrainage.parrainage_project.tools.Notification;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static sn.parrainage.parrainage_project.entities.SessionManager.getCurrentUserId;

public class ElecteurDashboardController implements Initializable {
    IUser userdao = new UtilisateurImpl();
    private DBConnection db = new DBConnection();
    @FXML
    private TableColumn<Utilisateur, Integer> idCand;

    @FXML
    private TableColumn<Utilisateur, String> nomCand;

    @FXML
    private TableView<Utilisateur> parrainageTbl;

    @FXML
    private TableColumn<Utilisateur, String> prenomCan;
    @FXML
    private Button parrainCandidat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
    }
    public ObservableList<Utilisateur> getAllCandidat()
    {
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user WHERE profil=2";

        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()){
                Utilisateur util = new Utilisateur();
                util.setId(rs.getInt("id"));
                util.setNom(rs.getString("nom"));
                util.setPrenom(rs.getString("prenom"));
                utilisateurs.add(util);
            }
            System.out.println("liste Candidat " + utilisateurs.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return utilisateurs;
    }
    public void load()
    {
        ObservableList<Utilisateur> listeCand = getAllCandidat();
        parrainageTbl.setItems(listeCand);
        prenomCan.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nomCand.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idCand.setCellValueFactory(new PropertyValueFactory<>("id"));
    }
    @FXML
    void parrainCandi(ActionEvent event){
        Utilisateur CanSelected = parrainageTbl.getSelectionModel().getSelectedItem();
        Utilisateur userLog = SessionManager.getCurrentUserId();
        if (userdao.countParrainsForCandidat(CanSelected.getId()) <= 7){
            if (userLog != null && userLog.getActived() ==1){
                boolean aDejaParraine = userdao.nbParrain(userLog.getId());
                if (!aDejaParraine){
                    userdao.saveParrainage(userLog.getId(),CanSelected.getId());
                    parrainCandidat.setDisable(true);
                }
                else {
                    Notification.NotifError("Erreur","Vous avez deja parrainer");
                }

                System.out.println("Succes");
            }else {
                Notification.NotifError("Erreur","Cet electeur a deja parraine");
            }
        }else {
            Notification.NotifError("Erreur","Nombre parrain atteint");
        }
    }

}
