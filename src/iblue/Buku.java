package iblue;

import java.util.Date;

public class Buku {

    private String kodeBuku;
    private String judulBuku;
    private String pengarang;
    private String penerbit;
    private String kota;
    private int edisi;
    private Date tanggalPublikasi;
    private int isbn;
    private int stok;

    public Buku(String kodeBuku, String judulBuku, String pengarang, String penerbit, String kota, int edisi, Date tanggalPublikasi, int isbn, int stok) {
        this.kodeBuku = kodeBuku;
        this.judulBuku = judulBuku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.kota = kota;
        this.edisi = edisi;
        this.tanggalPublikasi = tanggalPublikasi;
        this.isbn = isbn;
        this.stok = stok;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
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

    public Date getTanggalPublikasi() {
        return tanggalPublikasi;
    }

    public void setTanggalPublikasi(Date tanggalPublikasi) {
        this.tanggalPublikasi = tanggalPublikasi;
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
