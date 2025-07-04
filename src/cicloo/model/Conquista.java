package cicloo.model;

import java.util.Objects;

/**
 * Representa uma conquista (achievement) que pode ser desbloqueada por um usuário.
 * Cada conquista possui um nome, uma descrição e um status de desbloqueio.
 * O método {@code toString()} formata a saída no terminal com cores ANSI para indicar o status.
 * 
 * Conquistas são utilizadas para motivar o progresso do usuário dentro do sistema Cicloo.
 * 
 * @author Eric
 */
public class Conquista {

    private String nome;
    private String descricao;
    private boolean desbloqueada;

    /**
     * Código ANSI para resetar a cor no terminal.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Código ANSI para cor verde (usado quando a conquista está desbloqueada).
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Código ANSI para cor cinza (usado quando a conquista está bloqueada).
     */
    public static final String ANSI_GRAY = "\u001B[90m";

    /**
     * Cria uma nova conquista com nome e descrição fornecidos.
     * Inicialmente, a conquista está bloqueada.
     *
     * @param nome o nome da conquista.
     * @param descricao uma descrição breve da condição ou significado da conquista.
     * @throws IllegalArgumentException se nome ou descrição forem nulos ou vazios.
     */
    public Conquista(String nome, String descricao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da conquista não pode ser vazio.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da conquista não pode ser vazia.");
        }
        this.nome = nome;
        this.descricao = descricao;
        this.desbloqueada = false;
    }

    /**
     * Retorna o nome da conquista.
     *
     * @return o nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da conquista (privado para controle interno).
     *
     * @param nome novo nome.
     */
    private void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a descrição da conquista.
     *
     * @return a descrição.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da conquista (privado para controle interno).
     *
     * @param descricao nova descrição.
     */
    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna se a conquista está desbloqueada.
     *
     * @return {@code true} se desbloqueada, {@code false} caso contrário.
     */
    public boolean isDesbloqueada() {
        return desbloqueada;
    }

    /**
     * Desbloqueia a conquista.
     */
    public void desbloquear() {
        this.desbloqueada = true;
    }

    /**
     * Define o status de desbloqueio da conquista (privado para controle interno).
     *
     * @param desbloqueada novo status de desbloqueio.
     */
    private void setDesbloqueada(boolean desbloqueada) {
        this.desbloqueada = desbloqueada;
    }

    /**
     * Retorna uma string representando a conquista, formatada com cores ANSI para o terminal.
     *
     * @return uma string colorida indicando se a conquista está bloqueada ou desbloqueada.
     */
    @Override
    public String toString() {
        if (this.desbloqueada) {
            String status = "[DESBLOQUEADA]";
            return ANSI_GREEN + status + " " + nome + ": " + descricao + ANSI_RESET;
        } else {
            String status = "[BLOQUEADA]  ";
            return ANSI_GRAY + status + " " + nome + ": " + descricao + ANSI_RESET;
        }
    }
    
    /**
     * Gera um código hash para o objeto Conquista.
     * * @return O código hash.
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + (this.desbloqueada ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conquista other = (Conquista) obj;
        if (this.desbloqueada != other.desbloqueada) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }
    
    
}
