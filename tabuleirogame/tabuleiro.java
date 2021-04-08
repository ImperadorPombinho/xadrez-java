package Xadrez.tabuleirogame;

public class tabuleiro {
    private int linhas;
    private int colunas;
    private peca[][] pecas;

    public tabuleiro(int linhas, int colunas) {
        if(linhas < 1 || colunas < 1){
            throw new excecaoTabul("erro criando tabuleiro: eh necessario que haja pelo menos 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    //METODO
    public peca peca(int linha, int coluna){
        if(!posicaoExiste(linha, coluna)){
            throw new excecaoTabul("nao tem essa posicao nesse tabuleiro");
        }
        return pecas[linha][coluna];
    }
    public peca peca(posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new excecaoTabul("nao tem essa posicao nesse tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    public void colocarPeca(peca peca, posicao posicao){
        if(istoEhUmaPeca(posicao)){
            throw new excecaoTabul("ja tem essa peca no tabuleiro " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicaoo = posicao;
    }
    private boolean posicaoExiste(int linha, int coluna){
        return linha >= 0 && linha < getLinhas() && coluna >= 0 && coluna < getColunas();
    }

    public boolean posicaoExiste(posicao posicao){
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());

    }

    public boolean istoEhUmaPeca(posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new excecaoTabul("nao tem essa posicao nesse tabuleiro");
        }
        return peca(posicao) != null;
    }

}
