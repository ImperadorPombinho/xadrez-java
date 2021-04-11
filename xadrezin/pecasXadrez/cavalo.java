package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.xadrezpeca;
/*
Autor: Pedro Henrique Barros de Oliveira Sousa
Professor Auxiliar/que ajudou: Nélio Alves -> github.com/acenelio
*/
public class cavalo extends xadrezpeca{

    public cavalo(tabuleiro tabul, cor corzinha) {
        super(tabul, corzinha);
        
    }

    @Override
    public String toString(){
        return "♞";
    }
    private boolean podeMover(posicao posicao){
        xadrezpeca aux = (xadrezpeca)getTabul().peca(posicao);
        return aux == null || aux.getCorzinha() != getCorzinha();
    }
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabul().getLinhas()][getTabul().getColunas()];

        posicao auxPosicao = new posicao(0, 0);
       
        auxPosicao.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() - 2);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() - 2, posicaoo.getColuna() - 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() - 2, posicaoo.getColuna() + 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() + 2);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() + 2);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
       
        auxPosicao.setCoordenada(posicaoo.getLinha() + 2, posicaoo.getColuna() + 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() + 2, posicaoo.getColuna() - 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        
        auxPosicao.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() - 2);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }

        return matriz;
    }
    
}
