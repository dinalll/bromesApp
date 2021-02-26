package sample;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BazaDAO {
    private static BazaDAO instance=null;
    private Connection conn;
    private String url="";
    private PreparedStatement dodajUbazu,aktivni,zakljuci,razduzi,dajMusterijubr;
    private BazaDAO(){
        url="jdbc:sqlite:baza.db";
        try {
            conn=DriverManager.getConnection(url);
            dodajUbazu=conn.prepareStatement("INSERT into Musterija (Naziv,Telefon,Uredjaj,datumZ,aktivan) values(?,?,?,?,?)");
            aktivni=conn.prepareStatement("Select * from Musterija where datumR is null");
            zakljuci=conn.prepareStatement("Update Musterija set opisGreske=?,cijenaPopravka=?,aktivan=? where Telefon=?");
            dajMusterijubr=conn.prepareStatement("Select * from Musterija where Telefon=?");
            razduzi=conn.prepareStatement("Update Musterija set datumR=? where Telefon=?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void dodaj(Musterija musterija){
        try {
            dodajUbazu.setString(1,musterija.getImePrezime());
            dodajUbazu.setString(2,musterija.getBrojTelefona());
            dodajUbazu.setString(3,musterija.getUredaj());
            dodajUbazu.setString(4,musterija.getDatumPrijema()+"");
            dodajUbazu.setInt(5,0);
            dodajUbazu.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public ArrayList<Musterija> dajAktivne(){
        ArrayList<Musterija> povratni= new ArrayList<Musterija>();
        try {
            ResultSet rs=aktivni.executeQuery();
            while(rs.next()){
                Musterija dodaj= new Musterija(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getInt(8));
                dodaj.setOpisGreske(rs.getString(5));
                dodaj.setCijenaPopravka(rs.getInt(6));
                dodaj.setDatumRazduzenja(rs.getString(7));
                povratni.add(dodaj);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return povratni;
    }
    public Musterija dajMusteriju(String broj){
        Musterija povratni=null;
        try {
            dajMusterijubr.setString(1,broj);
            ResultSet rs= dajMusterijubr.executeQuery();
            povratni= new Musterija(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(7));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return povratni;
    }
    public void zakljuci(String greska,int cijena,String tel){
        try {
            zakljuci.setString(1,greska);
            zakljuci.setInt(2,cijena);
            zakljuci.setInt(3,1);
            zakljuci.setString(4,tel);
            zakljuci.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void razduzi(String tel){
        try {
            razduzi.setString(1,dajDatum());
            razduzi.setString(2,tel);
            razduzi.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static BazaDAO getInstance(){
        if(instance==null){
            instance=new BazaDAO();
        }
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null){
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public String dajDatum(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now)+"";
    }
}
