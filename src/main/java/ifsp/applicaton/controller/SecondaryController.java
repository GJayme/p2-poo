package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        WindowLoader.setRoot("primary");
    }
}