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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ControllerTransaksiAdmin implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private Label lblTgl;

    @FXML
    private TextField idPeminjam;

    @FXML
    private TextField idBuku;

    @FXML
    private TextField idTransaksi;

    @FXML
    private Button btnPinjam;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnUpdate;

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
    private TableView<Peminjaman> tPeminjaman;

    @FXML
    private TableColumn<Peminjaman, Integer> colId;

    @FXML
    private TableColumn<Peminjaman, Integer> colPeminjam;

    @FXML
    private TableColumn<Peminjaman, String> colBuku;

    @FXML
    private TableColumn<Peminjaman, LocalDateTime> colTglPinjam;

    @FXML
    private TableColumn<Peminjaman, LocalDateTime> colBatasTanggal;

    @FXML
    private TableColumn<Peminjaman, LocalDateTime> colTglKembali;

    @FXML
    private TableColumn colAksi;

    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnPinjam) {
            pinjamBuku();
        } else if (actionEvent.getSource() == btnUpdate) {
            updateTransaksi();
        } else if (actionEvent.getSource() == btnClear) {
            bersih();
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
        showPeminjaman();
        setFieldValueFromTable();
    }

    public Connection getConnection() {
        Connection conn;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan", "root", "");
            return conn;
        } catch (Exception e) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }

    public ObservableList<Peminjaman> getDaftarPeminjaman() {
        ObservableList<Peminjaman> daftarPeminjaman = FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM transaksi";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Peminjaman peminjaman;
            while (resultSet.next()) {
                peminjaman = new Peminjaman(resultSet.getInt("id"), resultSet.getInt("idPeminjam"),
                        resultSet.getString("idBuku"), resultSet.getDate("tanggalPinjam"),
                        resultSet.getDate("batasTanggal"), resultSet.getDate("tanggalKembali"));
                daftarPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarPeminjaman;
    }

    public void showPeminjaman() {
        ObservableList<Peminjaman> list = getDaftarPeminjaman();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPeminjam.setCellValueFactory(new PropertyValueFactory<>("idPeminjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        colTglPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colBatasTanggal.setCellValueFactory(new PropertyValueFactory<>("batasTanggal"));
        colTglKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        tPeminjaman.setItems(list);

        FilteredList<Peminjaman> filteredJurnal = new FilteredList(getDaftarPeminjaman(), b -> true);

        idPeminjam.textProperty().addListener((observable) -> {
            String keyword = idPeminjam.getText();

            if(keyword == null || keyword.length() == 0){
                filteredJurnal.setPredicate(b -> true);
            } else {
                filteredJurnal.setPredicate(b -> String.valueOf(b.getIdPeminjam()).toLowerCase().contains(keyword));
            }
            tPeminjaman.setItems(filteredJurnal);
        });

    }

    private void pinjamBuku() {
        String inIdPeminjam = idPeminjam.getText();
        String inIdBuku = idBuku.getText();
        String query = "CALL add_transaction(" + inIdPeminjam + ",'" + inIdBuku + "')";
        executeQuery(query);
        showPeminjaman();
        bersih();
    }

    private void setFieldValueFromTable() {

        tPeminjaman.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Peminjaman peminjaman = tPeminjaman.getItems().get(tPeminjaman.getSelectionModel().getSelectedIndex());
                idTransaksi.setText(String.valueOf(peminjaman.getId()));
                lblTgl.setText(String.valueOf(LocalDate.parse(String.valueOf(peminjaman.getTanggalKembali()))));
            }
        });

    }

    private void updateTransaksi() {
        String inIdTransaksi = idTransaksi.getText();
        String inTanggalKembali = lblTgl.getText();
        if(inTanggalKembali.equals("")){
            String query = "CALL update_transaction(" + inIdTransaksi + ")";
            executeQuery(query);
        }
        showPeminjaman();
        bersih();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void bersih() {
        idPeminjam.setText("");
        idBuku.setText("");
        lblTgl.setText("");
    }
}