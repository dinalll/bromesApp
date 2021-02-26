package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {
    public PasswordField passFld;
    public TextField imeFld;
    public void zatvori(ActionEvent actionEvent){
        passFld.getParent().getScene().getWindow().hide();
    }
    public void potvrdi(ActionEvent actionEvent){
        if(passFld.textProperty().get().equals("password") && imeFld.getText().equals("Aldin")){
            passFld.getParent().getScene().getWindow().hide();
        }
    }
}
