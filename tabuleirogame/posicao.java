package Xadrez.tabuleirogame;
/*
Autor: Pedro Henrique Barros de Oliveira Sousa
Professor Auxiliar/que ajudou: Nélio Alves -> github.com/acenelio
*/
public class posicao {
    private int linha;
    private int coluna;
    
    public posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setCoordenada(int linha, int coluna){
         this.linha = linha;
         this.coluna = coluna;
        
    }

    //METODO:
    @Override
    public String toString(){
        return getLinha() + ", " + getColuna();
    }
}
