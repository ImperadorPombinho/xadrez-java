package Xadrez.xadrezin;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.pecasXadrez.rei;
import Xadrez.xadrezin.pecasXadrez.torre;

public class partidaxadrez {
    private tabuleiro tabuleiro;
    public partidaxadrez(){
        tabuleiro = new tabuleiro(8, 8);
        setupInicial();
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
     private void setupInicial(){
         tabuleiro.colocarPeca(new torre(tabuleiro, cor.WHITE), new posicao(2, 1));
         tabuleiro.colocarPeca(new rei(tabuleiro, cor.BLACK), new posicao(0, 4));
         tabuleiro.colocarPeca(new rei(tabuleiro, cor.WHITE), new posicao(7, 4));
     }
    
}
