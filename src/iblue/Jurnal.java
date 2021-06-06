package iblue;

public class Jurnal {

    private String kode;
    private String judul;
    private String tahun;
    private int volume;

    public Jurnal(String kode, String judul, String tahun, int volume){
        this.kode = kode;
        this.judul = judul;
        this.tahun = tahun;
        this.volume = volume;
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
