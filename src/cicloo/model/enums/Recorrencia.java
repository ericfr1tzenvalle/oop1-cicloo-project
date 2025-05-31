/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model.enums;

/**
 *
 * @author 2024020020
 */

/**
 * Representa a recorrência de um hábito.
 */
public enum Recorrencia {
    DIARIA("Diaria"),
    SEMANAL("Semanal"),
    MENSAL("Mensal"),
    ANUAL("Anual"),
    PERSONALIZADO("Personalizado");
    
    private final String descrição;

    private Recorrencia(String descrição) {
        this.descrição = descrição;
    }

    public String getDescrição() {
        return descrição;
    }
    
    
    
}
