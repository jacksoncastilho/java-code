/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelha;

import java.util.*;

/**
 *
 * @author Jackson Castilho
 */
public class jogoVelha {

    public static void singlePlayer() {
        //criando objeto scanner 
        Scanner string = new Scanner(System.in);

        //matriz das posicoes 3X3
        String[][] matrizPosicoes = new String[3][3];

        //vetor do players
        String[] player = new String[2];
        player[0] = "robo";

        //define simbolo default para todas as posicoes
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                matrizPosicoes[l][c] = "-";
            }
        }

        //nome do player
        System.out.print("Informe seu nome: ");
        player[1] = string.nextLine();

        System.out.println();
        System.out.println("Escolha seu simbolo:\n[1] para X\n[2] para O ");
        String simboloPlayer = string.nextLine();

        String simboloRobo;

        //associa player ao simbolo
        switch (simboloPlayer) {
            case "2":
                simboloRobo = "X";
                simboloPlayer = "O";
                break;
            default:
                simboloPlayer = "X";
                simboloRobo = "O";
        }

        System.out.println();

        //rodadas das jogadas
        for (int rodadas = 0; rodadas < 9; rodadas++) {

            System.out.println("Sua vez " + player[0]);

            //vez do robo jogar
            if (player[0].equals("robo")) {

                //funcao random para sortear numeros "aleatorios" para linha e coluna
                Random random = new Random();
                while (true) {
                    int linha = random.nextInt(3), coluna = random.nextInt(3);
                    //chama metodo checkPosition() para verificar se ja tem simbolo na posicao, caso nao tenha armazena o simbolo da jogada
                    if (checkPosition(matrizPosicoes, player[0], simboloRobo, linha, coluna) == false) {
                        matrizPosicoes[linha][coluna] = simboloRobo;
                        break;
                    }
                }
            } //vez do player jogar
            else if (player[0].equals(player[0])) {

                //loop para obter as posicoes {linha, coluna}
                while (true) {
                    //chama o method getPosition() e armazena no vetor as posicoes {linha, coluna}
                    int[] position = getPosition();

                    //chama metodo checkPosition() para verificar se ja tem simbolo na posicao, caso nao tenha armazena o simbolo da jogada
                    if (checkPosition(matrizPosicoes, player[0], simboloPlayer, position[0], position[1]) == false) {
                        matrizPosicoes[position[0]][position[1]] = simboloPlayer;
                        break;
                    }
                }
            }
            //verifica se possu ganhador a partir da rodada 4
            if (rodadas > 3 & rodadas < 9) {

                //chama e verifica se checkWinner() retornou true, caso tenha retornado false na rodada 8 o jogo empatou
                if (checkWinner(matrizPosicoes, player) == true) {
                    System.out.println("Paraben " + player[0] + " " + "voce venceu!!!!");
                    break;
                } else if (rodadas > 7 & checkWinner(matrizPosicoes, player) == false) {
                    System.out.println("Jogo Empatou!!");
                }
            }

            //logica que intercala a vez do player
            String intercala = player[0];                    //A cada rodada a posicao [0] vai para [1] e
            player[0] = player[1];                        //a posicao [1] vai para [0]. Assim interca-
            player[1] = intercala;                           //lando o player da rodada;

        }

    }

    public static void multPlayer() {

        //criando objetos Scanner 
        Scanner strings = new Scanner(System.in);

        //matriz das posicao 3X3
        String[][] matrizPosicoes = new String[3][3]; //[linha][coluna]

        //vetor de controle da vez de quem joga
        String nomePlayer[] = new String[2];
        //vetor para armazenar o simbolo dos players
        String[] simboloPlayers = new String[2];

        //define simbolo default para todas as posicoes
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                matrizPosicoes[l][c] = "-";
            }
        }

        //informacoes player1
        System.out.print("Informe o nome do player1: ");
        nomePlayer[0] = strings.nextLine();
        System.out.println();
        System.out.println("Escolha seu simbolo\n[1] para X\n[2] para O:");
        simboloPlayers[0] = strings.nextLine();

        //informacoes player2
        System.out.print("Informe o nome do player2: ");
        nomePlayer[1] = strings.nextLine();
        System.out.println();

        //associa player ao simbolo
        switch (simboloPlayers[0]) {
            case "2":
                simboloPlayers[1] = "X";
                simboloPlayers[0] = "O";
                break;
            default:
                simboloPlayers[0] = "X";
                simboloPlayers[1] = "O";
        }

        //rodadas das jogadas
        for (int rodadas = 0; rodadas < 9; rodadas++) {

            System.out.println("Sua vez " + nomePlayer[0]);

            //define a posicao linha coluna
            while (true) {

                //chama metodo checkPosition() para verificar se ja tem simbolo na posicao, caso nao tenha armazena o simbolo da jogada
                int[] position = getPosition();

                //verifica se ja tem simbolo na posicao [linha][coluna]. Se tiver valor padrao "-" armazenha o simbolo na posicao
                if (checkPosition(matrizPosicoes, nomePlayer[0], simboloPlayers[0], position[0], position[1]) == false) {
                    break;
                }
            }
            //verifica se possui vencedor a partir da rodada 3
            if (rodadas > 3 & rodadas < 9) {
                //chama e verifica se checkWinner() retornou true, caso tenha retornado false na rodada 8 o jogo empatou
                if (checkWinner(matrizPosicoes, nomePlayer) == true) {
                    System.out.println("Paraben " + nomePlayer[0] + " " + "voce venceu!!!!");
                    break;
                } else if (rodadas > 7 & checkWinner(matrizPosicoes, nomePlayer) == false) {
                    System.out.println("Jogo Empatou!!");
                }
            }

            //logica que intercala a vez do PLAYER
            String intercalaPlayer = nomePlayer[0];             //A cada rodada a posicao [0] vai para [1] e
            nomePlayer[0] = nomePlayer[1];                      //a posicao [1] vai para [0]. Assim interca-
            nomePlayer[1] = intercalaPlayer;                    //lando o player e o simbolo da rodada;

            //logica que intercala a vez do SIMBOLO
            String intercalaSimbolo = simboloPlayers[0];
            simboloPlayers[0] = simboloPlayers[1];
            simboloPlayers[1] = intercalaSimbolo;
        }
    }

    public static int[] getPosition() {
        //criando objeto objeto scanner
        Scanner values = new Scanner(System.in);

        //vetor ds posicoes {linha, coluna}
        int[] positions = new int[2];

        //loop para definir as posicoes da linha e da coluna
        while (true) {

            //posicao linha
            System.out.print("Informe uma posicao para linha: ");
            positions[0] = values.nextInt() -1;

            //posicao coluna
            System.out.print("Informe uma posicao para coluna: ");
            positions[1] = values.nextInt() -1;

            //verifica se a posicao existe
            if (positions[0] + 1 > 3 || positions[1] + 1 > 3) {
                System.out.println("---------------------");
                System.out.println("|Posicao nao existe!|");
                System.out.println("---------------------");

            } else {
                break;
            }
        }
        System.out.println();

        //retorna um vetor positions {linha, coluna}
        return positions;
    }

    public static boolean checkPosition(String matrizPosicoes[][], String vezPlayer, String simbolo, int linha, int coluna) {

        boolean checkTem = true;

        //verifica se ja tem simbolo na posicao {linha, coluna}. Se tiver valor padrao "-" armazenha
        if (matrizPosicoes[linha][coluna].equals("-")) {

            matrizPosicoes[linha][coluna] = simbolo;

            //mostra matriz no console
            for (int l = 0; l < 3; l++) {
                System.out.print("[");
                for (int c = 0; c < 3; c++) {
                    System.out.print(matrizPosicoes[l][c]);
                }
                System.out.println("]");
            }

            System.out.println();

            //retorna false caso nao tenha simbolo na posicao 
            checkTem = false;
            return checkTem;

        } else if (matrizPosicoes[linha][coluna].equals("X") || matrizPosicoes[linha][coluna].equals("O")) {
            if (vezPlayer.equals("robo")) {

            } else {
                System.out.println("----------------------------");
                System.out.println("|Posicao ja possui simbolo!|");
                System.out.println("----------------------------");
                System.out.println();
            }
        }
        //caso ja tenha simbolo na posicao retorna true
        return checkTem;
    }

    public static boolean checkWinner(String[][] matriz, String[] player) {
        boolean winner = false;

        //verifica compatibilidade das linhas
        for (int linha = 0; linha < 3; linha++) {
            if (matriz[linha][0].equals("X") & matriz[linha][1].equals("X") & matriz[linha][2].equals("X")) {
                winner = true;
                break;

            } else if (matriz[linha][0].equals("O") & matriz[linha][1].equals("O") & matriz[linha][2].equals("O")) {
                winner = true;
                break;
            }
        }

        //verifica compatibilidade das coluna
        for (int coluna = 0; coluna < 3; coluna++) {
            if (matriz[0][coluna].equals("X") & matriz[1][coluna].equals("X") & matriz[2][coluna].equals("X")) {
                winner = true;
                break;

            } else if (matriz[0][coluna].equals("O") & matriz[1][coluna].equals("O") & matriz[2][coluna].equals("O")) {
                winner = true;
                break;

            }
        }

        //verifica diagonal [0][0] para [2][2]
        if (matriz[0][0].equals("X") & matriz[1][1].equals("X") & matriz[2][2].equals("X")) {
            winner = true;
        } else if (matriz[0][0].equals("O") & matriz[1][1].equals("O") & matriz[2][2].equals("O")) {
            winner = true;
        } //verifica diagonal [0][2] para [2][0]
        else if (matriz[0][2].equals("X") & matriz[1][1].equals("X") & matriz[2][0].equals("X")) {
            winner = true;

        } else if (matriz[0][2].equals("O") & matriz[1][1].equals("O") & matriz[2][0].equals("O")) {
            winner = true;
        }

        return winner;
    }

    public static void main(String[] args) {

        //criando objeto scanner
        Scanner values = new Scanner(System.in);
        
        //dando as boas vindas
        System.out.println("Bem Vindo ao Jogo da Velha!");
        System.out.println();

        //loop principal. Enquanto o usuario nao escolher a opcao [2] o jogo continua
        while (true) {
            
            //escolha do modo de jogo single ou mult
            System.out.println("Jogar em modo SinglePlayer ou Multplayer?\n[1] SinglePlayer\n[2] MultPlayer");
            int modoJogo = values.nextInt();
            
            if (modoJogo == 1) {

                //---SinglePlayer---
                singlePlayer();

            } else if (modoJogo == 2) {

                //---MultPlayer---
                multPlayer();
            }

            //jogar outra vez?
            System.out.println();
            System.out.println("Jogar outra vez? \n[1] SIM\n[2] NAO");
            
            int decisao = values.nextInt();
            if (decisao == 2) {
                break;
            }

        }
    }
}
