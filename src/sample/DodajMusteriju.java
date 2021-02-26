package sample;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DodajMusteriju {

    public TextField imeFld;
    public TextField brFld;
    public TextField aparatFld;
    public void dodaj(){
        BazaDAO.getInstance().dodaj(new Musterija(imeFld.getText(),brFld.getText(),dajDatum(),aparatFld.getText(),0));
        zatvori();
    }
    public void zatvori(){
        ((Stage) imeFld.getParent().getScene().getWindow()).close();
    }
    public String dajDatum(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now)+"";
    }
}
