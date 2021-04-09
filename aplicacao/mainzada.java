package Xadrez.aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.xadrezin.excecaoxadrez;
import Xadrez.xadrezin.partidaxadrez;
import Xadrez.xadrezin.xadrezpeca;
import Xadrez.xadrezin.xadrezposicao;

public class mainzada {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       partidaxadrez partidaxadrez = new partidaxadrez();
       while(true){
        try{
            UI.limparTelaConsole();   
            UI.printartabuleiro(partidaxadrez.getpecas());  
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
            //System.out.println("pecapturada: " + pecaCapturada);
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
        
    }

}
