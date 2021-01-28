module ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens ifsp.applicaton.view to javafx.fxml;
    opens ifsp.applicaton.controller to javafx.fxml;
    opens ifsp.domain.entities.pessoa to javafx.base;
    opens ifsp.domain.entities.venda to javafx.base;

    exports ifsp.applicaton.view;
    exports ifsp.applicaton.controller;
}