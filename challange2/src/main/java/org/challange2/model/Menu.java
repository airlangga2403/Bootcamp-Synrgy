package challange2.model;

public class Menu {
    private final String nama;
    private int jumlah;
    private double harga;
    private String note;

    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public Menu(String nama, int jumlah, double harga, String note) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
        this.note = note;
    }

    public String getNama() {
        return nama;
    }

    public String getNote() {
        return note;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
