package Xadrez.xadrezin.pecasXadrez;

import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.cor;
import Xadrez.xadrezin.partidaxadrez;
import Xadrez.xadrezin.xadrezpeca;

public class peao extends xadrezpeca{
    private partidaxadrez partidaxadrez;
    
    public peao(tabuleiro tabul, cor corzinha, partidaxadrez partidaxadrez) {
        super(tabul, corzinha);
        this.partidaxadrez = partidaxadrez;
    }

    @Override
    public String toString(){
        return "♟";
    }


    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabul().getLinhas()][getTabul().getColunas()];
        posicao pos = new posicao(0, 0);

        if(getCorzinha() == cor.WHITE){
            pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna());
            if(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() - 2, posicaoo.getColuna());
            posicao pos2 = new posicao(posicaoo.getLinha() - 1, posicaoo.getColuna());
            if(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos) && getTabul().posicaoExiste(pos2) && !getTabul().istoEhUmaPeca(pos2) && getContMovimento() == 0){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() - 1);
            if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() - 1, posicaoo.getColuna() + 1);
            if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            // jogada especial en passant branco
            if(posicaoo.getLinha() == 3){
                posicao esquerda = new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 1);
                if(getTabul().posicaoExiste(esquerda) && haUmaPecaDoOponente(esquerda) && getTabul().peca(esquerda) == partidaxadrez.getEnPassantVulnerabilidade()){
                  matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;  
                }
                posicao direita = new posicao(posicaoo.getLinha(), posicaoo.getColuna() + 1);
                if(getTabul().posicaoExiste(direita) && haUmaPecaDoOponente(direita) && getTabul().peca(direita) == partidaxadrez.getEnPassantVulnerabilidade()){
                  matriz[direita.getLinha() - 1][direita.getColuna()] = true;  
                }
            }
        }else{
            pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna());
            if(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() + 2, posicaoo.getColuna());
            posicao pos2 = new posicao(posicaoo.getLinha() + 1, posicaoo.getColuna());
            if(getTabul().posicaoExiste(pos) && !getTabul().istoEhUmaPeca(pos) && getTabul().posicaoExiste(pos2) && !getTabul().istoEhUmaPeca(pos2) && getContMovimento() == 0){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() - 1);
            if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            pos.setCoordenada(posicaoo.getLinha() + 1, posicaoo.getColuna() + 1);
            if(getTabul().posicaoExiste(pos) && haUmaPecaDoOponente(pos)){
                matriz[pos.getLinha()][pos.getColuna()] = true;
            }
            // jogada especial en passant preta
            if(posicaoo.getLinha() == 4){
                posicao esquerda = new posicao(posicaoo.getLinha(), posicaoo.getColuna() - 1);
                if(getTabul().posicaoExiste(esquerda) && haUmaPecaDoOponente(esquerda) && getTabul().peca(esquerda) == partidaxadrez.getEnPassantVulnerabilidade()){
                  matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;  
                }
                posicao direita = new posicao(posicaoo.getLinha(), posicaoo.getColuna() + 1);
                if(getTabul().posicaoExiste(direita) && haUmaPecaDoOponente(direita) && getTabul().peca(direita) == partidaxadrez.getEnPassantVulnerabilidade()){
                  matriz[direita.getLinha() + 1][direita.getColuna()] = true;  
                }
            }

        }
        
        return matriz;
    }

    
}
