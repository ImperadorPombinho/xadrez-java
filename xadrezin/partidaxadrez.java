package Xadrez.xadrezin;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.tabuleirogame.peca;
import Xadrez.tabuleirogame.posicao;
import Xadrez.tabuleirogame.tabuleiro;
import Xadrez.xadrezin.pecasXadrez.bispo;
import Xadrez.xadrezin.pecasXadrez.cavalo;
import Xadrez.xadrezin.pecasXadrez.peao;
import Xadrez.xadrezin.pecasXadrez.rainha;
import Xadrez.xadrezin.pecasXadrez.rei;
import Xadrez.xadrezin.pecasXadrez.torre;

/*
Autor: Pedro Henrique Barros de Oliveira Sousa
Professor Auxiliar/que ajudou: Nélio Alves -> github.com/acenelio
*/
public class partidaxadrez {
    private tabuleiro tabuleiro;
    private int turno;
    private boolean xeque;
    private boolean xequeMate;
    private jogador jogador = new jogador();
    private xadrezpeca enPassantVulnerabilidade;
    private xadrezpeca promocao;

    private List<peca> pecasNoTabuleiro = new ArrayList<>();
    private List<peca> pecasCapturadas = new ArrayList<>();
    public partidaxadrez(){
        tabuleiro = new tabuleiro(8, 8);
        turno = 1;
        xeque = false;
        xequeMate = false;
        enPassantVulnerabilidade = null;
        jogador.setCorjogadoratual(cor.WHITE);
        setupInicial();
    }
    
    public xadrezpeca getPromocao() {
        return promocao;
    }

    private void setPromocao(xadrezpeca promocao) {
        this.promocao = promocao;
    }

    public xadrezpeca getEnPassantVulnerabilidade() {
        return enPassantVulnerabilidade;
    }
    private void setEnPassantVulnerabilidade(xadrezpeca enPassantVulnerabilidade){
        this.enPassantVulnerabilidade = enPassantVulnerabilidade;
    }


    public int getTurno() {
        return turno;
    }

    public jogador getJogador() {
        return jogador;
    }



    private void setXeque(boolean xeque) {
        this.xeque = xeque;
    }

    public boolean getXeque() {
        return xeque;
    }
    public boolean getXequeMate() {
        return xequeMate;
    }

