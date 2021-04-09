package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.xadrezpeca;

public class torre extends xadrezpeca{

    public torre(tabuleiro tabul, cor corzinha) {
        super(tabul, corzinha);
        
    }
    @Override
    public String toString(){
        return "♜";
    }
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabul().getLinhas()][getTabul().getColunas()];
        
        posicao pos = new posicao(0, 0);
        //acima
        pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna());
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setLinha(pos.getLinha() - 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //esquerda
        pos.setCoordenada(posicaoo.getLinha(), posicaoo.getColuna() - 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setColuna(pos.getColuna() - 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //direita
        pos.setCoordenada(posicaoo.getLinha(), posicaoo.getColuna() + 1);
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setColuna(pos.getColuna() + 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        //baixo
        pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna());
        while(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
            pos.setLinha(pos.getLinha() + 1);
        }
        if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
            matriz[pos.getLinha()][pos.getColuna()] = true;
        }
        
        return matriz;
    }

    
}
