package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
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
    private DatePicker tfPublikasi;

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


    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnInsertBuku){
            insertBuku();
        } else if (actionEvent.getSource() == btnUpdateBuku){
            updateBuku();
        } else if (actionEvent.getSource() == btnDeleteBuku){
            deleteBuku();
        } else if (actionEvent.getSource() == btnSearchBuku){
            searchBuku();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBuku();
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
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM buku";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Buku buku;
            while (resultSet.next()) {
                buku = new Buku(resultSet.getString("kodeBuku"),
                        resultSet.getString("judulBuku"),
                        resultSet.getString("pengarang"),
                        resultSet.getString("penerbit"),
                        resultSet.getString("kota"),
                        resultSet.getInt("edisi"),
                        resultSet.getDate("tanggalPublikasi"),
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
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        colKota.setCellValueFactory(new PropertyValueFactory<>("kota"));
        colEdisi.setCellValueFactory(new PropertyValueFactory<>("edisi"));
        colPublikasi.setCellValueFactory(new PropertyValueFactory<>("tanggalPublikasi"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        tBuku.setItems(list);
    }

    private void insertBuku(){

        String query = "";
        
        String inKodeBuku = tfKode.getText();
        String inJudul = tfJudul.getText();
        String inPengarang = tfNamaPengarang.getText();
        String inPenerbit = tfPenerbit.getText();
        String inKota = tfKota.getText();
        String inEdisi = tfEdisi.getText();
        String inPublikasi = tfPublikasi.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String inIsbn = tfIsbn.getText();
        String inStok = tfStok.getText();

        if (inEdisi.equals("") && inIsbn.equals("")) {
            query = "INSERT INTO buku VALUES('" + inKodeBuku + "','" + inJudul + "','" +
                    inPengarang + "','" + inPenerbit + "','" + inKota + "', 0,'" +
                    inPublikasi + "', 0," + inStok + ");";
        } else if(inEdisi.equals("")){
            query = "INSERT INTO buku VALUES('" + inKodeBuku + "','" + inJudul + "','" +
                    inPengarang + "','" + inPenerbit + "','" + inKota + "', 0,'" + inPublikasi
                    + "'," + inIsbn + "," + inStok + ");";
        } else if (inIsbn.equals("")){
            query = "INSERT INTO buku VALUES('" + inKodeBuku + "','" + inJudul + "','" +
                    inPengarang + "','" + inPenerbit + "','" + inKota
                    + "'," + inEdisi + ",'" + inPublikasi + "', 0," + inStok + ");";
        } else {
            query = "INSERT INTO buku VALUES('" + inKodeBuku + "','" + inJudul + "','" +
                    inPengarang + "','" + inPenerbit + "','" + inKota + "'," +
                    inEdisi + ",'" + inPublikasi + "'," + inIsbn + "," + inStok + ");";
        }

        if(!(query.equals(""))){
            executeQuery(query);
            showBuku();
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap!");
        }

    }

    private void updateBuku(){
        String query = "UPDATE buku SET judulBuku = '" + tfJudul.getText() + "', pengarang = '" +
                tfNamaPengarang.getText() + "', penerbit = '" + tfPenerbit.getText() + "', kota = '" + tfKota.getText()
                + "', edisi = " + tfEdisi.getText() + ", tanggalPublikasi = '" + tfPublikasi.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "', isbn = " + tfIsbn.getText() + ", stok = " + tfStok.getText()
                + " WHERE kodeBuku = '" + tfKode.getText() + "';";
        executeQuery(query);
        showBuku();
    }

    private void deleteBuku(){
        String query = "DELETE FROM buku WHERE kodeBuku = '" + tfKode.getText() + "';";
        executeQuery(query);
        showBuku();
    }

    private void searchBuku(){
        String query = "SELECT * FROM buku WHERE kodeBuku LIKE '%" + tfKode.getText() + "%' AND judulBuku LIKE '%" + tfJudul.getText() + "%' AND pengarang LIKE '%" +
                tfNamaPengarang.getText() + "%' AND penerbit LIKE '%" + tfPenerbit.getText() + "%' AND kota LIKE '%" + tfKota.getText()
                +  "%';";
        executeQuerySelect(query);
        showBuku();
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

    private void executeQuerySelect(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeQuery(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
