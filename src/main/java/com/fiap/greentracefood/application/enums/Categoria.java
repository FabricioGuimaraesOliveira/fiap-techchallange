package com.fiap.greentracefood.application.enums;

public enum Categoria {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA;
    public static boolean contains(Categoria categoria) {
        for (Categoria c : Categoria.values()) {
            if (c.equals(categoria)) {
                return true;
            }
        }
        return false;
    }
}