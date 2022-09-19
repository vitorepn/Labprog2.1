package ControleAereo.src;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Handler _handler = new Handler();
        Dictionary<String, String> Escolhas = new Hashtable<>();
        Scanner in = new Scanner(System.in);
        String[] opcoes = {"Encerrar o programa", "Exibir o menu", "Escolher aeroportos"};
        int escolha;
        for(int i = 0;i<opcoes.length;i++) {
            Escolhas.put(String.valueOf(i), opcoes[i]);
        }
        System.out.println("Bem vindo ao sistema de rotas aéreas");
        System.out.println("Para usar você precisa escolher um aeroporto de partida e um de destino");

        while(true){
            Enumeration<String> e = Escolhas.keys();
            while(e.hasMoreElements()){
                String key = e.nextElement();
                System.out.println(key +"-"+Escolhas.get(key));
            }
            System.out.print("O que você deseja fazer?");

            escolha = in.nextInt();

            if(escolha == 0) break;
            if(escolha < 0 || escolha >= opcoes.length){
                System.out.println("Opção inválida");
                continue;
            }
            if(escolha == 1) _handler.menu();
            if(escolha == 2) _handler.menorRota();
        }
    }
}

