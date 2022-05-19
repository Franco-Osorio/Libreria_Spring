package com.example.libreria.enumeraciones;

public enum GeneroLiterario {
    
//    ACCION, AVENTURA, BIBLICOS, BIOGRAFIA, DRAMA, FICCION, MITOLOGIA, ROMANCE, SUSPENSO, TERROR;
    ACCION("ACCION"),
    AVENTURA("AVENTURA"),
    BIBLICOS("BIBLICOS"),
    BIOGRAFIA("BIOGRAFIA"),
    DRAMA("DRAMA"),
    FICCION("FICCION"),
    MITOLOGIA("MITOLOGIA"),
    ROMANCE("ROMANCE"),
    SUSPENSO("SUSPENSO"),
    TERROR("TERROR");
    
    private String nuevoGenero;
    
    private GeneroLiterario(String nuevoGenero) {
        this.nuevoGenero = nuevoGenero;
    }
    
    public String getNuevoGenero() {
        return this.nuevoGenero;
    }
}
