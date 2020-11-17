package batalhanaval;

import java.util.*;
import javax.swing.text.Position;

/**
 *
 * @author Jackson Castilho
 */
public class BatalhaNaval {

    static String[][] campo = new String[10][10];

    public static void main(String[] args) {

        //criando objeto scanner
        Scanner values = new Scanner(System.in);

        System.out.println("Bem Vindo ao Jogo da Velha!");
        System.out.println();

        //loop principal. Enquanto o usuario nao escolher a opcao [2] o jogo continua
        while (true) {

            singlePlayer();

            //jogar outra vez?
            System.out.println();
            System.out.println("Jogar outra vez? \n[1] SIM\n[2] NAO");
            int decisao = values.nextInt();

            if (decisao == 2) {
                break;
            }

        }
    }

    public static void singlePlayer() {

        Scanner strings = new Scanner(System.in);

        //chama a funcao que coloca os eleentos no campo de forma randomica
        randomPosition();

        //mostra o campo para o player
        for (int l = 0; l < 10; l++) {
            System.out.print("[");
            for (int c = 0; c < 10; c++) {
                if (campo[l][c].equals("n") || campo[l][c].equals("c") || campo[l][c].equals("a")) {
                    System.out.print("-");
                } else {
                    System.out.print(campo[l][c]);
                }
            }
            System.out.println("]");
        }
        System.out.println("");

        //criando objeto objeto scanner
        Scanner values = new Scanner(System.in);

        //vetor dos posicoes {linha, coluna}
        int positions[] = new int[2];
        
        int disparos = 0;
        while(true) {
        System.out.print("Informe a quantidade de disparos: ");
        disparos = values.nextInt();
        if (disparos < 99) {
            break;
        }
        }
        
        System.out.println("");

        //matrizes para fazer controle das posicoes acertadas pelo player
        int[][] navioPositions = new int[disparos][2];
        int[][] canhaoPositions = new int[disparos][2];
        int[][] aviaoPositions = new int[disparos][2];
        int[][] errosPositions = new int[disparos][2];

        //controle da pontuacao que ao final do jogo é multiplicado por um valor referente ao elemento. Navio * 2, canhao * 3, aviao * 5
        int controlePontuacaoNavio = 0, controlePontuacaoCanhao = 0, controlePontuacaoAviao = 0, controlePontuacaoErros = 0, pontuacaoFinal = 0;

        //loop para definir as posicoes da linha e da coluna
        for (int i = 0; i < disparos; i++) {
            while (true) {
                //posicao linha
                System.out.print("Informe uma posicao para linha: ");
                positions[0] = values.nextInt() - 1;

                //posicao coluna
                System.out.print("Informe uma posicao para coluna: ");
                positions[1] = values.nextInt() - 1;

                //verifica se a posicao existe
                if (positions[0] + 1 < 1 || positions[1] + 1 < 1 || positions[0] + 1 > 10 || positions[1] + 1 > 10) {
                    System.out.println("---------------------");
                    System.out.println("|Posicao nao existe!|");
                    System.out.println("---------------------");

                } else {
                    break;
                }
            }

            //verifica se o usuario acertou algum elemento na posicao {linha, coluna}
            //caso tenha acertado um for é acionaddo para guardar a posicao do elemen-
            //to e soma + 1 no controle de pontuacao para depois ser multiplicado.
            System.out.println("");
            if (campo[positions[0]][positions[1]].equals("n")) {
                System.out.println("Acertou!!!");
                campo[positions[0]][positions[1]] = "#";

                for (int c = 0; c < 2; c++) {
                    navioPositions[controlePontuacaoNavio][c] = positions[c] + 1;
                }

                controlePontuacaoNavio++;

            } else if (campo[positions[0]][positions[1]].equals("c")) {
                System.out.println("Acertou!!!");
                campo[positions[0]][positions[1]] = "#";

                for (int c = 0; c < 2; c++) {
                    canhaoPositions[controlePontuacaoCanhao][c] = positions[c] + 1;
                }

                controlePontuacaoCanhao++;

            } else if (campo[positions[0]][positions[1]].equals("a")) {
                System.out.println("Acertou!!!");
                campo[positions[0]][positions[1]] = "#";
                for (int c = 0; c < 2; c++) {
                    aviaoPositions[controlePontuacaoAviao][c] = positions[c] + 1;
                }

                controlePontuacaoAviao++;

            } else if (campo[positions[0]][positions[1]].equals("-")) {
                System.out.println("Errou!!");
                for (int c = 0; c < 2; c++) {
                    errosPositions[controlePontuacaoErros][c] = positions[c] + 1;
                }
                controlePontuacaoErros++;
                campo[positions[0]][positions[1]] = "X";

            } else if (campo[positions[0]][positions[1]].equals("#")) {
                System.out.println("Errou!!");
                for (int c = 0; c < 2; c++) {
                    errosPositions[controlePontuacaoErros][c] = positions[c] + 1;
                }
                controlePontuacaoErros++;
            }
            
            //mostra matriz
            for (int l = 0; l < 10; l++) {
                System.out.print("[");
                for (int c = 0; c < 10; c++) {
                    if (campo[l][c].equals("n") || campo[l][c].equals("c") || campo[l][c].equals("a")) {
                        System.out.print("-");
                    } else {
                        System.out.print(campo[l][c]);
                    }
                }
                System.out.println("]");
            }
            System.out.println("");
        }
        
        System.out.println("--FIM DE JOGO--");
        System.out.println("");
        
        //mostra no console a matriz final
        for (int l = 0; l < 10; l++) {
            System.out.print("[");
            for (int c = 0; c < 10; c++) {
                System.out.print(campo[l][c]);
            }
            System.out.println("]");
        }

        //vetor que facilita infeitar o console e intercalar os elementos
        int elementos[] = new int[4];
        String[] nomeElementos = new String[4];
        nomeElementos[0] = "NAVIO";
        nomeElementos[1] = "CANHAO";
        nomeElementos[2] = "AVIAO";
        nomeElementos[3] = "ERROS";

        //vetor que permite intercalar os elementos na logica do for
        int pontuacao[] = new int[3];
        pontuacao[0] = controlePontuacaoNavio * 2;
        pontuacao[1] = controlePontuacaoCanhao * 3;
        pontuacao[2] = controlePontuacaoAviao * 5;

        System.out.println();
        for (int e = 0; e < 4; e++) {

            System.out.println(nomeElementos[e]);
            if (e < 3) {
                System.out.println("Acertou nas posições:");
            } else {
                System.out.println("Errou nas posições:");
            }
            for (int l = 0; l < disparos; l++) {

                for (int c = 0; c < 2; c++) {

                    elementos[0] = navioPositions[l][c];
                    elementos[1] = canhaoPositions[l][c];
                    elementos[2] = aviaoPositions[l][c];
                    elementos[3] = errosPositions[l][c];

                    if (elementos[e] > 0) {

                        if (c < 1) {
                            System.out.print("{" + elementos[e] + ",");
                        } else {
                            System.out.println(elementos[e] + "}");
                        }
                    }
                }
            }
            if (e < 3) {
                System.out.println("Pontuacao total de cada elemento: " + pontuacao[e]);
                pontuacaoFinal += pontuacao[e];
            }
            System.out.println("");
        }

        System.out.println("Pontuacao Final: " + pontuacaoFinal);
    }

