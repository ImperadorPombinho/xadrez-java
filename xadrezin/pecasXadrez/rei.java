package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.partidaxadrez;
import Xadrez.xadrezin.xadrezpeca;

public class rei extends xadrezpeca{

    private partidaxadrez partidaxadrez;

    public rei(tabuleiro tabul, cor corzinha, partidaxadrez partidaxadrez) {
        super(tabul, corzinha);
        this.partidaxadrez = partidaxadrez;
    }
    @Override
    public String toString(){
        //k simboliza rei no tabuleiro
        return "♚";
    }
    private partidaxadrez getPartidaxadrez() {
        return partidaxadrez;
    }
    private boolean podeMover(posicao posicao){
        xadrezpeca aux = (xadrezpeca)getTabul().peca(posicao);
        return aux == null || aux.getCorzinha() != getCorzinha();
    }
    private boolean testaTorreEmRoque(posicao posicao){
        xadrezpeca aux = (xadrezpeca)getTabul().peca(posicao);
        return aux != null && aux instanceof torre && aux.getCorzinha() == getCorzinha() && aux.getContMovimento() == 0;
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


        // Jogada especial Roque
        if(getContMovimento() == 0 && !getPartidaxadrez().getXeque()){
            //roque pequeno
             posicao posTorre1 = new posicao(posicaoo.getLinha(), posicaoo.getColuna() + 3);
             if(testaTorreEmRoque(posTorre1)){
                 posicao p1 =  new posicao(posicaoo.getLinha(), posicaoo.getColuna() + 1);
                 posicao p2 =  new posicao(posicaoo.getLinha(), posicaoo.getColuna() + 2);
                 if(getTabul().peca(p1) == null && getTabul().peca(p2) == null){
                     matriz[posicaoo.getLinha()][posicaoo.getColuna() + 2] = true;
                 }
             }
            // roque grande
            posicao posTorre2 = new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 4);
            if(testaTorreEmRoque(posTorre2)){
                posicao p1 =  new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 1);
                posicao p2 =  new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 2);
                posicao p3 =  new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 3);
                if(getTabul().peca(p1) == null && getTabul().peca(p2) == null && getTabul().peca(p3) == null){
                    matriz[posicaoo.getLinha()][posicaoo.getColuna() - 2] = true;
                }
            }

        }

        return matriz;
    }


}
