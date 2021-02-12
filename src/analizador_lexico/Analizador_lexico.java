/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_lexico;

import analizador_lexico.Token.Tipos;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aloc
 */
public class Analizador_lexico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("INGRESE UNA EXPRESIÓN ARITMÉTICA");
        System.out.println("DEJAR ESPACIO ENTRE CARACTER EJP: 2 + 2");
        Scanner input = new Scanner(System.in);
        ArrayList<Token> tokens = lex(input.nextLine());
        for (Token token : tokens) {
            System.out.println("[" + token.getTipo() + ": " + token.getValor() + "]");
        }
    }
    
    private static ArrayList<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<Token>();
        final StringTokenizer st = new StringTokenizer(input);

        while(st.hasMoreTokens()) {
            String palabra = st.nextToken();
            boolean matched = false;

            for (Tipos tokenTipo : Tipos.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);
                if(matcher.find()) {
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(palabra);
                    tokens.add(tk);
                    matched = true;
                }
            }

            if (!matched) {
                throw new RuntimeException("Se encontró un token invalido.");
            }
        }

        return tokens;
    }
    
}
