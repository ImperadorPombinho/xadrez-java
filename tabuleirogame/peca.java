package Xadrez.tabuleirogame;

public class peca {
    protected posicao posicaoo;
    private tabuleiro tabul;


    public peca(tabuleiro tabul) {
        this.tabul = tabul;
        posicaoo = null;
    }


    protected tabuleiro getTabul() {
        return tabul;
    }
    

}