    private void setXequeMate(boolean xequeMate) {
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

        //jogada especial promoção




        if(testarXeque(getJogador().getCorjogadoratual())){
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new excecaoxadrez("voce nao pode se colocar em Xeque");
        }
        xadrezpeca pecaMovida = (xadrezpeca)tabuleiro.peca(destino);
        setPromocao(null);
        if(pecaMovida instanceof peao){
            if((pecaMovida.getCorzinha() == cor.WHITE && destino.getLinha() == 0 || pecaMovida.getCorzinha() == cor.BLACK && destino.getLinha() == 7 )){
                setPromocao((xadrezpeca)tabuleiro.peca(destino));
                setPromocao(recolocacaoDaPecaPromovida("♛"));
            }
        }
        setXeque((testarXeque(oponente(getJogador().getCorjogadoratual()))) ? true : false);
        if(testarXequeMate(oponente(getJogador().getCorjogadoratual()))){
            setXequeMate(true);
        }else{
            proximoTurno();
        }
        //jogada especial en passant 
        if(pecaMovida instanceof peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2 )){
            setEnPassantVulnerabilidade(pecaMovida);

        }else{
            setEnPassantVulnerabilidade(null);
        }

        
        return (xadrezpeca) pecaCapturada;

    }
    public xadrezpeca recolocacaoDaPecaPromovida(String tipo) {
        if(getPromocao() == null ){
            throw new IllegalStateException("nao ha peca para ser promovida");
        }
        if(!tipo.equals("♝") && !tipo.equals("♛") && !tipo.equals("♞") && !tipo.equals("♜")){
            throw new InvalidParameterException("tipo invalido para a promocao");
        }
        posicao pos = getPromocao().getXadrezPosicao().paraPosicao();
        peca aux = tabuleiro.removerPeca(pos);
        pecasNoTabuleiro.remove(aux);

        xadrezpeca novapeca = novaPeca(tipo, getPromocao().getCorzinha());
        tabuleiro.colocarPeca(novapeca, pos);
        pecasNoTabuleiro.add(novapeca);


        return novapeca;
        
    }

    private xadrezpeca novaPeca(String tipo, cor corzinha){
        if(tipo.equals("♝")) return new bispo(tabuleiro, corzinha);
        if(tipo.equals("♛")) return new rainha(tabuleiro, corzinha);
        if(tipo.equals("♞")) return new cavalo(tabuleiro, corzinha);
        return new torre(tabuleiro, corzinha);
        
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
        // roque pequeno
        if(retiradaOrigem instanceof rei && destino.getColuna() == origem.getColuna() + 2){
            posicao origemTorre = new posicao(origem.getLinha(), origem.getColuna() + 3);
            posicao destinoTorre = new posicao(origem.getLinha(), origem.getColuna() + 1);
            xadrezpeca torre = (xadrezpeca)tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.AumentarContadorMovimento();

        } 
        //roque grande 
        if(retiradaOrigem instanceof rei && destino.getColuna() == origem.getColuna() - 2){
            posicao origemTorre = new posicao(origem.getLinha(), origem.getColuna() - 4);
            posicao destinoTorre = new posicao(origem.getLinha(), origem.getColuna() - 1);
            xadrezpeca torre = (xadrezpeca)tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.AumentarContadorMovimento();

        } 

        //jogada especial en passant
        if(retiradaOrigem instanceof peao){
            if(origem.getColuna() != destino.getColuna() && pecaCapturada == null){
                posicao posicaoPeao;
                 if(retiradaOrigem.getCorzinha() == cor.WHITE){
                     posicaoPeao = new posicao(destino.getLinha() + 1, destino.getColuna());

                 }else{
                    posicaoPeao = new posicao(destino.getLinha() - 1, destino.getColuna());
                 }
                 pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
                 pecasCapturadas.add(pecaCapturada);
                 pecasNoTabuleiro.remove(pecaCapturada);
            }
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
          // roque pequeno
          if(aux instanceof rei && destino.getColuna() == origem.getColuna() + 2){
            posicao origemTorre = new posicao(origem.getLinha(), origem.getColuna() + 3);
            posicao destinoTorre = new posicao(origem.getLinha(), origem.getColuna() + 1);
            xadrezpeca torre = (xadrezpeca)tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origemTorre);
            torre.DiminuirContadorMovimento();

        } 
        //roque grande 
        if(aux instanceof rei && destino.getColuna() == origem.getColuna() - 2){
            posicao origemTorre = new posicao(origem.getLinha(), origem.getColuna() - 4);
            posicao destinoTorre = new posicao(origem.getLinha(), origem.getColuna() - 1);
            xadrezpeca torre = (xadrezpeca)tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origemTorre);
            torre.DiminuirContadorMovimento();

        }
         //jogada especial en passant
         if(aux instanceof peao){
            if(origem.getColuna() != destino.getColuna() && pecaCapturada == getEnPassantVulnerabilidade()){
                xadrezpeca peao = (xadrezpeca)tabuleiro.removerPeca(destino);
                posicao posicaoPeao;
                 if(aux.getCorzinha() == cor.WHITE){
                     posicaoPeao = new posicao(3, destino.getColuna());

                 }else{
                    posicaoPeao = new posicao(4, destino.getColuna());
                 }
                 tabuleiro.colocarPeca(peao, posicaoPeao);
            }
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
         colocarNovaPeca('b', 1, new cavalo(tabuleiro, cor.WHITE));
         colocarNovaPeca('g', 1, new cavalo(tabuleiro, cor.WHITE));
         colocarNovaPeca('c', 1, new bispo(tabuleiro, cor.WHITE));
         colocarNovaPeca('f', 1, new bispo(tabuleiro, cor.WHITE));
         colocarNovaPeca('d', 1, new rainha(tabuleiro, cor.WHITE));
         colocarNovaPeca('a', 1, new torre(tabuleiro, cor.WHITE));
         colocarNovaPeca('e', 1, new rei(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('a', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('b', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('c', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('d', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('e', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('f', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('g', 2, new peao(tabuleiro, cor.WHITE, this));
         colocarNovaPeca('h', 2, new peao(tabuleiro, cor.WHITE, this));


         colocarNovaPeca('a', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('b', 8, new cavalo(tabuleiro, cor.BLACK));
         colocarNovaPeca('g', 8, new cavalo(tabuleiro, cor.BLACK));
         colocarNovaPeca('c', 8, new bispo(tabuleiro, cor.BLACK));
         colocarNovaPeca('d', 8, new rainha(tabuleiro, cor.BLACK));
         colocarNovaPeca('f', 8, new bispo(tabuleiro, cor.BLACK));
         colocarNovaPeca('e', 8, new rei(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('h', 8, new torre(tabuleiro, cor.BLACK));
         colocarNovaPeca('a', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('b', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('c', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('d', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('e', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('f', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('g', 7, new peao(tabuleiro, cor.BLACK, this));
         colocarNovaPeca('h', 7, new peao(tabuleiro, cor.BLACK, this));

     }











    
}
