/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model.enums;

/**
 * Enum que representa as diversas categorias que um hábito pode ter.
 * <p>
 * Cada categoria é utilizada para classificar e organizar os hábitos
 * criados pelos usuários, permitindo uma melhor visualização e acompanhamento.
 * </p>
 *
 * @author 2024020020
 */
public enum Categoria {
    /** Categoria para hábitos relacionados à saúde e bem-estar físico. */
    SAUDE("Saúde"),

    /** Categoria para hábitos relacionados a aprendizado e desenvolvimento intelectual. */
    ESTUDO("Estudo"),

    /** Categoria para hábitos focados em aumentar a eficiência e a organização. */
    PRODUTIVIDADE("Produtividade"),

    /** Categoria para hábitos relacionados a hobbies, diversão e tempo livre. */
    LAZER("Lazer"),

    /** Categoria para hábitos de controle e planejamento financeiro. */
    FINANCAS("Finanças"),

    /** Categoria para hábitos que visam melhorar interações sociais e familiares. */
    RELACIONAMENTOS("Relacionamentos"),

    /** Categoria para hábitos ligados ao desenvolvimento espiritual e mindfulness. */
    ESPIRITUALIDADE("Espiritualidade"),

    /** Categoria para hábitos focados no crescimento e desenvolvimento profissional. */
    CARREIRA("Carreira"),

    /** Categoria para hábitos de cuidado pessoal, tanto físico quanto mental. */
    AUTOCUIDADO("Autocuidado"),

    /** Categoria para hábitos relacionados a trabalho voluntário e ajuda comunitária. */
    VOLUNTARIADO("Voluntariado");

    /**
     * A representação textual amigável da categoria.
     */
    private final String descricao;

    /**
     * Construtor privado para a enumeração Categoria.
     *
     * @param descricao A descrição textual da categoria (e.g., "Saúde").
     */
    Categoria(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição textual amigável da categoria.
     * <p>
     * Este método é útil para exibir o nome da categoria de forma legível
     * em interfaces de usuário.
     * </p>
     *
     * @return A {@code String} contendo a descrição da categoria.
     */
    //Esse eu tinha criado antes mas o toString já resolve.
    public String getDescricao() {
        return descricao;
    }
    /**
     * Retorna uma representação em String da descrição.
     * * @return Uma String da descrição.
     */
    @Override
    public String toString() {
        return descricao;
    }
    
    
}