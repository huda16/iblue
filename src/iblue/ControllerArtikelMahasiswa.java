package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerArtikelMahasiswa implements Initializable {

    @FXML
    private TextField tfKeyword;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnDaftarBuku;

    @FXML
    private Button btnDaftarJurnal;

    @FXML
    private Button btnDaftarArtikel;

    @FXML
    private TableView<Artikel> tArtikel;

    @FXML
    private TableColumn<Artikel, Integer> colIdArtikel;

    @FXML
    private TableColumn<Artikel, String> colIdJurnal;

    @FXML
    private TableColumn<Artikel, Integer> colNomor;

    @FXML
    private TableColumn<Artikel, String> colJudulArtikel;

    @FXML
    private TableColumn<Artikel, String> colPengarang;

    @FXML
    private TableColumn<Artikel, Integer> colAwal;

    @FXML
    private TableColumn<Artikel, Integer> colAkhir;

    @FXML
    private TableColumn<Artikel, String> colDoi;

    @FXML
    private TableColumn<Artikel, Date> colDidaftarkan;

    @FXML
    private TableColumn<Artikel, Date> colDireview;

    @FXML
    private TableColumn<Artikel, Date> colDipublikasikan;

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
        showArtikel();
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

    public ObservableList<Artikel> getDaftarArtikel() {
        ObservableList<Artikel> daftarArtikel = FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM artikel";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Artikel artikel;
            while (resultSet.next()) {
                artikel = new Artikel(resultSet.getInt("id"),
                        resultSet.getString("idJurnal"),
                        resultSet.getString("judul"),
                        resultSet.getString("pengarang"),
                        resultSet.getInt("nomor"),
                        resultSet.getInt("halamanAwal"),
                        resultSet.getInt("halamanAkhir"),
                        resultSet.getString("doi"),
                        resultSet.getDate("tanggalDidaftarkan"),
                        resultSet.getDate("tanggalDireview"),
                        resultSet.getDate("tanggalDipublikasikan"));
                daftarArtikel.add(artikel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarArtikel;
    }

    public void showArtikel(){
        ObservableList<Artikel> list = getDaftarArtikel();
        colIdArtikel.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdJurnal.setCellValueFactory(new PropertyValueFactory<>("idJurnal"));
        colJudulArtikel.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        colAwal.setCellValueFactory(new PropertyValueFactory<>("halamanAwal"));
        colAkhir.setCellValueFactory(new PropertyValueFactory<>("halamanAkhir"));
        colDoi.setCellValueFactory(new PropertyValueFactory<>("doi"));
        colDidaftarkan.setCellValueFactory(new PropertyValueFactory<>("tanggalDidaftarkan"));
        colDireview.setCellValueFactory(new PropertyValueFactory<>("tanggalDireview"));
        colDipublikasikan.setCellValueFactory(new PropertyValueFactory<>("tanggalDipublikasikan"));
        tArtikel.setItems(list);

        FilteredList<Artikel> filteredArtikel = new FilteredList(getDaftarArtikel(), b -> true);

        tfKeyword.textProperty().addListener((observable) -> {
            String keyword = tfKeyword.getText();

            if(keyword == null || keyword.length() == 0){
                filteredArtikel.setPredicate(b -> true);
            } else {
                filteredArtikel.setPredicate(b -> b.getJudul().toLowerCase().contains(keyword));
            }
            tArtikel.setItems(filteredArtikel);
        });

    }


}
