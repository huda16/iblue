package iblue;

import java.util.Date;

public class Artikel {

    private int id;
    private String idJurnal;
    private String judul;
    private String pengarang;
    private int nomor;
    private int halamanAwal;
    private int halamanAkhir;
    private String doi;
    private Date tanggalDidaftarkan;
    private Date tanggalDireview;
    private Date tanggalDipublikasikan;

    public Artikel(int id, String idJurnal, String judul, String pengarang, int nomor, int halamanAwal, int halamanAkhir, String doi, Date tanggalDidaftarkan, Date tanggalDireview, Date tanggalDipublikasikan){
        this.id = id;
        this.idJurnal =idJurnal;
        this.judul = judul;
        this.pengarang = pengarang;
        this.nomor = nomor;
        this.halamanAwal = halamanAwal;
        this.halamanAkhir = halamanAkhir;
        this.doi = doi;
        this.tanggalDidaftarkan = tanggalDidaftarkan;
        this.tanggalDireview = tanggalDireview;
        this.tanggalDipublikasikan = tanggalDipublikasikan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdJurnal() {
        return idJurnal;
    }

    public void setIdJurnal(String idJurnal) {
        this.idJurnal = idJurnal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public int getHalamanAwal() {
        return halamanAwal;
    }

    public void setHalamanAwal(int halamanAwal) {
        this.halamanAwal = halamanAwal;
    }

    public int getHalamanAkhir() {
        return halamanAkhir;
    }

    public void setHalamanAkhir(int halamanAkhir) {
        this.halamanAkhir = halamanAkhir;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Date getTanggalDidaftarkan() {
        return tanggalDidaftarkan;
    }

    public void setTanggalDidaftarkan(Date tanggalDidaftarkan) {
        this.tanggalDidaftarkan = tanggalDidaftarkan;
    }

    public Date getTanggalDireview() {
        return tanggalDireview;
    }

    public void setTanggalDireview(Date tanggalDireview) {
        this.tanggalDireview = tanggalDireview;
    }

    public Date getTanggalDipublikasikan() {
        return tanggalDipublikasikan;
    }

    public void setTanggalDipublikasikan(Date tanggalDipublikasikan) {
        this.tanggalDipublikasikan = tanggalDipublikasikan;
    }
}