    public static void randomPosition() {

        //define valor default para todas as posicoes
        for (int l = 0; l < 10; l++) {
            for (int c = 0; c < 10; c++) {
                campo[l][c] = "-";
            }
        }

        Random random = new Random();

        int quantidadeElementos = random.nextInt(7);

        //define a quantidade de elemtos que serao postos no campo
        while (quantidadeElementos < 3) {
            quantidadeElementos = random.nextInt(5);
        }

        int linha = 0, coluna = 0;

        for (int i = 0; i < quantidadeElementos; i++) {
            //sorteia quais elementos vao para matriz 0=canhao 1=navio 2=aviao
            int elemento = random.nextInt(3);
            int horizontalVertical = random.nextInt(2);

            //--navio--
            if (elemento == 1) {
                //Navio
                while (true) {
                    //define um valor padrao maximo no random para os elementos nao ecederem o tamanho da matriz
                    linha = random.nextInt(7);
                    coluna = random.nextInt(7);
                    if (horizontalVertical == 0 & campo[linha][coluna].equals("-") & campo[linha][coluna + 1].equals("-") & campo[linha][coluna + 2].equals("-") & campo[linha][coluna + 3].equals("-")) {
                        for (int p = coluna; p < coluna + 4; p++) {
                            campo[linha][p] = "n";
                        }
                        break;
                    } else if (horizontalVertical == 1 & campo[linha][coluna].equals("-") & campo[linha + 1][coluna].equals("-") & campo[linha + 2][coluna].equals("-") & campo[linha + 3][coluna].equals("-")) {
                        for (int p = linha; p < linha + 4; p++) {
                            campo[p][coluna] = "n";
                        }
                        break;
                    }
                }
            }//--canhao--
            if (elemento == 0) {
                while (true) {
                    linha = random.nextInt(8);
                    coluna = random.nextInt(8);
                    if (horizontalVertical == 0 & campo[linha][coluna].equals("-") & campo[linha][coluna + 1].equals("-") & campo[linha][coluna + 2].equals("-")) {
                        for (int p = coluna; p < coluna + 3; p++) {
                            campo[linha][p] = "c";
                        }
                        break;
                    } else if (horizontalVertical == 1 & campo[linha][coluna].equals("-") & campo[linha + 1][coluna].equals("-") & campo[linha + 2][coluna].equals("-")) {
                        for (int p = linha; p < linha + 3; p++) {
                            campo[p][coluna] = "c";
                        }
                        break;
                    }
                }

            } //--aviao--
            else if (elemento == 2) {
                //Navio
                while (true) {
                    linha = random.nextInt(9);
                    coluna = random.nextInt(9);
                    if (horizontalVertical == 0 & campo[linha][coluna].equals("-") & campo[linha][coluna + 1].equals("-")) {
                        for (int p = coluna; p < coluna + 2; p++) {
                            campo[linha][p] = "a";
                        }
                        break;
                    } else if (horizontalVertical == 1 & campo[linha][coluna].equals("-") & campo[linha + 1][coluna].equals("-")) {
                        for (int p = linha; p < linha + 2; p++) {
                            campo[p][coluna] = "a";
                        }
                        break;
                    }
                }
            }
        }

    }
}
