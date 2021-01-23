package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        WindowLoader.setRoot("secondary");
    }
}
