package Xadrez.xadrezin;

import Xadrez.tabuleirogame.peca;
import Xadrez.tabuleirogame.tabuleiro;

public class xadrezpeca extends peca{
    private cor corzinha;

    public xadrezpeca(tabuleiro tabul, cor corzinha) {
        super(tabul);
        this.corzinha = corzinha;
    }

    public cor getCorzinha() {
        return corzinha;
    }

    
    
    


}
