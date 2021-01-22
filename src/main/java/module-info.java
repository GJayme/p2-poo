module ifsp {
    requires javafx.controls;
    requires javafx.fxml;

    opens ifsp.applicaton.view to javafx.fxml;
    opens ifsp.applicaton.controller to javafx.fxml;

    exports ifsp.applicaton.view;
    exports ifsp.applicaton.controller;

}