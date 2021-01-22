package ifsp.applicaton.controller;

import java.io.IOException;

import ifsp.applicaton.view.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
