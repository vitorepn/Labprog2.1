package ControleAereo.src;

import java.lang.*;
import java.util.*;

public class Dijkstra {
    private ShortestPath calculador;
    private ArrayList<Triple<String,String,Double>> _caminhos;
    private int tamanho;
    private String[] enumerador;
    int matriz[][];

    public Dijkstra(ArrayList<Triple<String,String,Double>> caminhos){

        tamanho = ((int) Math.sqrt(caminhos.size()));
        String linhaAnterior = "";
        int linha = -1;
        int coluna = 0;
        calculador = new ShortestPath(tamanho);
        _caminhos = caminhos;
        matriz = new int[tamanho][tamanho];
        enumerador = new String[tamanho];

        for (Triple<String,String,Double> a: caminhos){
            if(linhaAnterior!=a.first) {
                linha++;
                enumerador[linha] = a.first;
            }
            if(coluna == tamanho) coluna = 0;
            matriz[linha][coluna] = (a.third).intValue();
            linhaAnterior = a.first;
            coluna++;
        }
    }

    private int getEnumerador(String iata){
        int valor = 0;
        int valorEnum=0;
        for (String chave : enumerador){
            if(iata.equals(chave)) {
                valorEnum = valor;
            }
            valor++;
        }
        return valorEnum;
    }

    public void menorRota(String origem, String destino){
        int valorOrigem = getEnumerador(origem);
        int valorDestino = getEnumerador(destino);
        calculador.dijkstra(matriz,valorOrigem,valorDestino,enumerador);
    }
}

class ShortestPath {
    static int V;
    public ShortestPath(int vertice){
        V = vertice;
    }
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int origem, int destino, int escala, int distancia,String[] enumerador)
    {
        System.out.println("Rota: " + enumerador[origem] +
                            " -> " + enumerador[escala] +
                            " -> " + enumerador[destino] +
                            " - " + distancia);

    }

    void dijkstra(int graph[][], int src,int destino,String[] enumerador)
    {
        int dist[] = new int[V];
        int parada = 0;
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]){
                    dist[v] = (int) (dist[u] + graph[u][v]);
                    if(v == destino){
                        parada = u;
                    }
                }

        }

        printSolution(src, destino, parada,dist[destino],enumerador);
    }
}