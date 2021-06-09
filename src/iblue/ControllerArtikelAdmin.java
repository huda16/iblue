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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static iblue.ControllerBukuAdmin.isNotNumberOnly;

public class ControllerArtikelAdmin implements Initializable {

    @FXML
    private TextField tfIdArtikel;

    @FXML
    private TextField tfIdJurnal;

    @FXML
    private TextField tfNomor;

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
    private Button btnClearArtikel;

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
        } else if (actionEvent.getSource() == btnClearArtikel){
            clearArtikel();
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
        showArtikel();
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

        tfJudul.textProperty().addListener((observable) -> {
            String keyword = tfJudul.getText();

            if(keyword == null || keyword.length() == 0){
                filteredArtikel.setPredicate(b -> true);
            } else {
                filteredArtikel.setPredicate(b -> b.getJudul().toLowerCase().contains(keyword.toLowerCase()));
            }
            tArtikel.setItems(filteredArtikel);
        });

    }

    private void insertArtikel(){

        String inIdJurnal = tfIdJurnal.getText();
        String inJudul = tfJudul.getText();
        String inPengarang = tfPengarang.getText();
        String inNomor = tfNomor.getText();
        String inHalamanAwal = tfHalamanAwal.getText();
        String inHalamanAkhir = tfHalamanAkhir.getText();
        String inDoi = tfDoi.getText();
        LocalDate inDidaftarkan = tfDaftar.getValue();
        LocalDate inDireview = tfReview.getValue();
        LocalDate inDipublikasikan = tfPublikasi.getValue();

        boolean isAfterDaftar = inDireview.isAfter(inDidaftarkan);
        boolean isAfterReview = inDipublikasikan.isAfter(inDireview);
        boolean isHalamanAkhirAfter = (Integer.parseInt(inHalamanAkhir) >= Integer.parseInt(inHalamanAwal));

        if (inIdJurnal.isEmpty() || inJudul.isEmpty() || inNomor.isEmpty() || inHalamanAwal.isEmpty()
                || inHalamanAkhir.isEmpty() || inDoi.isEmpty()|| inDidaftarkan == null || inDireview == null
                || inDipublikasikan == null) {
            JOptionPane.showMessageDialog(null, "Seluruh kolom wajib terisi");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        } else if (isNotNumberOnly(inNomor) || isNotNumberOnly(inHalamanAwal) || isNotNumberOnly(inHalamanAkhir)) {
            JOptionPane.showMessageDialog(null, "Kolom Nomor, Halaman Awal,dan Halaman Akhir " +
                    "hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Nomor, Halaman Awal, atau Halaman Akhir berisi nilai bukan angka!");
        } else if (!(isAfterDaftar && isAfterReview)){
            JOptionPane.showMessageDialog(null, "Pastikan Kolom Tanggal Direview setelah Tanggal Didaftarkan"
                    + "dan Tanggal Dipublikasikan setelah Tanggal Direview!");
            throw new IllegalArgumentException("Urutan tanggal tidak benar!");
        } else if (!(isHalamanAkhirAfter)){
            JOptionPane.showMessageDialog(null, "Pastikan Kolom Halaman Akhir setelah Halaman Awal");
            throw new IllegalArgumentException("Urutan halaman tidak benar!");
        }

        String query = "INSERT INTO artikel"
                + "(idJurnal, judul, pengarang, nomor, halamanAwal, halamanAkhir, doi, tanggalDidaftarkan, tanggalDireview, tanggalDipublikasikan)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inIdJurnal);
            stmt.setString(2, inJudul);
            stmt.setString(3, inPengarang);
            stmt.setInt(4, Integer.parseInt(inNomor));
            stmt.setInt(5, Integer.parseInt(inHalamanAwal));
            stmt.setInt(6, Integer.parseInt(inHalamanAkhir));
            stmt.setString(7, inDoi);
            stmt.setDate(8, java.sql.Date.valueOf(Objects.requireNonNull(inDidaftarkan)));
            stmt.setDate(9, java.sql.Date.valueOf(Objects.requireNonNull(inDireview)));
            stmt.setDate(10, java.sql.Date.valueOf(Objects.requireNonNull(inDipublikasikan)));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " artikel berhasil ditambahkan");
                showArtikel();
                clearArtikel();
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan data artikel!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }



    }

    private void updateArtikel(){

        String inIdArtikel = tfIdArtikel.getText();
        String inIdJurnal = tfIdJurnal.getText();
        String inJudul = tfJudul.getText();
        String inPengarang = tfPengarang.getText();
        String inNomor = tfNomor.getText();
        String inHalamanAwal = tfHalamanAwal.getText();
        String inHalamanAkhir = tfHalamanAkhir.getText();
        String inDoi = tfDoi.getText();
        LocalDate inDidaftarkan = tfDaftar.getValue();
        LocalDate inDireview = tfReview.getValue();
        LocalDate inDipublikasikan = tfPublikasi.getValue();

        boolean isAfterDaftar = inDireview.isAfter(inDidaftarkan);
        boolean isAfterReview = inDipublikasikan.isAfter(inDireview);
        boolean isHalamanAkhirAfter = (Integer.parseInt(inHalamanAkhir) >= Integer.parseInt(inHalamanAwal));


        if (inIdJurnal.isEmpty() || inJudul.isEmpty() || inNomor.isEmpty() || inHalamanAwal.isEmpty()
                || inHalamanAkhir.isEmpty() || inDoi.isEmpty()|| inDidaftarkan == null || inDireview == null
                || inDipublikasikan == null) {
            JOptionPane.showMessageDialog(null, "Seluruh kolom wajib terisi");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        } else if (isNotNumberOnly(inNomor) || isNotNumberOnly(inHalamanAwal) || isNotNumberOnly(inHalamanAkhir)) {
            JOptionPane.showMessageDialog(null, "Kolom Nomor, Halaman Awal,dan Halaman Akhir " +
                    "hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Nomor, Halaman Awal, atau Halaman Akhir berisi nilai bukan angka!");
        } else if (!(isAfterDaftar && isAfterReview)){
            JOptionPane.showMessageDialog(null, "Pastikan Kolom Tanggal Direview setelah Tanggal Didaftarkan"
                    + "dan Tanggal Dipublikasikan setelah Tanggal Direview!");
            throw new IllegalArgumentException("Urutan tanggal tidak benar!");
        } else if (!(isHalamanAkhirAfter)){
            JOptionPane.showMessageDialog(null, "Pastikan Kolom Halaman Akhir setelah Halaman Awal");
            throw new IllegalArgumentException("Urutan halaman tidak benar!");
        }

        String query = "UPDATE buku SET idJurnal = ?, judul = ?, pengarang = ?, nomor = ?, halamanAwal = ?," +
                "halamanAkhir = ?, doi = ?, tanggalDidaftarkan = ?, tanggalDireview = ?, tanggalDipublikasikan = ? WHERE id = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inIdJurnal);
            stmt.setString(2, inJudul);
            stmt.setString(3, inPengarang);
            stmt.setInt(4, Integer.parseInt(inNomor));
            stmt.setInt(5, Integer.parseInt(inHalamanAwal));
            stmt.setInt(6, Integer.parseInt(inHalamanAkhir));
            stmt.setString(7, inDoi);
            stmt.setDate(8, java.sql.Date.valueOf(Objects.requireNonNull(inDidaftarkan)));
            stmt.setDate(9, java.sql.Date.valueOf(Objects.requireNonNull(inDireview)));
            stmt.setDate(10, java.sql.Date.valueOf(Objects.requireNonNull(inDipublikasikan)));
            stmt.setInt(11, Integer.parseInt(inIdArtikel));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " artikel berhasil diperbarui");
                showArtikel();
                clearArtikel();
            } else {
                JOptionPane.showMessageDialog(null, "ID artikel '" + inIdArtikel + "' tidak ditemukan! Data artikel gagal diperbarui!");
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui data artikel!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }

    }

    private void deleteArtikel(){

        String inIdArtikel = tfIdArtikel.getText();

        if (inIdArtikel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom ID Artikel harus terisi! Silahkan klik data artikel yang akan dihapus terlebih dahulu.");
            throw new NullPointerException("Kolom ID Artikel masih kosong!");
        }

        String query = "DELETE FROM buku WHERE id = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inIdArtikel);

            int confirmDelete = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data artikel ini?");

            if (confirmDelete == 0) {
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, rowsAffected + " artikel berhasil dihapus");
                    showArtikel();
                    clearArtikel();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode buku '" + inIdArtikel + "' tidak ditemukan! Data artikel gagal dihapus!");
                }
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data artikel!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void clearArtikel(){
        tfIdArtikel.setText("");
        tfIdJurnal.setText("");
        tfJudul.setText("");
        tfPengarang.setText("");
        tfNomor.setText("");
        tfHalamanAwal.setText("");
        tfHalamanAkhir.setText("");
        tfDoi.setText("");
        tfDaftar.setValue(null);
        tfReview.setValue(null);
        tfPublikasi.setValue(null);
    }

    private void setFieldValueFromTable(){

        tArtikel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Artikel artikel = tArtikel.getItems().get(tArtikel.getSelectionModel().getSelectedIndex());
                tfIdArtikel.setText(String.valueOf(artikel.getId()));
                tfIdJurnal.setText(artikel.getIdJurnal());
                tfJudul.setText(artikel.getJudul());
                tfPengarang.setText(artikel.getPengarang());
                tfNomor.setText(String.valueOf(artikel.getNomor()));
                tfHalamanAwal.setText(String.valueOf(artikel.getHalamanAwal()));
                tfHalamanAkhir.setText(String.valueOf(artikel.getHalamanAkhir()));
                tfDoi.setText(artikel.getDoi());
                tfDaftar.setValue(LocalDate.parse(String.valueOf(artikel.getTanggalDidaftarkan())));
                tfReview.setValue(LocalDate.parse(String.valueOf(artikel.getTanggalDireview())));
                tfPublikasi.setValue(LocalDate.parse(String.valueOf(artikel.getTanggalDipublikasikan())));
            }
        });

    }


}
