package sn.parrainage.parrainage_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sn.parrainage.parrainage_project.entities.SessionManager;
import sn.parrainage.parrainage_project.entities.Utilisateur;
import sn.parrainage.parrainage_project.entities.UtilisateurImpl;
import sn.parrainage.parrainage_project.repositories.IUser;
import sn.parrainage.parrainage_project.tools.Notification;
import sn.parrainage.parrainage_project.tools.Outils;

public class LoginController {
    @FXML
    private TextField loginTfd;

    @FXML
    private PasswordField passwordTfd;

    private IUser userdao = new UtilisateurImpl();
    @FXML
    void login(ActionEvent event) {
        String login = loginTfd.getText();
        String password = passwordTfd.getText();
        if (login.trim().equals("") || password.trim().equals("")){
            Notification.NotifError("Erreur !", "Tous les champs sont obligatoires !");
        }else{
            Utilisateur user = userdao.seConnecter(login, password);
            if(user != null){
                try {
//                    Notification.NotifSuccess("Succés !", "Connexion réussie !");
                    if (user.getProfile().getName().equalsIgnoreCase("ROLE_ADMIN"))
                        Outils.load(event, "Bienvenue", "/pages/admin.fxml");
                    else if (user.getProfile().getName().equalsIgnoreCase("ROLE_CANDIDAT"))
                        Outils.load(event, "Bienvenue", "/pages/candidat.fxml");
                    else
                        Outils.load(event, "Bienvenue", "/pages/electeurDashboard.fxml");
                    SessionManager.setCurrentUserId(user);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Notification.NotifError("Erreur !", "Login et/ou password incorrects !");
            }
        }
    }

}
