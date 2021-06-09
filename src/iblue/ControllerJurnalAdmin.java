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

public class ControllerJurnalAdmin implements Initializable {

    @FXML
    private Button btnInsertJurnal;

    @FXML
    private Button btnUpdateJurnal;

    @FXML
    private Button btnDeleteJurnal;

    @FXML
    private Button btnClearJurnal;

    @FXML
    private Button btnLogout;

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
    private TextField tfKode;

    @FXML
    private TextField tfJudul;

    @FXML
    private TextField tfVolume;

    @FXML
    private TextField tfTahun;

    @FXML
    private TextField tfKodeRak;

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

        if(actionEvent.getSource() == btnInsertJurnal){
            insertJurnal();
        } else if (actionEvent.getSource() == btnUpdateJurnal){
            updateJurnal();
        } else if (actionEvent.getSource() == btnDeleteJurnal){
            deleteJurnal();
        } else if (actionEvent.getSource() == btnClearJurnal){
            clearJurnal();
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
        showJurnal();
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

        tfJudul.textProperty().addListener((observable) -> {
            String keyword = tfJudul.getText();

            if(keyword == null || keyword.length() == 0){
                filteredJurnal.setPredicate(b -> true);
            } else {
                filteredJurnal.setPredicate(b -> b.getJudul().toLowerCase().contains(keyword.toLowerCase()));
            }
            tJurnal.setItems(filteredJurnal);
        });

    }

    private void insertJurnal(){

        String inKode = tfKode.getText();
        String inJudul = tfJudul.getText();
        String inTahun = tfTahun.getText();
        String inVolume = tfVolume.getText();
        String inKodeRak = tfKodeRak.getText();

        /* field validation */
        if (inKode.isEmpty() || inJudul.isEmpty() || inTahun.isEmpty() || inVolume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Judul, Tahun, Volume, dan Kode Rak wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }
        else if (isNotNumberOnly(inTahun) || isNotNumberOnly(inVolume)) {
            JOptionPane.showMessageDialog(null, "Kolom Tahun dan Volume hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Tahun atau Volume stok berisi nilai bukan angka!");
        }

        String query = "INSERT INTO jurnal (kode, judul, tahun, volume, kodeRak) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            /* required field */
            stmt.setString(1, inKode);
            stmt.setString(2, inJudul);
            stmt.setInt(3, Integer.parseInt(inTahun));
            stmt.setInt(4, Integer.parseInt(inVolume));
            stmt.setString(5, inKodeRak);

            /* execute query and getting how many rows affected after query execution */
            int rowsAffected = stmt.executeUpdate();

            /* showing success response */
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " jurnal berhasil ditambahkan");
                showJurnal();
                clearJurnal();
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            int duplicatePKErrorCode = 1062;

            if (errorCode == duplicatePKErrorCode) {
                JOptionPane.showMessageDialog(null, "Kode Jurnal '"+ inKode +"' sudah digunakan! Harap gunakan kode lain.");
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan data jurnal!");
            }
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }

    }

    private void updateJurnal(){

        String inKode = tfKode.getText();
        String inJudul = tfJudul.getText();
        String inTahun = tfTahun.getText();
        String inVolume = tfVolume.getText();
        String inKodeRak = tfKodeRak.getText();

        /* field validation */
        if (inKode.isEmpty() || inJudul.isEmpty() || inTahun.isEmpty() || inVolume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Judul, Tahun, Volume, dan Kode Rak wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }
        else if (isNotNumberOnly(inTahun) || isNotNumberOnly(inVolume)) {
            JOptionPane.showMessageDialog(null, "Kolom Tahun dan Volume hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Tahun atau Volume stok berisi nilai bukan angka!");
        }

        String query = "UPDATE jurnal SET judul = ?, tahun = ?, volume = ?, kodeRak = ? WHERE kode = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            /* required field */
            stmt.setString(1, inJudul);
            stmt.setInt(2, Integer.parseInt(inTahun));
            stmt.setInt(3, Integer.parseInt(inVolume));
            stmt.setString(4, inKodeRak);
            stmt.setString(5, inKode);


            /* execute query and getting how many rows affected after query execution */
            int rowsAffected = stmt.executeUpdate();

            /* showing success response */
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " jurnal berhasil diperbarui");
                showJurnal();
                clearJurnal();
            } else {
                /* if value of kodeBuku not found */
                JOptionPane.showMessageDialog(null, "Kode jurnal '" + inKode + "' tidak ditemukan! Data jurnal gagal diperbarui!");
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();

            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui data jurnal!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }

    }

    private void deleteJurnal(){

        String inKode = tfKode.getText();

        /* field validation */
        if (inKode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom kode harus terisi! Silahkan klik data jurnal yang akan dihapus terlebih dahulu.");
            throw new NullPointerException("Kolom kode masih kosong!");
        }

        String query = "DELETE FROM jurnal WHERE kode = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            /* required field */
            stmt.setString(1, inKode);

            /* confirm delete action */
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data jurnal ini?");

            if (confirmDelete == 0) {
                /* execute query and getting how many rows affected after query execution */
                int rowsAffected = stmt.executeUpdate();

                /* showing alert response */
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, rowsAffected + " jurnal berhasil dihapus");
                    showJurnal();
                    clearJurnal();
                } else {
                    /* if value of kodeBuku not found */
                    JOptionPane.showMessageDialog(null, "Kode jurnal '" + inKode + "' tidak ditemukan! Data jurnal gagal dihapus!");
                }
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();

            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data jurnal!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void clearJurnal(){
        tfKode.setText("");
        tfJudul.setText("");
        tfTahun.setText("");
        tfVolume.setText("");
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

    private void setFieldValueFromTable(){

        tJurnal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Jurnal jurnal = tJurnal.getItems().get(tJurnal.getSelectionModel().getSelectedIndex());
                tfKode.setText(jurnal.getKode());
                tfJudul.setText(jurnal.getJudul());
                tfTahun.setText(String.valueOf(jurnal.getTahun()));
                tfVolume.setText(String.valueOf(jurnal.getVolume()));
            }
        });

    }




}
