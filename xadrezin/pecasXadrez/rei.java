package Xadrez.xadrezin.pecasXadrez;

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
}
