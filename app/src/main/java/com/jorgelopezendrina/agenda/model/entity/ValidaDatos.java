package com.jorgelopezendrina.agenda.model.entity;

/**
 * Clase interna con métodos estáticos que uso para validar datos.
 * */

public class ValidaDatos {

    public static boolean validaCadena(String cad) {
        boolean error = false;
        if (cad.length() == 0) {
            error = true;
        }
        for (int i = 0; i < cad.length(); i++) {
            if (!Character.isAlphabetic(cad.charAt(i))) {
                error = true;
            }
        }
        return error;
    }

    public static boolean validaNumero(String num) {
        return num.isEmpty() || num.length() <= 0 || num.length() > 5;
    }

    public static boolean validaTlf(String tlf) {
        return tlf.length() == 9 && tlf.charAt(0) >= '6' && tlf.charAt(0) <= '9';
    }
}

