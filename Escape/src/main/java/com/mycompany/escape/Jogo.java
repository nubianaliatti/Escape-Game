/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.escape;

/**
 *
 * @author NOTE
 */
import java.util.*;
public class Jogo {
    private char matriz[][] = new char[10][10];
    private boolean perdeu;
    private boolean ganhou;
    private List<String> instrucoes = new ArrayList<>();
    int linhaAtual;
    int colunaAtual;
    int dificuldade;

    public Jogo(int d) {
        dificuldade = d;
        inicializar();
        configurarTabela(dificuldade);
    }
    
    private void inicializar(){
        instrucoes.clear();
        perdeu = false;
        ganhou = false;
        linhaAtual = 0;
        colunaAtual = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matriz[i][j] = ' ';
            }
        }
        matriz[0][0] = 'P';
        configurarTabela(dificuldade);
    }

    private void configurarTabela(int dificuldade) {
        int qtdBombas;
        int saida;
        Random gerador = new Random();
        saida = gerador.nextInt(10);
        matriz[saida][9] = 'S';
        int linha, coluna;
        if (dificuldade == 0){
            qtdBombas = 12;
            int i = 0;
            while (i < 12) {
                linha = gerador.nextInt(9);
                coluna = gerador.nextInt(9);
                if (checarMatriz(linha, coluna)) {
                    matriz[linha][coluna] = 'B';
                    i++;
                }
            }
        }
        else if (dificuldade == 1) {
            qtdBombas = 20;
            int j = 0;
            while (j < 20) {
                linha = gerador.nextInt(9);
                coluna = gerador.nextInt(9);
                if (checarMatriz(linha, coluna)) {
                    matriz[linha][coluna] = 'B';
                    j++;
                }
            }
        }
        else if (dificuldade == 2) {
            qtdBombas = 30;
            int k = 0;
            while (k < 30) {
                linha = gerador.nextInt(9);
                coluna = gerador.nextInt(9);
                if (checarMatriz(linha, coluna)) {
                    matriz[linha][coluna] = 'B';
                    k++;
                }
            }
        }
        jogar();
    }

    public void jogar() {
        Scanner sc = new Scanner(System.in);
        String jogada;
        imprimir();
        while (!ganhou && !perdeu) {
            System.out.println("Digite sua jogada: ex(d,2)");
            jogada = sc.nextLine();
            instrucoes.add(jogada);
            checarJogada(jogada);
            System.out.println(jogada);
        }
        if (ganhou) {
            imprimirJogo();
            System.out.println("Parabens, voce ganhou!!");
        } else if (perdeu) {
            imprimirJogo();
            System.out.println("Game Over!");
            System.out.println("Deslocamentos realizados");
            for (String s : instrucoes) {
                System.out.println(s);
            }
        }
        String jogarNovamente;
        System.out.println("Deseja jogar novamente? (S / N)");
        jogarNovamente = sc.nextLine();
        if (jogarNovamente.charAt(0) == 's' || jogarNovamente.charAt(0) == 'S')
            inicializar();
    }
    
    private boolean checarMatriz(int linha, int coluna) {
        boolean permitido = true;
        if(matriz[linha][coluna] == 'P')
            permitido = false;
        if(matriz[linha][coluna] == 'B')
            permitido = false;
        if (coluna - 1 >= 0) {
            if (matriz[linha][coluna - 1] == 'B') {
                permitido = false;
            }
        }
        if (coluna + 1 <= 9) {
            if (matriz[linha][coluna + 1] == 'B') {
                permitido = false;
            }
        }
        if (linha - 1 >= 0) {
            if (matriz[linha - 1][coluna] == 'B') {
                permitido = false;
            }
        }
        if (linha + 1 <= 9) {
            if (matriz[linha + 1][coluna] == 'B') {
                permitido = false;
            }
        }
        return permitido;
    }

    public void checarJogada(String jogada) {
        if (jogada.charAt(1) == 'd') {
            if (colunaAtual + Character.getNumericValue(jogada.charAt(3)) <= 9) {
                for(int i = colunaAtual; i <= (colunaAtual+Character.getNumericValue(jogada.charAt(3))); i++){
                    if (matriz[linhaAtual][i] == 'B') {
                    perdeu = true;
                    matriz[linhaAtual][i] = 'X';
                }
                }
                if (!perdeu){
                    if(matriz[linhaAtual][colunaAtual + Character.getNumericValue(jogada.charAt(3))] == 'S') {
                    ganhou = true;
                    }
                } if(!perdeu) {
                    matriz[linhaAtual][colunaAtual + Character.getNumericValue(jogada.charAt(3))] = 'P';
                    matriz[linhaAtual][colunaAtual] = ' ';
                    colunaAtual = colunaAtual + Character.getNumericValue(jogada.charAt(3));
                    imprimir();
                }
            }
            else
                System.out.println("Jogada invalida");
        } else if (jogada.charAt(1) == 'e') {
            if (colunaAtual - Character.getNumericValue(jogada.charAt(3)) >= 0) {
                for(int i = colunaAtual; i >= (colunaAtual - Character.getNumericValue(jogada.charAt(3)));i--){
                    if (matriz[linhaAtual][i] == 'B') {
                    perdeu = true;
                    matriz[linhaAtual][i] = 'X';
                    }
                }
                if(!perdeu){
                    if (matriz[linhaAtual][colunaAtual - Character.getNumericValue(jogada.charAt(3))] == 'S') {
                    ganhou = true;
                    }
                } if(!perdeu) {
                    matriz[linhaAtual][colunaAtual - Character.getNumericValue(jogada.charAt(3))] = 'P';
                    matriz[linhaAtual][colunaAtual] = ' ';
                    colunaAtual = colunaAtual - Character.getNumericValue(jogada.charAt(3));
                    imprimir();
                }
            }
            else
                System.out.println("Jogada invalida");
        } else if (jogada.charAt(1) == 'b') {
            if (linhaAtual + Character.getNumericValue(jogada.charAt(3)) <= 9) {
                for(int i = linhaAtual; i <= (linhaAtual + Character.getNumericValue(jogada.charAt(3)));i++){
                    if (matriz[i][colunaAtual] == 'B') {
                    perdeu = true;
                    matriz[i][colunaAtual] = 'X';
                } 
                }
                if (!perdeu){
                    if (matriz[linhaAtual + Character.getNumericValue(jogada.charAt(3))][colunaAtual] == 'S') {
                    ganhou = true;
                    }
                } if(!perdeu) {
                    matriz[linhaAtual + Character.getNumericValue(jogada.charAt(3))][colunaAtual] = 'P';
                    matriz[linhaAtual][colunaAtual] = ' ';
                    linhaAtual = linhaAtual + Character.getNumericValue(jogada.charAt(3));
                    imprimir();
                }
            }
            else
                System.out.println("Jogada invalida");
        } else if (jogada.charAt(1) == 'c') {
            if (linhaAtual - Character.getNumericValue(jogada.charAt(3)) >= 0) { 
                for(int i = linhaAtual; i >= (linhaAtual - Character.getNumericValue(jogada.charAt(3)));i--){
                    if (matriz[linhaAtual - Character.getNumericValue(jogada.charAt(3))][colunaAtual] == 'B'){
                    perdeu = true;
                    matriz[i][colunaAtual] = 'X';
                }
                }                
                if(!perdeu){
                    if(matriz[linhaAtual - Character.getNumericValue(jogada.charAt(3))][colunaAtual] == 'S')
                    ganhou = true;
                }
                if(!perdeu) {
                    matriz[linhaAtual - Character.getNumericValue(jogada.charAt(3))][colunaAtual] = 'P';
                    matriz[linhaAtual][colunaAtual] = ' ';
                    linhaAtual = linhaAtual - Character.getNumericValue(jogada.charAt(3));
                    imprimir();
                }
            }
            else
                System.out.println("Jogada invalida");
        }
    }

    public void imprimir() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(matriz[i][j] == 'B' || matriz[i][j] == 'S')
                    System.out.print("|" + " ");
                else
                    System.out.print("|" + matriz[i][j]);
            }
            System.out.println("|");
        }
    }

    private void imprimirJogo(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + matriz[i][j]);
            }
            System.out.println("|");
        }
    }


}
