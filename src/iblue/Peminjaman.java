package iblue;

import java.awt.*;
import java.util.Date;

public class Peminjaman {

    private int id;
    private int idPeminjam;
    private String idBuku;
    private Date tanggalPinjam;
    private Date batasTanggal;
    private Date tanggalKembali;

    public Peminjaman(int id, int idPeminjam, String idBuku, Date tanggalPinjam, Date batasTanggal,
            Date tanggalKembali) {
        this.id = id;
        this.idPeminjam = idPeminjam;
        this.idBuku = idBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.batasTanggal = batasTanggal;
        this.tanggalKembali = tanggalKembali;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(int idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getBatasTanggal() {
        return batasTanggal;
    }

    public void setBatasTanggal(Date batasTanggal) {
        this.batasTanggal = batasTanggal;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
}