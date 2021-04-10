package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.xadrezpeca;

public class bispo extends xadrezpeca{

    public bispo(tabuleiro tabul, cor corzinha) {
        super(tabul, corzinha);
        
    }

    @Override
    public String toString(){
        return "♝";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabul().getLinhas()][getTabul().getColunas()];

        posicao pos = new posicao(0, 0);

        //esqSuperior
        pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() - 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setCoordenada(pos.getLinha() - 1, pos.getColuna() - 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //esqInferior
        pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() - 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setCoordenada(pos.getLinha() + 1, pos.getColuna() - 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //dirSuperior
        pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() + 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setCoordenada(pos.getLinha() - 1, pos.getColuna() + 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //dirInferior
        pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() + 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setCoordenada(pos.getLinha() + 1, pos.getColuna() + 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }

        
        return matriz;
    }
    
}
