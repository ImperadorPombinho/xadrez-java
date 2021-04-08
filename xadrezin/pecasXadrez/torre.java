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
        return "T";
    }

    
}
