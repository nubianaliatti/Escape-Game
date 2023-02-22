/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.escape;

/**
 *
 * @author NOTE
 */
import java.util.*;
public class Escape {

    public static void main(String[] args) {
        
        int dificuldade;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao Escape Game");
        System.out.println("Escolha o nivel de dificuldade: ");
        System.out.println("0 - Facil");
        System.out.println("1 - Medio");
        System.out.println("2 - Dificil");
        
        dificuldade = sc.nextInt();
        
        Jogo j1 = new Jogo(dificuldade);
    }
}
