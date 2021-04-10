package Xadrez.xadrezin;


import java.util.ArrayList;
import java.util.List;

import Xadrez.tabuleirogame.peca;
import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.pecasXadrez.rei;
import Xadrez.xadrezin.pecasXadrez.torre;

public class partidaxadrez {
    private tabuleiro tabuleiro;
    private int turno;
    private jogador jogador = new jogador();

    private List<peca> pecasNoTabuleiro = new ArrayList<>();
    private List<peca> pecasCapturadas = new ArrayList<>();
    public partidaxadrez(){
        tabuleiro = new tabuleiro(8, 8);
        turno = 1;
        jogador.setCorjogadoratual(cor.WHITE);
        setupInicial();
    }
    
    public int getTurno() {
        return turno;
    }


    public xadrezpeca[][] getpecas(){
        xadrezpeca[][] mat = new xadrezpeca[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas() ;i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (xadrezpeca) tabuleiro.peca(i, j);
                
            }
            
        }
        return mat;
    }
    public boolean[][] possiveisMovimentos(xadrezposicao origemPosicao){
        posicao posicao = origemPosicao.paraPosicao();
        validacaoPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).possiveisMovimentos();
    }
    public xadrezpeca perfomaceMoveXadrezPeca(xadrezposicao origemposicao, xadrezposicao destinoposicao){
        posicao origem = origemposicao.paraPosicao();
        posicao destino = destinoposicao.paraPosicao();
        validacaoPosicaoOrigem(origem);
        validacaoPosicaoDestino(origem , destino);
        peca pecaCapturada = fazerMovimento(origem, destino);
        proximoTurno();
        return (xadrezpeca) pecaCapturada;

    }
    private peca fazerMovimento(posicao origem, posicao destino){
        peca retiradaOrigem = tabuleiro.removerPeca(origem);
        peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(retiradaOrigem, destino);

        if(pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }

    private void validacaoPosicaoOrigem(posicao posicao){
        if(!tabuleiro.istoEhUmaPeca(posicao)){
            throw new excecaoxadrez("nao existe peca na posicao de origem");
            
        }
        if(jogador.getCorjogadoratual() != ((xadrezpeca)tabuleiro.peca(posicao)).getCorzinha()){
            throw new  excecaoxadrez("nao pode mover pecas adversarias");
        }
        if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel()) {
            throw new excecaoxadrez("nao existe movimentos possiveis para a peca escolhida");
        }
    }
    private void validacaoPosicaoDestino(posicao origem, posicao destino){
        if(!tabuleiro.peca(origem).possivelMovimento(destino)){
            throw new excecaoxadrez("a peca escolhida nao pode se mover para a posicao de destino");
        }
    }
    private void proximoTurno(){
        turno++;
        jogador.setCorjogadoratual((jogador.getCorjogadoratual() == cor.WHITE) ? cor.BLACK : cor.WHITE);
    }
    private void colocarNovaPeca(char coluna, int linha, xadrezpeca peca){
        tabuleiro.colocarPeca(peca, new xadrezposicao(coluna, linha).paraPosicao());
        pecasNoTabuleiro.add(peca);
    }
     private void setupInicial(){
         colocarNovaPeca('c', 1, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('c', 2, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('d', 2, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 2, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 1, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('d', 1, new rei(tabuleiro, cor.WHITE));

         colocarNovaPeca('c', 7, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('c', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('d', 7, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 7, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('d', 8, new rei(tabuleiro, cor.BLACK));


     }

    public jogador getJogador() {
        return jogador;
    }







    
}
