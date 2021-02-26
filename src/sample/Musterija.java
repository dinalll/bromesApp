package sample;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Musterija {
    private String pregledan="NE";
    private String imePrezime;
    private String brojTelefona;
    private String datumPrijema;
    private String datumRazduzenja;
    private String uredaj;
    private String opisGreske;
    private int cijenaPopravka;
    private long idHashBroj;



    public Musterija(String imePrezime, String brojTelefona,  String datumPrijema,String uredaj,int pregledan) {

        this.imePrezime = imePrezime;
        this.brojTelefona = brojTelefona;
        this.datumPrijema = datumPrijema;
        this.uredaj = uredaj;
        if(pregledan==1)this.pregledan="DA";else{this.pregledan="NE";}
        System.out.println(Long.getLong(datumPrijema.replace(" ","").replace("/","").replace(":","")));
        //this.idHashBroj= Integer.parseInt(datumPrijema.replace(" ","").replace("/","").replace(":",""));
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(String datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public String getUredaj() {
        return uredaj;
    }

    public void setUredaj(String uredaj) {
        this.uredaj = uredaj;
    }

    public String getDatumRazduzenja() {
        return datumRazduzenja;
    }

    public void setDatumRazduzenja(String datumRazduzenja) {
        this.datumRazduzenja = datumRazduzenja;
    }
    public String getOpisGreske() {
        return opisGreske;
    }

    public void setOpisGreske(String opisGreske) {
       this.opisGreske = opisGreske;
    }

    public int getCijenaPopravka() {
        return cijenaPopravka;
    }

    public void setCijenaPopravka(int cijenaPopravka) {
        this.cijenaPopravka = cijenaPopravka;
    }
    public boolean artiklUServisu(){
        if(datumRazduzenja==null)return true;
        return false;
    }

    public void setPregledan(String pregledan) {
        this.pregledan = pregledan;
    }

    public String getPregledan() {
        return pregledan;
    }
}
