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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Year;
import java.util.Objects;
import java.util.ResourceBundle;

import static iblue.ControllerBukuAdmin.isNotNumberOnly;

public class ControllerRakAdmin implements Initializable {

    @FXML
    private Button btnDaftarMahasiswa;

    @FXML
    private Button btnDaftarPeminjaman;

    @FXML
    private Button btnDaftarRak;

    @FXML
    private Button btnDaftarBuku;

    @FXML
    private Button btnDaftarJurnal;

    @FXML
    private Button btnDaftarArtikel;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnInsertRak;

    @FXML
    private Button btnUpdateRak;

    @FXML
    private Button btnDeleteRak;

    @FXML
    private Button btnClearRak;

    @FXML
    private TextField tfKode;

    @FXML
    private TextField tfNama;

    @FXML
    private Label btnRak;

    @FXML
    private TextField tfKeterangan;

    @FXML
    private TextField tfLokasi;

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

        if(actionEvent.getSource() == btnInsertRak){
            insertRak();
        } else if (actionEvent.getSource() == btnUpdateRak){
            updateRak();
        } else if (actionEvent.getSource() == btnDeleteRak){
            deleteRak();
        } else if (actionEvent.getSource() == btnClearRak){
            clearRak();
        } else if (actionEvent.getSource() == btnDaftarMahasiswa) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarMahasiswa.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarPeminjaman) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("transaksiAdmin.fxml")));
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarRakAdmin.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnDaftarJurnal) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarJurnalAdmin.fxml")));
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarArtikelAdmin.fxml")));
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DaftarBukuAdmin.fxml")));
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("loginAdmin.fxml")));
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
        setFieldValueFromTable();
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

        tfKode.textProperty().addListener((observable) -> {
            String keyword = tfKode.getText();

            if(keyword == null || keyword.length() == 0){
                filteredRak.setPredicate(b -> true);
            } else {
                filteredRak.setPredicate(b -> b.getKode().toLowerCase().contains(keyword.toLowerCase()));
            }
            tRak.setItems(filteredRak);
        });

    }

    private void insertRak(){

        String inKode = tfKode.getText();
        String inNama = tfNama.getText();
        String inLokasi = tfLokasi.getText();
        String inKeterangan = tfKeterangan.getText();

        if (inKode.isEmpty() || inNama.isEmpty() || inLokasi.isEmpty() || inKeterangan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Nama, Lokasi, dan Keterangan wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }

        String query = "INSERT INTO Rak (kode, nama, lokasi, keterangan) VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inKode);
            stmt.setString(2, inNama);
            stmt.setString(3, inLokasi);
            stmt.setString(4, inKeterangan);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " Rak berhasil ditambahkan");
                showRak();
                clearRak();
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            int duplicatePKErrorCode = 1062;

            if (errorCode == duplicatePKErrorCode) {
                JOptionPane.showMessageDialog(null, "Kode rak '"+ inKode +"' sudah digunakan! Harap gunakan kode lain.");
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan data rak!");
            }
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }

    }

    private void updateRak(){

        String inKode = tfKode.getText();
        String inNama = tfNama.getText();
        String inLokasi = tfLokasi.getText();
        String inKeterangan = tfKeterangan.getText();

        if (inKode.isEmpty() || inNama.isEmpty() || inLokasi.isEmpty() || inKeterangan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Nama, Lokasi, dan Keterangan wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }

        String query = "UPDATE rak SET nama = ?, lokasi = ?, keterangan = ? WHERE kode = ?";

        try {

            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inNama);
            stmt.setString(2, inLokasi);
            stmt.setString(3, inKeterangan);
            stmt.setString(4, inKode);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " Rak berhasil diperbarui");
                showRak();
                clearRak();
            } else {
                JOptionPane.showMessageDialog(null, "Kode rak '" + inKode + "' tidak ditemukan! Data rak gagal diperbarui!");
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();

            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui data rak!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }

    }

    private void deleteRak(){

        String inKode = tfKode.getText();

        if (inKode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom kode harus terisi! Silahkan klik data rak yang akan dihapus terlebih dahulu.");
            throw new NullPointerException("Kolom kode masih kosong!");
        }

        try {
            Statement statement = getConnection().createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS = 0");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String query = "DELETE FROM rak WHERE kode = ?";

        try {

            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inKode);

            int confirmDelete = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data Rak ini?");

            if (confirmDelete == 0) {

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, rowsAffected + " Rak berhasil dihapus");
                    showRak();
                    clearRak();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode rak '" + inKode + "' tidak ditemukan! Data rak gagal dihapus!");
                }
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data rak!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void clearRak(){
        tfKode.setText("");
        tfNama.setText("");
        tfLokasi.setText("");
        tfKeterangan.setText("");
    }

    private void setFieldValueFromTable(){

        tRak.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Rak rak = tRak.getItems().get(tRak.getSelectionModel().getSelectedIndex());
                tfKode.setText(rak.getKode());
                tfNama.setText(rak.getNama());
                tfLokasi.setText(rak.getLokasi());
                tfKeterangan.setText(rak.getKeterangan());
            }
        });

    }




}
