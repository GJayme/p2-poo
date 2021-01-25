module ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens ifsp.applicaton.view to javafx.fxml;
    opens ifsp.applicaton.controller to javafx.fxml;

    exports ifsp.applicaton.view;
    exports ifsp.applicaton.controller;

}