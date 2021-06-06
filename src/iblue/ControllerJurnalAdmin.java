package iblue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;
import java.util.ResourceBundle;

public class ControllerJurnalAdmin implements Initializable {

    @FXML
    private Button btnInsertJurnal;

    @FXML
    private Button btnUpdateJurnal;

    @FXML
    private Button btnDeleteJurnal;

    @FXML
    private Button btnSearchJurnal;

    @FXML
    private TextField tfKode;

    @FXML
    private TextField tfJudul;

    @FXML
    private TextField tfVolume;

    @FXML
    private TextField tfTahun;

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


    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnInsertJurnal){
            insertJurnal();
        } else if (actionEvent.getSource() == btnUpdateJurnal){
            updateJurnal();
        } else if (actionEvent.getSource() == btnDeleteJurnal){
            deleteJurnal();
        } else if (actionEvent.getSource() == btnSearchJurnal){
            searchJurnal();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showJurnal();
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
                        resultSet.getString("tahun"),
                        resultSet.getInt("volume"));
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
        tJurnal.setItems(list);
    }

    private void insertJurnal(){
        String query = "INSERT INTO jurnal VALUES('" + tfKode.getText() + "','" + tfJudul.getText() + "','"  + tfTahun.getText() + "'," + tfVolume.getText() + ")";
        executeQuery(query);
        showJurnal();
    }

    private void updateJurnal(){
        String query = "UPDATE jurnal SET judul = '" + tfJudul.getText() + "', tahun = '" + tfTahun.getText() + "', volume = " + tfVolume.getText() + " WHERE kode = '" + tfKode.getText() + "';";
        executeQuery(query);
        showJurnal();
    }

    private void deleteJurnal(){
        String query = "DELETE FROM Jurnal WHERE kode = '" + tfKode.getText() + "';";
        executeQuery(query);
        showJurnal();
    }

    private void searchJurnal(){
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
