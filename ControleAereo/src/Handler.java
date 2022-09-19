package ControleAereo.src;

import java.sql.SQLException;
import java.util.*;

public class Handler {
    private final State _state = new State();
    public Handler() throws SQLException, ClassNotFoundException {
    }

    public HashSet<Aeroporto> get() {
        return _state.get();
    }
    public void menu(){
        HashSet<Aeroporto> aeroportos = _state.get();
        HashSet<String> estados = new HashSet<>();
        Scanner in = new Scanner(System.in);

        for(Aeroporto a : aeroportos){
            estados.add(a.getUf());
        }

        for(String uf : estados) System.out.println(uf);
        System.out.print("Você quer ver dados do estado: ");
        String escolha = in.nextLine();

        for(Aeroporto a: aeroportos){
            if(a.getUf().equals(escolha.toUpperCase()))
                System.out.println("Cidade: " + a.getCidade() +
                                    " / Aeroporto: " + a.getNome() +
                                    " - " + a.getIata());
        }
    }

    public void menorRota(){
        Scanner in = new Scanner(System.in);
        String origem, destino;
        boolean escolhaInvalida = true;
        HashSet<Aeroporto> aeroportos = _state.get();
        do{
            System.out.print("Aeroporto de origem: ");
            origem = in.nextLine().toUpperCase();
            System.out.print("Aeroporto de destino: ");
            destino = in.nextLine().toUpperCase();
            int valido=0;

            for(Aeroporto a : aeroportos){
                if(a.getIata().equals(origem)){
                    System.out.println("Aeroporto válido: " + a.getIata() + " - " + a.getNome());
                    valido++;
                }
                if(a.getIata().equals(destino)) {
                    System.out.println("Aeroporto válido: " + a.getIata() + " - " + a.getNome());
                    valido++;
                }
            }
            if(valido==2) escolhaInvalida = false;
        }
        while(escolhaInvalida);
        System.out.println("Calculando a rota entre os aeroportos");
        calcularMenorRota(origem, destino);
    }
    private void calcularMenorRota(String origem, String destino){
        Dijkstra _dij;
        ArrayList<Triple<String,String,Double>> caminhos = new ArrayList<>();

        for(Aeroporto a : _state.get()){
            for(Aeroporto b: _state.get()){
                Triple<String,String,Double> ligacao = Distancia(a,b);
                caminhos.add(ligacao);
            }
        }
        for(Triple<String,String,Double>caminho : caminhos){
            if(caminho.first.equals(origem) && caminho.second.equals(destino)){
                caminho.third = Double.MAX_VALUE;
            }
            if(caminho.first.equals(destino) && caminho.second.equals(origem)){
                caminho.third = Double.MAX_VALUE;
            }
        }
        _dij = new Dijkstra(caminhos);

        _dij.menorRota(origem, destino);
    }

    public Triple<String,String,Double> Distancia(Aeroporto aero1, Aeroporto aero2){
        double lat1 = aero1.getLatitude();
        double lon1 = aero1.getLongitude();
        double el1 = aero1.getAltitude()*0.3048;
        double lat2 = aero2.getLatitude();
        double lon2 = aero2.getLongitude();
        double el2 = aero2.getAltitude()*0.3048;

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = el1 - el2;

        distance = Math.sqrt((Math.pow(distance, 2) + Math.pow(height, 2)))*1e-3;

        return new Triple<String,String,Double>(aero1.getIata(),aero2.getIata(),distance);
    }
}
