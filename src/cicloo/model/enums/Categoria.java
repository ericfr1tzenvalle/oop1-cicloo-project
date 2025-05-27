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
 * Representa as categorias disponíveis para um hábito.
 */

public enum Categoria {
    SAUDE("Saúde"),
    ESTUDO("Estudo"),
    PRODUTIVIDADE("Produtividade"),
    LAZER("Lazer"),
    FINANCAS("Finanças"),
    RELACIONAMENTOS("Relacionamentos"),
    ESPIRITUALIDADE("Espiritualidade"),
    CARREIRA("Carreira"),
    AUTOCUIDADO("Autocuidado"),
    VOLUNTARIADO("Voluntariado");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
