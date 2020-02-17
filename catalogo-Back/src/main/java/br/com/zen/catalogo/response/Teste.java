package br.com.zen.catalogo.response;

import java.util.Arrays;

/**
 * Teste
 */
public class Teste {

    public static void main(String[] args) {
        Arrays.asList(SemanaEnum.values()).forEach(dia -> {
            System.out.println(dia);
        });
    }
}