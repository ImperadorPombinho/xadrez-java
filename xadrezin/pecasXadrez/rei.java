package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.xadrezpeca;

public class rei extends xadrezpeca{

    public rei(tabuleiro tabul, cor corzinha) {
        super(tabul, corzinha);
        
    }
    @Override
    public String toString(){
        //k simboliza rei no tabuleiro
        return "♚";
    }
    private boolean podeMover(posicao posicao){
        xadrezpeca aux = (xadrezpeca)getTabul().peca(posicao);
        return aux == null || aux.getCorzinha() != getCorzinha();
    }
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabul().getLinhas()][getTabul().getColunas()];

        posicao auxPosicao = new posicao(0, 0);
        //acima
        auxPosicao.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna());
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //abaixo
        auxPosicao.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna());
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //esquerda
        auxPosicao.setCoordenada(posicaoo.getLinha(), posicaoo.getColuna() - 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //direita
        auxPosicao.setCoordenada(posicaoo.getLinha(), posicaoo.getColuna() + 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //supEsquerda
        auxPosicao.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() - 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //infEsquerda
        auxPosicao.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() - 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //supDireita
        auxPosicao.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() + 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }
        //infDireita
        auxPosicao.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() + 1);
        if(getTabul().posicaoExiste(auxPosicao) && podeMover(auxPosicao)){
            matriz[auxPosicao.getLinha()][auxPosicao.getColuna()] = true;
        }

        return matriz;
    }
}
