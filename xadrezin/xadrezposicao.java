package Xadrez.xadrezin;

import Xadrez.tabuleirogame.posicao;
/*
Autor: Pedro Henrique Barros de Oliveira Sousa
Professor Auxiliar/que ajudou: Nélio Alves -> github.com/acenelio
*/
public class xadrezposicao {
    private char coluna;
    private int linha;

    public xadrezposicao(char coluna, int linha) {
        if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new excecaoxadrez("erro instanciando Xadrezposicao, valores validos sao de a1 a h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    protected posicao paraPosicao(){
        return new posicao(8 - getLinha(), getColuna() - 'a');
    }
    protected  static xadrezposicao dePosicao(posicao posicao){
        return new xadrezposicao((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }
    @Override
    public String toString(){
        return "" + getColuna() + getLinha();
    }
    
}
