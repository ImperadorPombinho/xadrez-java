package Xadrez.aplicacao;



import java.util.Scanner;

import Xadrez.xadrezin.partidaxadrez;
import Xadrez.xadrezin.xadrezpeca;
import Xadrez.xadrezin.xadrezposicao;


public class mainzada {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
       partidaxadrez partidaxadrez = new partidaxadrez();
       while(true){
        UI.printartabuleiro(partidaxadrez.getpecas());  
        System.out.println();
        System.out.print("Posicao de origem: ");
        xadrezposicao origem = UI.lerPosicaoXadrez(scan);
        
        System.out.println();
        System.out.print("Posicao de destino: ");
        xadrezposicao destino = UI.lerPosicaoXadrez(scan);

        xadrezpeca pecaCapturada = partidaxadrez.perfomaceMoveXadrezPeca(origem, destino);
       }
        
    }
    
}
