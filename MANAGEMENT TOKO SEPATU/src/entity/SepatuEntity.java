package entity;

public class SepatuEntity {
    private String nama;
    private String merk;
    private String warna;
    private int stok;

    public SepatuEntity(String nama, String merk, String warna, int stok) {
        this.nama = nama;
        this.merk = merk;
        this.warna = warna;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "SepatuEntity{" +
                "nama='" + nama + '\'' +
                ", merk='" + merk + '\'' +
                ", warna='" + warna + '\'' +
                ", stok=" + stok +
                '}';
    }

    // Tambahan metode atau fungsionalitas lain jika diperlukan
}
    