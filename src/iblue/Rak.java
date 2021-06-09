package iblue;

public class Rak {

    private String kode;
    private String nama;
    private String lokasi;
    private String keterangan;

    public Rak(String kode, String nama, String lokasi, String keterangan){
        this.kode = kode;
        this.nama = nama;
        this.lokasi = lokasi;
        this.keterangan = keterangan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
