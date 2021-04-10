package Xadrez.tabuleirogame;

public abstract class peca {
    protected posicao posicaoo;
    private tabuleiro tabul;


    public peca(tabuleiro tabul) {
        this.tabul = tabul;
        posicaoo = null;
    }

    public posicao getPosicaoo() {
        return posicaoo;
    }
    protected tabuleiro getTabul() {
        return tabul;
    }
    
    public abstract boolean[][] possiveisMovimentos();

    
    public boolean possivelMovimento(posicao posicao){
            return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean haAlgumMovimentoPossivel(){
        boolean[][] matriz = possiveisMovimentos();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if(matriz[i][j] == true){
                    return true;
                }
            }
            
        }
        return false;
    }






}
