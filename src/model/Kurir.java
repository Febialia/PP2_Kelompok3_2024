package model;

public class Kurir {
    private int id;
    private String nama_kurir;
    private int total;

    // Konstruktor
    public Kurir() {}

    public Kurir(int id, String nama_kurir) {
        this.id = id;
        this.nama_kurir = nama_kurir;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public int getTotal(){
        return this.total;
    }


    public String getNamaKurir() {
        return nama_kurir;
    }

    public void setNamaKurir(String nama_kurir) {
        this.nama_kurir = nama_kurir;
    }

    // toString untuk representasi string dari objek Kurir
    @Override
    public String toString()
    {
        return nama_kurir;
    }
}