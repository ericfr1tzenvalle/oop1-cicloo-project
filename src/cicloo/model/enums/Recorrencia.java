/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model.enums;

/**
 * Enum que representa a recorrência ou a frequência com que um hábito
 * deve ser executado.
 * <p>
 * Define os intervalos de tempo padrão para a repetição de um hábito,
 * como diário, semanal, etc., além de uma opção para configurações personalizadas.
 * </p>
 *
 * @author 2024020020
 */
public enum Recorrencia {
    /** Recorrência diária, o hábito deve ser executado todos os dias. */
    DIARIA("Diaria"),

    /** Recorrência semanal, o hábito deve ser executado uma vez por semana. */
    SEMANAL("Semanal"),

    /** Recorrência mensal, o hábito deve ser executado uma vez por mês. */
    MENSAL("Mensal"),

    /** Recorrência anual, o hábito deve ser executado uma vez por ano. */
    ANUAL("Anual"),

    /**
     * Recorrência personalizada, permitindo definir intervalos específicos
     * (ex: a cada X dias).
     */
    PERSONALIZADO("Personalizado");

    /**
     * A representação textual da frequência de recorrência.
     */
    private final String descricao;

    /**
     * Construtor privado para a enumeração Recorrencia.
     *
     * @param descricao textual da recorrência (e.g., "Diaria").
     */
    private Recorrencia(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição textual recorrência.
     * 
     * @return A {@code String} contendo a descrição da recorrência.
     */
    @Override
    public String toString() {
        return descricao;
    }
    
    
}