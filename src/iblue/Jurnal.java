package iblue;

import java.time.Year;

public class Jurnal {

    private String kode;
    private String judul;
    private int tahun;
    private int volume;
    private String kodeRak;

    public Jurnal(String kode, String judul, int tahun, int volume, String kodeRak){
        this.kode = kode;
        this.judul = judul;
        this.tahun = tahun;
        this.volume = volume;
        this.kodeRak = kodeRak;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
