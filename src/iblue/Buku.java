package iblue;

import java.util.Date;

public class Buku {

    private String kode_buku;
    private String judul_buku;
    private String pengarang;
    private String penerbit;
    private String kota;
    private int edisi;
    private Date tanggal_publikasi;
    private int isbn;
    private int stok;

    public Buku(String kode_buku, String judul_buku, String pengarang, String penerbit, String kota, int edisi, Date tanggal_publikasi, int isbn, int stok) {
        this.kode_buku = kode_buku;
        this.judul_buku = judul_buku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.kota = kota;
        this.edisi = edisi;
        this.tanggal_publikasi = tanggal_publikasi;
        this.isbn = isbn;
        this.stok = stok;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public int getEdisi() {
        return edisi;
    }

    public void setEdisi(int edisi) {
        this.edisi = edisi;
    }

    public Date getTanggal_publikasi() {
        return tanggal_publikasi;
    }

    public void setTanggal_publikasi(Date tanggal_publikasi) {
        this.tanggal_publikasi = tanggal_publikasi;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

}
