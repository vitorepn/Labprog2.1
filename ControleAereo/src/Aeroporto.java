package ControleAereo.src;

public class Aeroporto {
    private final String nome;
    private final double latitude;
    private final double longitude;
    private final int altitude;
    private final String uf;
    private final String cidade;
    private final String iata;

    public Aeroporto(String n, double lat, double lon, int alt, String u, String cid, String i){
        this.nome = n;
        this.latitude = lat;
        this.longitude = lon;
        this.altitude = alt;
        this.uf = u;
        this.cidade = cid;
        this.iata = i;
    }
    public String getNome(){
        return this.nome;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public int getAltitude(){
        return this.altitude;
    }
    public String getUf(){
        return this.uf;
    }
    public String getCidade(){
        return this.cidade;
    }
    public String getIata(){
        return this.iata;
    }

}
