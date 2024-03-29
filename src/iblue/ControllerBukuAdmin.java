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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextField tfKodeRak;

    @FXML
    private Button btnInsertBuku;

    @FXML
    private Button btnUpdateBuku;

    @FXML
    private Button btnDeleteBuku;

    @FXML
    private Button btnClearBuku;

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
    private TableColumn<Buku, String> colKodeRak;

    public static boolean isNumberOnly(String s) {
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(s);
        
        return matcher.find();
    }

    public static boolean isNotNumberOnly(String s) {
        return !isNumberOnly(s);
    }

    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnInsertBuku){
            try {
                insertBuku();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        } else if (actionEvent.getSource() == btnUpdateBuku){
            try {
                updateBuku();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        } else if (actionEvent.getSource() == btnDeleteBuku){
            try {
                deleteBuku();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        } else if (actionEvent.getSource() == btnClearBuku){
            clearBuku();
        } else if (actionEvent.getSource() == btnDaftarMahasiswa) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DaftarMahasiswa.fxml"))));
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
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("transaksiAdmin.fxml"))));
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
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DaftarJurnalAdmin.fxml"))));
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
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DaftarArtikelAdmin.fxml"))));
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
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DaftarBukuAdmin.fxml"))));
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
                Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginAdmin.fxml"))));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBuku();
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
                        resultSet.getInt("stok"),
                        resultSet.getString("kodeRak"));
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
        colKodeRak.setCellValueFactory(new PropertyValueFactory<>("kodeRak"));

        tBuku.setItems(list);

        FilteredList<Buku> filteredBuku = new FilteredList(getDaftarBuku(), b -> true);

        tfJudul.textProperty().addListener((observable) -> {
            String keyword = tfJudul.getText();

            if(keyword == null || keyword.length() == 0){
                filteredBuku.setPredicate(b -> true);
            } else {
                filteredBuku.setPredicate(b -> b.getJudulBuku().toLowerCase().contains(keyword.toLowerCase()));
            }
            tBuku.setItems(filteredBuku);
        });
    }

    private void insertBuku() {

        String inKodeBuku = tfKode.getText();
        String inJudul = tfJudul.getText();
        String inPengarang = tfNamaPengarang.getText();
        String inPenerbit = tfPenerbit.getText();
        String inKota = tfKota.getText();
        String inEdisi = tfEdisi.getText();
        LocalDate inPublikasi = tfPublikasi.getValue();
        String inIsbn = tfIsbn.getText();
        String inStok = tfStok.getText();
        String inKodeRak = tfKodeRak.getText();


        if (inKodeBuku.isEmpty() || inJudul.isEmpty() || inPengarang.isEmpty() || inPenerbit.isEmpty()
                || inKota.isEmpty() || inPublikasi == null || inStok.isEmpty() || inKodeRak.isEmpty()
        ) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Judul, Pengarang, Penerbit, " +
                    "Kota, Tanggal Publikasi, Stok, dan Kode Rak wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }
        else if (isNotNumberOnly(inEdisi) || isNotNumberOnly(inIsbn) || isNotNumberOnly(inStok)) {
            JOptionPane.showMessageDialog(null, "Kolom Edisi, ISBN, dan Stok hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Edisi, ISBN, atau stok berisi nilai bukan angka!");
        }

        String query = "INSERT INTO buku"
                + "(kodeBuku, judulBuku, pengarang, penerbit, kota, edisi, tanggalPublikasi, isbn, stok, kodeRak)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inKodeBuku);
            stmt.setString(2, inJudul);
            stmt.setString(3, inPengarang);
            stmt.setString(4, inPenerbit);
            stmt.setString(5, inKota);
            stmt.setDate(7, java.sql.Date.valueOf(Objects.requireNonNull(inPublikasi)));
            stmt.setInt(9, Integer.parseInt(inStok));
            stmt.setString(10, inKodeRak);

            if (!inEdisi.isEmpty()) {
                stmt.setInt(6, Integer.parseInt(inEdisi));
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            if (!inIsbn.isEmpty()) {
                stmt.setInt(8, Integer.parseInt(inIsbn));
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " buku berhasil ditambahkan");
                showBuku();
                clearBuku();
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            int duplicatePKErrorCode = 1062;

            if (errorCode == duplicatePKErrorCode) {
                JOptionPane.showMessageDialog(null, "Kode Buku '"+ inKodeBuku +"' sudah digunakan! Harap gunakan kode lain.");
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan data buku!");
            }
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void updateBuku() {

        String inKodeBuku = tfKode.getText();
        String inJudul = tfJudul.getText();
        String inPengarang = tfNamaPengarang.getText();
        String inPenerbit = tfPenerbit.getText();
        String inKota = tfKota.getText();
        String inEdisi = tfEdisi.getText();
        LocalDate inPublikasi = tfPublikasi.getValue();
        String inIsbn = tfIsbn.getText();
        String inStok = tfStok.getText();
        String inKodeRak = tfKodeRak.getText();

        if (inKodeBuku.isEmpty() || inJudul.isEmpty() || inPengarang.isEmpty() || inPenerbit.isEmpty()
                || inKota.isEmpty() || inPublikasi == null || inStok.isEmpty() || inKodeRak.isEmpty()
        ) {
            JOptionPane.showMessageDialog(null, "Kolom Kode, Judul, Pengarang, Penerbit, " +
                    "Kota, Tanggal Publikasi, Stok, dan Kode Rak wajib diisi!");
            throw new NullPointerException("Kolom yang wajib diisi masih ada yang kosong!");
        }
        else if (isNotNumberOnly(inEdisi) || isNotNumberOnly(inIsbn) || isNotNumberOnly(inStok)) {
            JOptionPane.showMessageDialog(null, "Kolom Edisi, ISBN, dan Stok hanya boleh diisi dengan angka!");
            throw new NumberFormatException("Kolom Edisi, ISBN, atau stok berisi nilai bukan angka!");
        }

        String query = "UPDATE buku SET judulBuku = ?, pengarang = ?, penerbit = ?, kota = ?, edisi = ?," +
                "tanggalPublikasi = ?, isbn = ?, stok = ?, kodeRak = ? WHERE kodeBuku = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inJudul);
            stmt.setString(2, inPengarang);
            stmt.setString(3, inPenerbit);
            stmt.setString(4, inKota);
            stmt.setDate(6, java.sql.Date.valueOf(Objects.requireNonNull(inPublikasi)));
            stmt.setInt(8, Integer.parseInt(inStok));
            stmt.setString(9, inKodeRak);
            stmt.setString(10, inKodeBuku);

            if (!inEdisi.isEmpty()) {
                stmt.setInt(5, Integer.parseInt(inEdisi));
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            if (!inIsbn.isEmpty()) {
                stmt.setInt(7, Integer.parseInt(inIsbn));
            } else {
                stmt.setNull(7, Types.INTEGER);
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, rowsAffected + " buku berhasil diperbarui");
                showBuku();
                clearBuku();
            } else {
                JOptionPane.showMessageDialog(null, "Kode buku '" + inKodeBuku + "' tidak ditemukan! Data buku gagal diperbarui!");
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui data buku!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void deleteBuku() {

        String inKodeBuku = tfKode.getText();


        if (inKodeBuku.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kolom kode harus terisi! Silahkan klik data buku yang akan dihapus terlebih dahulu.");
            throw new NullPointerException("Kolom kode masih kosong!");
        }

        String query = "DELETE FROM buku WHERE kodeBuku = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);

            stmt.setString(1, inKodeBuku);

            int confirmDelete = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data buku ini?");

            if (confirmDelete == 0) {

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, rowsAffected + " buku berhasil dihapus");
                    showBuku();
                    clearBuku();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode buku '" + inKodeBuku + "' tidak ditemukan! Data buku gagal dihapus!");
                }
            }

        } catch (SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();

            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data buku!");
            System.err.println("MYSQL Error " + errorCode + ": " + sqlException.getMessage());
        }
    }

    private void clearBuku(){
        tfKode.setText("");
        tfJudul.setText("");
        tfNamaPengarang.setText("");
        tfPenerbit.setText("");
        tfKota.setText("");
        tfEdisi.setText("");
        tfPublikasi.setValue(null);
        tfIsbn.setText("");
        tfStok.setText("");
    }

    private void setFieldValueFromTable(){

        tBuku.setOnMouseClicked(mouseEvent -> {
            Buku buku = tBuku.getItems().get(tBuku.getSelectionModel().getSelectedIndex());
            tfKode.setText(buku.getKodeBuku());
            tfJudul.setText(buku.getJudulBuku());
            tfNamaPengarang.setText(buku.getPengarang());
            tfPenerbit.setText(buku.getPenerbit());
            tfKota.setText(buku.getKota());
            tfEdisi.setText(String.valueOf(buku.getEdisi()));
            tfPublikasi.setValue(LocalDate.parse(String.valueOf(buku.getTanggalPublikasi())));
            tfIsbn.setText(String.valueOf(buku.getIsbn()));
            tfStok.setText(String.valueOf(buku.getStok()));
        });

    }


}
