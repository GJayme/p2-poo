package ifsp.applicaton.controller;

import java.io.IOException;

import ifsp.applicaton.view.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}