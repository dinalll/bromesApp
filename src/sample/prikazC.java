package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class prikazC {
    @FXML TextField imeFld;
    @FXML TextField telFld;
    @FXML TextField aparatFld;
    @FXML TextField prijemFld;
    @FXML TextField izdavanjeFld;
    @FXML TextField cijenaFLd;
    @FXML TextArea pregledFld;
    private Musterija musterija=null;
    prikazC(Musterija musterija){
        this.musterija=musterija;
    }
    public void initialize(){
        imeFld.setText(musterija.getImePrezime());
        telFld.setText(musterija.getBrojTelefona());
        aparatFld.setText(musterija.getUredaj());
        prijemFld.setText(musterija.getDatumPrijema());
        if(musterija.getDatumRazduzenja()==null || musterija.getDatumRazduzenja().length()==0){
            izdavanjeFld.setText("Nije podignut aparat");
        }
        if(musterija.getPregledan().equals("NE")){
            cijenaFLd.setText("Aparat nije pregledan");
            pregledFld.setText("Kvar aparata nije konstatovan");
        }
        else{
            izdavanjeFld.setText(musterija.getDatumRazduzenja());
            cijenaFLd.setText(musterija.getCijenaPopravka()+".00 KM");
            pregledFld.setText(musterija.getOpisGreske());
        }
    }
}
