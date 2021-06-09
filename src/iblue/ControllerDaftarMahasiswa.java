package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerDaftarMahasiswa implements Initializable {

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
    private TableView<Mahasiswa> tMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, Integer> colNim;

    @FXML
    private TableColumn<Mahasiswa, String> colNama;

    @FXML
    private TableColumn<Mahasiswa, String> colEmail;

    @FXML
    private TableColumn<Mahasiswa, String> colProdi;

    @FXML
    private TableColumn<Mahasiswa, String> colTelepon;

    @FXML
    private TableColumn<Mahasiswa, String> colAlamat;

    @FXML
    private TextField tfKeyword;

    String query = "";

    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {


        if (actionEvent.getSource() == btnDaftarJurnal) {
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
        }else if (actionEvent.getSource() == btnDaftarPeminjaman) {
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
        showMahasiswa();
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

    public ObservableList<Mahasiswa> getDaftarMahasiswa() {
        ObservableList<Mahasiswa> daftarMahasiswa = FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM mahasiswa";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Mahasiswa mahasiswa;
            while (resultSet.next()) {
                mahasiswa = new Mahasiswa(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("prodi"),
                        resultSet.getString("telepon"),
                        resultSet.getString("alamat"),
                        resultSet.getInt("aktif"));
                daftarMahasiswa.add(mahasiswa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarMahasiswa;
    }

    public void showMahasiswa(){

        ObservableList<Mahasiswa> list = getDaftarMahasiswa();
        colNim.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colProdi.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        tMahasiswa.setItems(list);

        tMahasiswa.setItems(list);

        FilteredList<Mahasiswa> filteredMahasiswa = new FilteredList(getDaftarMahasiswa(), b -> true);

        tfKeyword.textProperty().addListener((observable) -> {
            String keyword = tfKeyword.getText();

            if(keyword == null || keyword.length() == 0){
                filteredMahasiswa.setPredicate(b -> true);
            } else {
                filteredMahasiswa.setPredicate(b -> String.valueOf(b.getId()).toLowerCase().contains(keyword.toLowerCase()));
            }
            tMahasiswa.setItems(filteredMahasiswa);
        });
    }



}
