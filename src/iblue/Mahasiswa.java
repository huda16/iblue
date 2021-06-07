package iblue;

public class Mahasiswa {

    private int id;
    private String name;
    private String email;
    private String password;
    private String prodi;
    private String telepon;
    private String alamat;
    private int aktif;

    public Mahasiswa(int id, String name, String email, String password, String prodi, String telepon, String alamat, int aktif){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.prodi = prodi;
        this.telepon = telepon;
        this.alamat = alamat;
        this.aktif = aktif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getAktif() {
        return aktif;
    }

    public void setAktif(int aktif) {
        this.aktif = aktif;
    }
}
