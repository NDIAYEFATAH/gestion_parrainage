package sn.parrainage.parrainage_project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.entities.Role;
import sn.parrainage.parrainage_project.entities.RoleService;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    @FXML
    private TextField identifianttfd;

    @FXML
    private TextField nomtfd;

    @FXML
    private PasswordField passwordtfd;

    @FXML
    private TextField prenomtfd;

    @FXML
    private ComboBox<Role> roleComboBox;
    private RoleService roleService;
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleService = new RoleService();
        List<Role> roles = roleService.getAllRole();
        ObservableList<Role> roles1 = FXCollections.observableArrayList(roles);
        roleComboBox.setItems(roles1);

        roleComboBox.setCellFactory(param -> new  RoleList());
        roleComboBox.setButtonCell(new RoleList());
    }
    @FXML
    void save(ActionEvent event) throws SQLException{
        String sql = "INSERT INTO user VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,nomtfd.getText());
            db.getPstm().setString(2,prenomtfd.getText());
            db.getPstm().setString(3,identifianttfd.getText());
            db.getPstm().setString(4,passwordtfd.getText());
            db.getPstm().setInt(5,1);
            Role selectedRole = roleComboBox.getValue();
            if (selectedRole != null) {
                db.getPstm().setInt(6, selectedRole.getId());
            }

            db.executeMaj();
            db.closeConnection();

            nomtfd.setText("");
            prenomtfd.setText("");
            identifianttfd.setText("");
            passwordtfd.setText("");
            roleComboBox.setValue(null);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
