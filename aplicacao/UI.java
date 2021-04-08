package Xadrez.aplicacao;

import Xadrez.xadrezin.xadrezpeca;

public class UI {
    public static void printartabuleiro(xadrezpeca[][] pecas){
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8-i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printarpeca(pecas[i][j]);
                
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");

    }
    private static void printarpeca(xadrezpeca peca){
        if(peca == null){
            System.out.print("-");
        }else{
            System.out.print(peca);
        }
        System.out.print(" ");
    }
}
