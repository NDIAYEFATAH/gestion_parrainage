package sn.parrainage.parrainage_project.controller;

import javafx.scene.control.ListCell;
import sn.parrainage.parrainage_project.entities.Role;

public class RoleList extends ListCell<Role> {
    @Override
    protected void updateItem(Role role, boolean empty) {
        super.updateItem(role, empty);

        if (empty || role == null) {
            setText(null);
        } else {
            setText(role.getName());
        }
    }
}
