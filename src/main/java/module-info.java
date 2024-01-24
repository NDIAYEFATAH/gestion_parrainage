module sn.parrainage.parrainage_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayNotification;

    opens sn.parrainage.parrainage_project.entities to javafx.base;
    opens sn.parrainage.parrainage_project to javafx.fxml;
    exports sn.parrainage.parrainage_project;
    exports sn.parrainage.parrainage_project.controller;
    opens sn.parrainage.parrainage_project.controller to javafx.fxml;
}