package Xadrez.xadrezin;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.tabuleirogame.peca;
import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.pecasXadrez.bispo;
import Xadrez.xadrezin.pecasXadrez.peao;
import Xadrez.xadrezin.pecasXadrez.rei;
import Xadrez.xadrezin.pecasXadrez.torre;

public class partidaxadrez {
    private tabuleiro tabuleiro;
    private int turno;
    private boolean xeque;
    private boolean xequeMate;
    private jogador jogador = new jogador();

    private List<peca> pecasNoTabuleiro = new ArrayList<>();
    private List<peca> pecasCapturadas = new ArrayList<>();
    public partidaxadrez(){
        tabuleiro = new tabuleiro(8, 8);
        turno = 1;
        xeque = false;
        xequeMate = false;
        jogador.setCorjogadoratual(cor.WHITE);
        setupInicial();
    }
    
    public int getTurno() {
        return turno;
    }

    public jogador getJogador() {
        return jogador;
    }



    public void setXeque(boolean xeque) {
        this.xeque = xeque;
    }

    public boolean getXeque() {
        return xeque;
    }
    public boolean getXequeMate() {
        return xequeMate;
    }

    public void setXequeMate(boolean xequeMate) {
        this.xequeMate = xequeMate;
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


        if(testarXeque(getJogador().getCorjogadoratual())){
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new excecaoxadrez("voce nao pode se colocar em Xeque");
        }

        setXeque((testarXeque(oponente(getJogador().getCorjogadoratual()))) ? true : false);
        if(testarXequeMate(oponente(getJogador().getCorjogadoratual()))){
            setXequeMate(true);
        }else{
            proximoTurno();
        }

        
        return (xadrezpeca) pecaCapturada;

    }
    private peca fazerMovimento(posicao origem, posicao destino){
        xadrezpeca retiradaOrigem = (xadrezpeca)tabuleiro.removerPeca(origem);
        retiradaOrigem.AumentarContadorMovimento();
        peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(retiradaOrigem, destino);

        if(pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }
    private void desfazerMovimento(posicao origem, posicao destino, peca pecaCapturada){
        xadrezpeca aux = (xadrezpeca)tabuleiro.removerPeca(destino);
        aux.DiminuirContadorMovimento();
        tabuleiro.colocarPeca(aux, origem);

        if(pecaCapturada != null){
            tabuleiro.colocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
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
    private cor oponente(cor corzinha){
        return (corzinha == cor.WHITE) ? cor.BLACK : cor.WHITE;
    }
    private xadrezpeca reizinho(cor corzinhaaa){
        List<peca> lista = pecasNoTabuleiro.stream().filter(x -> ((xadrezpeca)x).getCorzinha() == corzinhaaa).collect(Collectors.toList());
        for (peca peca : lista) {
            if(peca instanceof rei){
                return (xadrezpeca)peca;
            }
        }
        throw new IllegalStateException("nao existe o rei da cor " + corzinhaaa + "no tabuleiro");
    }
    private boolean testarXeque(cor corzinhad){
        posicao posicaoDoRei = reizinho(corzinhad).getXadrezPosicao().paraPosicao();
        List<peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((xadrezpeca)x).getCorzinha() == oponente(corzinhad)).collect(Collectors.toList());
        for (peca peca : pecasDoOponente) {
            boolean[][] matriz = peca.possiveisMovimentos();
            if(matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()] == true){
                return true;
            }
        }
        return false;
    }
    private boolean testarXequeMate(cor corzinhad){
        if(!testarXeque(corzinhad)){
            return false;
        }
        List<peca> lista = pecasNoTabuleiro.stream().filter(x -> ((xadrezpeca)x).getCorzinha() == corzinhad).collect(Collectors.toList());

        for (peca peca : lista) {
            boolean[][] aux = peca.possiveisMovimentos();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if(aux[i][j] == true){
                        posicao origem = ((xadrezpeca)peca).getXadrezPosicao().paraPosicao();
                        posicao destino = new posicao(i, j);
                        peca pecacapturada = fazerMovimento(origem, destino);
                        boolean testexequee = testarXeque(corzinhad);
                        desfazerMovimento(origem, destino, pecacapturada);
                        if(!testexequee){
                            return false;
                        }
                    }
                }
                
            }

        }
        return true;






    }
     private void setupInicial(){
         colocarNovaPeca('h', 1, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('c', 1, new bispo(tabuleiro, cor.WHITE));
         colocarNovaPeca('f', 1, new bispo(tabuleiro, cor.WHITE));
         colocarNovaPeca('a', 1, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 1, new rei(tabuleiro, cor.WHITE));
         colocarNovaPeca('a', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('b', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('c', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('d', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('f', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('g', 2, new peao(tabuleiro, cor.WHITE));
         colocarNovaPeca('h', 2, new peao(tabuleiro, cor.WHITE));


         colocarNovaPeca('a', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('c', 8, new bispo(tabuleiro, cor.BLACK));
         colocarNovaPeca('f', 8, new bispo(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 8, new rei(tabuleiro, cor.BLACK));
         colocarNovaPeca('h', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('a', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('b', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('c', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('d', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('f', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('g', 7, new peao(tabuleiro, cor.BLACK));
         colocarNovaPeca('h', 7, new peao(tabuleiro, cor.BLACK));

     }











    
}
