package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    public TableView<Musterija> tabela;
    public TableColumn imeTbl;
    public TableColumn brTbl;
    public TableColumn nazivApTbl;
    public TableColumn datumPrTbl;
    public TableColumn pregledanTbl;

    public void initialize(){
        popuniAktivne();
        tabela.setRowFactory( tv -> {
            TableRow<Musterija> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    prikazi(row.getItem());
                }
            });
            return row ;
        });
    }
    public void prikazi(Musterija m){
        prikazC prikazc = new prikazC(m);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/prikaz.fxml"));
        fxmlLoader.setController(prikazc);
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }
/*    public boolean login() throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/resources/login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root,300,200));
        stage.show();
        return false;
    }*/
    public String dajDatum(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now)+"";
    }
    public void popuniAktivne(){
        imeTbl.setCellValueFactory(new PropertyValueFactory<Musterija,String>("imePrezime"));
        brTbl.setCellValueFactory(new PropertyValueFactory<Musterija,String>("brojTelefona"));
        nazivApTbl.setCellValueFactory(new PropertyValueFactory<Musterija,String>("uredaj"));
        datumPrTbl.setCellValueFactory(new PropertyValueFactory<Musterija, String>("datumPrijema"));
        pregledanTbl.setCellValueFactory(new PropertyValueFactory<Musterija,String>("pregledan"));
        tabela.setItems(dajAktivne());
    }
    private ObservableList<Musterija> dajAktivne() {
        ObservableList<Musterija> povratni = FXCollections.observableArrayList();
        for(Musterija m:BazaDAO.getInstance().dajAktivne()){
            povratni.add(m);
        }
        return povratni;
    }
    public void dodaj(){
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/dodajMusteriju.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage= new Stage();
        primaryStage.setScene(new Scene(root,367,182));
        primaryStage.show();
        primaryStage.setOnHiding( event -> {refreshTabela();} );

    }
    public void zakljuci(){
        if(dajTrenutni()!=null) {
            if(dajTrenutni().getPregledan().equals("DA")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacija");
                alert.setHeaderText(null);
                alert.setContentText("Uređaj je već pregledan/zakljucen!");
                alert.showAndWait();
                return;
            }
            zakljuciAparat zA = new zakljuciAparat(dajTrenutni());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/zakljuciAparat.fxml"));
            fxmlLoader.setController(zA);
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root, 400, 200));
            primaryStage.show();
            primaryStage.setOnHiding(event -> {
                refreshTabela();
            });
        }
    }
    public Musterija dajTrenutni(){
        Musterija povratni;
        povratni = tabela.getSelectionModel().getSelectedItem();
        return povratni;
    }
    public void razduzi(){
        if(dajTrenutni()!=null) {
            BazaDAO.getInstance().razduzi(dajTrenutni().getBrojTelefona());
            refreshTabela();
        }
    }
    public void refreshTabela(){
        tabela.getItems().clear();
        tabela.refresh();
        popuniAktivne();
    }
}
