/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model.enums;

/**
 * Enum que representa o nível de prioridade de um hábito.
 * <p>
 * A prioridade ajuda o usuário a identificar quais hábitos são mais
 * importantes e devem receber mais foco.
 * </p>
 *
 * @author 2024020020
 */
public enum Prioridade {
    /** Prioridade máxima, indicando um hábito de alta importância. */
    ALTA("Alta"),

    /** Prioridade intermediária, para hábitos de importância moderada. */
    MEDIA("Média"),

    /** Prioridade mínima, para hábitos de menor importância ou flexíveis. */
    BAIXA("Baixa");

    /**
     * A representação textual do nível de prioridade.
     */
    private final String descricao;

    /**
     * Construtor privado para a enumeração Prioridade.
     *
     * @param descricao A descrição textual da prioridade (e.g., "Alta").
     */
    Prioridade(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição textual do nível de prioridade.
     * @return A {@code String} contendo a descrição do nível de prioridade.
     */
    
    @Override
    public String toString() {
        return descricao;
    }
    
}