package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerRakMahasiswa implements Initializable {

    @FXML
    private Button btnDaftarBuku;

    @FXML
    private Button btnDaftarJurnal;

    @FXML
    private Button btnDaftarArtikel;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField tfKeyword;

    @FXML
    private TableView<Rak> tRak;

    @FXML
    private TableColumn<Rak, String> colKode;

    @FXML
    private TableColumn<Rak, String> colNama;

    @FXML
    private TableColumn<Rak, String> colLokasi;

    @FXML
    private TableColumn<Rak, String> colKeterangan;


    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {


        if (actionEvent.getSource() == btnDaftarJurnal) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarJurnalMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarArtikel) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarArtikelMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarBuku) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarBukuMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnLogout) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("loginMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showRak();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan", "root", "");
            return conn;
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }

    public ObservableList<Rak> getDaftarRak() {
        ObservableList<Rak> daftarRak = FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM rak";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Rak rak;
            while (resultSet.next()) {
                rak = new Rak(resultSet.getString("kode"),
                        resultSet.getString("nama"),
                        resultSet.getString("lokasi"),
                        resultSet.getString("keterangan"));
                daftarRak.add(rak);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarRak;
    }

    public void showRak(){
        ObservableList<Rak> list = getDaftarRak();
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        tRak.setItems(list);

        FilteredList<Rak> filteredRak = new FilteredList(getDaftarRak(), b -> true);

        tfKeyword.textProperty().addListener((observable) -> {
            String keyword = tfKeyword.getText();

            if(keyword == null || keyword.length() == 0){
                filteredRak.setPredicate(b -> true);
            } else {
                filteredRak.setPredicate(b -> b.getKode().toLowerCase().contains(keyword.toLowerCase()));
            }
            tRak.setItems(filteredRak);
        });

    }

}
