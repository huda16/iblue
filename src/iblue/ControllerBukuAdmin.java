package iblue;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerBukuAdmin implements Initializable {

    @FXML
    private TextField tfKode;

    @FXML
    private TextField tfJudul;

    @FXML
    private TextField tfNamaPengarang;

    @FXML
    private TextField tfPenerbit;

    @FXML
    private TextField tfKota;

    @FXML
    private TextField tfEdisi;

    @FXML
    private TextField tfPublikasi;

    @FXML
    private TextField tfIsbn;

    @FXML
    private TextField tfStok;

    @FXML
    private Button btnInsertBuku;

    @FXML
    private Button btnUpdateBuku;

    @FXML
    private Button btnDeleteBuku;

    @FXML
    private Button btnSearchBuku;

    @FXML
    private TableView<Buku> tBuku;

    @FXML
    private TableColumn<Buku, String> colKode;

    @FXML
    private TableColumn<Buku, String> colJudul;

    @FXML
    private TableColumn<Buku, String> colPengarang;

    @FXML
    private TableColumn<Buku, String> colPenerbit;

    @FXML
    private TableColumn<Buku, String> colKota;

    @FXML
    private TableColumn<Buku, Integer> colEdisi;

    @FXML
    private TableColumn<Buku, Date> colPublikasi;

    @FXML
    private TableColumn<Buku, Integer> colIsbn;

    @FXML
    private TableColumn<Buku, Integer> colStok;

    @FXML
    void handleButtonAction(ActionEvent event) {
        System.out.println("Hello!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public ObservableList<Buku> getDaftarBuku() {
        ObservableList<Buku> daftarBuku = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM buku";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Buku buku;
            while (resultSet.next()) {
                buku = new Buku(resultSet.getString("kode_buku"),
                        resultSet.getString("judul_buku"),
                        resultSet.getString("pengarang"),
                        resultSet.getString("penerbit"),
                        resultSet.getString("kota"),
                        resultSet.getInt("edisi"),
                        resultSet.getDate("tanggal_publikasi"),
                        resultSet.getInt("isbn"),
                        resultSet.getInt("stok"));
                daftarBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarBuku;
    }

    public void showBuku(){
        ObservableList<Buku> list = getDaftarBuku();
        colKode.setCellValueFactory(new PropertyValueFactory<Buku, String>("kode_buku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<Buku, String>("judul_buku"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<Buku, String>("pengarang"));
        colPenerbit.setCellValueFactory(new PropertyValueFactory<Buku, String>("penerbit"));
        colKota.setCellValueFactory(new PropertyValueFactory<Buku, String>("kota"));
        colEdisi.setCellValueFactory(new PropertyValueFactory<Buku, Integer>("edisi"));
        colPublikasi.setCellValueFactory(new PropertyValueFactory<Buku, Date>("tanggal_publikasi"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<Buku, Integer>("isbn"));
        colStok.setCellValueFactory(new PropertyValueFactory<Buku, Integer>("stok"));

        tBuku.setItems(list);
    }

}
