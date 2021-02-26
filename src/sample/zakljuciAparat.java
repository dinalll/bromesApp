package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class zakljuciAparat{
    private Musterija m;
    @FXML
    TextField imeFld;
    @FXML
    TextField aparatFld;
    @FXML
    TextField cijenaFld;
    @FXML
    TextArea opisArea;

    public zakljuciAparat(Musterija dajTrenutni) {
        this.m=dajTrenutni;
    }
    public void initialize(){
        imeFld.setText(m.getImePrezime());
        aparatFld.setText(m.getUredaj());
    }
    public void zakljuci(){
        BazaDAO.getInstance().zakljuci(opisArea.getText(), Integer.parseInt(cijenaFld.getText()),m.getBrojTelefona());
        odustani();
    }
    public void odustani(){
        imeFld.getParent().getScene().getWindow().hide();
    }

}
