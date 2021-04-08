package Xadrez.xadrezin;


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
    private void colocarNovaPeca(char coluna, int linha, xadrezpeca peca){
        tabuleiro.colocarPeca(peca, new xadrezposicao(coluna, linha).paraPosicao());
    }
     private void setupInicial(){
         colocarNovaPeca('b', 6, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 8, new rei(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 1, new rei(tabuleiro, cor.WHITE));
     }
    
}
