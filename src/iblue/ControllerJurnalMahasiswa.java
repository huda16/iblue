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

public class ControllerJurnalMahasiswa implements Initializable {

    @FXML
    private Button btnDaftarBuku;

    @FXML
    private Button btnDaftarJurnal;

    @FXML
    private Button btnDaftarArtikel;

    @FXML
    private Button btnDaftarRak;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField tfKeyword;

    @FXML
    private TableView<Jurnal> tJurnal;

    @FXML
    private TableColumn<Jurnal, String> colKode;

    @FXML
    private TableColumn<Jurnal, String> colJudul;

    @FXML
    private TableColumn<Jurnal, Year> colTahun;

    @FXML
    private TableColumn<Jurnal, Integer> colVolume;

    @FXML
    private TableColumn<Jurnal, String> colKodeRak;

    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {


        if (actionEvent.getSource() == btnDaftarJurnal) {
            try {
                //add you loading or delays - ;-)
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarJurnalMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarArtikel) {
            try {
                //add you loading or delays - ;-)
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarArtikelMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarBuku) {
            try {
                //add you loading or delays - ;-)
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarBukuMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarRak) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarRakMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnLogout) {
            try {
                //add you loading or delays - ;-)
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
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
        showJurnal();
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

    public ObservableList<Jurnal> getDaftarJurnal() {
        ObservableList<Jurnal> daftarJurnal = FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM jurnal";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Jurnal jurnal;
            while (resultSet.next()) {
                jurnal = new Jurnal(resultSet.getString("kode"),
                        resultSet.getString("judul"),
                        resultSet.getInt("tahun"),
                        resultSet.getInt("volume"),
                        resultSet.getString("kodeRak"));
                daftarJurnal.add(jurnal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarJurnal;
    }

    public void showJurnal(){
        ObservableList<Jurnal> list = getDaftarJurnal();
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        colKodeRak.setCellValueFactory(new PropertyValueFactory<>("kodeRak"));
        tJurnal.setItems(list);

        FilteredList<Jurnal> filteredJurnal = new FilteredList(getDaftarJurnal(), b -> true);

        tfKeyword.textProperty().addListener((observable) -> {
            String keyword = tfKeyword.getText();

            if(keyword == null || keyword.length() == 0){
                filteredJurnal.setPredicate(b -> true);
            } else {
                filteredJurnal.setPredicate(b -> b.getJudul().toLowerCase().contains(keyword.toLowerCase()));
            }
            tJurnal.setItems(filteredJurnal);
        });

    }




}
