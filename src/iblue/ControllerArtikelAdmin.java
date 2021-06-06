package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerArtikelAdmin implements Initializable {

    @FXML
    private TextField tfIdJurnal;

    @FXML
    private TextField tfJudul;

    @FXML
    private TextField tfPengarang;

    @FXML
    private TextField tfHalamanAwal;

    @FXML
    private TextField tfHalamanAkhir;

    @FXML
    private DatePicker tfDaftar;

    @FXML
    private DatePicker tfReview;

    @FXML
    private DatePicker tfPublikasi;

    @FXML
    private TextField tfDoi;

    @FXML
    private Button btnInsertArtikel;

    @FXML
    private Button btnUpdateArtikel;

    @FXML
    private Button btnDeleteArtikel;

    @FXML
    private Button btnSearchArtikel;

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

        if(actionEvent.getSource() == btnInsertArtikel){
            insertArtikel();
        } else if (actionEvent.getSource() == btnUpdateArtikel){
            updateArtikel();
        } else if (actionEvent.getSource() == btnDeleteArtikel){
            deleteArtikel();
        } else if (actionEvent.getSource() == btnSearchArtikel){
            searchArtikel();
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
        colDireview.setCellValueFactory(new PropertyValueFactory<>("tanggalDipublikasikan"));
        colDipublikasikan.setCellValueFactory(new PropertyValueFactory<>("tanggalDipublikasikan"));
        tArtikel.setItems(list);
    }

    private void insertArtikel(){
//        String query = "INSERT INTO buku VALUES('" + tfKode.getText() + "','" + tfJudul.getText() + "','" +
//                tfNamaPengarang.getText() + "','" + tfPenerbit.getText() + "','" + tfKota.getText()
//                + "'," + tfEdisi.getText() + ",'" + tfPublikasi.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//                + "'," + tfIsbn.getText() + "," + tfStok.getText() + ");";
//        executeQuery(query);
//        showArtikel();
    }

    private void updateArtikel(){
//        String query = "UPDATE buku SET judulBuku = '" + tfJudul.getText() + "', pengarang = '" +
//                tfNamaPengarang.getText() + "', penerbit = '" + tfPenerbit.getText() + "', kota = '" + tfKota.getText()
//                + "', edisi = " + tfEdisi.getText() + ", tanggalPublikasi = '" + tfPublikasi.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//                + "', isbn = " + tfIsbn.getText() + ", stok = " + tfStok.getText()
//                + " WHERE kodeBuku = '" + tfKode.getText() + "';";
//        executeQuery(query);
//        showArtikel();
    }

    private void deleteArtikel(){
//        String query = "DELETE FROM buku WHERE kodeBuku = '" + tfIdJurnal.getText() + "';";
//        executeQuery(query);
//        showArtikel();
    }

    private void searchArtikel(){
//        String query = "SELECT * FROM buku WHERE kodeBuku LIKE '%" + tfKode.getText() + "%' AND judulBuku LIKE '%" + tfJudul.getText() + "%' AND pengarang LIKE '%" +
//                tfNamaPengarang.getText() + "%' AND penerbit LIKE '%" + tfPenerbit.getText() + "%' AND kota LIKE '%" + tfKota.getText()
//                +  "%';";
//        executeQuerySelect(query);
//        showArtikel();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
