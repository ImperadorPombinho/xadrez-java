package Xadrez.aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import Xadrez.xadrezin.excecaoxadrez;

import Xadrez.xadrezin.partidaxadrez;
import Xadrez.xadrezin.xadrezpeca;
import Xadrez.xadrezin.xadrezposicao;

public class mainzada {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       String tipo;
       partidaxadrez partidaxadrez = new partidaxadrez();
       List<xadrezpeca> capturadas = new ArrayList<>();
         String[] nome = UI.lerNomes(scan);
         scan.nextLine();
       while(!partidaxadrez.getXequeMate()){
        try{
            UI.limparTelaConsole();   
            UI.printarPartida(partidaxadrez, nome, capturadas);
            System.out.println();
            System.out.print("Posicao de origem: ");
            
            xadrezposicao origem = UI.lerPosicaoXadrez(scan);
            
            boolean[][] possiveismovimentos = partidaxadrez.possiveisMovimentos(origem);
            UI.limparTelaConsole();
            UI.printartabuleiro(partidaxadrez.getpecas(), possiveismovimentos);

            System.out.println();
            System.out.print("Posicao de destino: ");
            xadrezposicao destino = UI.lerPosicaoXadrez(scan);

            xadrezpeca pecaCapturada = partidaxadrez.perfomaceMoveXadrezPeca(origem, destino);
            if(pecaCapturada != null){
                capturadas.add(pecaCapturada);
            }
            if(partidaxadrez.getPromocao() != null){
                tipo = UI.pedirPromocao(partidaxadrez, scan);
                partidaxadrez.recolocacaoDaPecaPromovida(tipo);
            }
        }
        catch(excecaoxadrez e){
            System.out.println(e.getMessage());
            scan.nextLine();

        }
        catch(InputMismatchException e){
            System.out.println(e.getMessage());
            scan.nextLine();
        }
       }
       UI.limparTelaConsole();
       UI.printarPartida(partidaxadrez, nome, capturadas);
        
    }

}
