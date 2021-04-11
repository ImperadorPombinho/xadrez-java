package Xadrez.xadrezin;

import Xadrez.tabuleirogame.peca;
import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
/*
Autor: Pedro Henrique Barros de Oliveira Sousa
Professor Auxiliar/que ajudou: Nélio Alves -> github.com/acenelio
*/
public abstract class xadrezpeca extends peca{
    private cor corzinha;
    private int contMovimento;

    public xadrezpeca(tabuleiro tabul, cor corzinha) {
        super(tabul);
        this.corzinha = corzinha;
    }
    public int getContMovimento() {
        return contMovimento;
    }


    public void setContMovimento(int contMovimento) {
        this.contMovimento = contMovimento;
    }
    public void AumentarContadorMovimento(){
        setContMovimento(getContMovimento() + 1);
    }
    public void DiminuirContadorMovimento(){
        setContMovimento(getContMovimento() - 1);
    }

    public xadrezposicao getXadrezPosicao(){
        return xadrezposicao.dePosicao(getPosicaoo());
    }
    public cor getCorzinha() {
        return corzinha;
    }

    protected boolean haUmaPecaDoOponente(posicao posicao){
        xadrezpeca p = (xadrezpeca) getTabul().peca(posicao);
        return p != null && p.getCorzinha() != getCorzinha();
    }




    
    
    


}
