package Xadrez.xadrezin.pecasXadrez;

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
        return matriz;
    }

    
}
