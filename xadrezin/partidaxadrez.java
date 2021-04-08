package Xadrez.xadrezin;

import Xadrez.tabuleirogame.tabuleiro;

public class partidaxadrez {
    private tabuleiro tabuleiro;
    public partidaxadrez(){
        tabuleiro = new tabuleiro(8, 8);
    }
    public xadrezpeca[][] getpecas(){
        xadrezpeca[][] mat = new xadrezpeca[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas() ;i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (xadrezpeca) tabuleiro.peca(i, j);
                
            }
            
        }
        return mat;
    }
    
}
