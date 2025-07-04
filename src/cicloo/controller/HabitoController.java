package cicloo.controller;

import cicloo.dao.HabitoDAO;
import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;
import java.util.Objects;

/**
 * Controlador responsável por operações relacionadas a um único hábito.
 * Permite concluir, atualizar e gerenciar o progresso de um hábito individual.
 * Utiliza o {@link HabitoDAO} para persistência em memória.
 * 
 * Antes de utilizar os métodos que manipulam dados, o hábito deve ser definido
 * com o método {@link #setHabito(Habito)}.
 * 
 * @author Eric
 */
public class HabitoController {

    private Habito habito;
    private final HabitoDAO habitoDAO;

    /**
     * Construtor padrão. Inicializa o DAO do hábito.
     */
    public HabitoController() {
        this.habitoDAO = HabitoDAO.getInstancia();
    }

    /**
     * Define o hábito que será controlado por esta instância.
     *
     * @param h o hábito a ser manipulado.
     * @throws IllegalArgumentException se o hábito for {@code null}.
     */
    public void setHabito(Habito h) {
        if (h == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        this.habito = h;
    }

    /**
     * Marca o hábito como concluído e atualiza o DAO.
     * Deve ser chamado apenas se {@code habito} estiver definido.
     */
    public void concluirHabito() {
        habito.marcarComoConcluido();
        habitoDAO.atualizar(habito);
    }

    /**
     * Verifica se o hábito atual já foi concluído.
     *
     * @return {@code true} se concluído, {@code false} caso contrário.
     */
    public boolean isConcluido() {
        return habito.isConcluido();
    }

    /**
     * Atualiza os dados principais do hábito e persiste a alteração.
     *
     * @param n novo nome do hábito.
     * @param c nova categoria do hábito.
     * @param p nova prioridade do hábito.
     * @param r nova recorrência do hábito.
     * @throws IllegalArgumentException se algum parâmetro for {@code null}.
     */
    public void atualizarHabito(String n, Categoria c, Prioridade p, Recorrencia r) {
        if (n == null) throw new IllegalArgumentException("Nome não pode ser nulo");
        if (c == null) throw new IllegalArgumentException("Categoria não pode ser nula");
        if (p == null) throw new IllegalArgumentException("Prioridade não pode ser nula");
        if (r == null) throw new IllegalArgumentException("Recorrência não pode ser nula");

        habito.setNome(n);
        habito.setCategoria(c);
        habito.setPrioridade(p);
        habito.setRecorrencia(r);
        habitoDAO.atualizar(habito);
    }

    /**
     * Reseta o streak (sequência de dias) do hábito para zero.
     * Atualiza o estado no DAO.
     */
    public void resetarStreak() {
        habito.setStreak(0);
        habitoDAO.atualizar(habito);
    }

    /**
     * Retorna um resumo do hábito atual.
     *
     * @return {@code String} com os dados formatados do hábito, ou mensagem de erro se não houver hábito.
     */
    public String exibirResumo() {
        return habito != null ? habito.toString() : "Nenhum hábito disponível";
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.habito);
        hash = 53 * hash + Objects.hashCode(this.habitoDAO);
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
        final HabitoController other = (HabitoController) obj;
        if (!Objects.equals(this.habito, other.habito)) {
            return false;
        }
        return Objects.equals(this.habitoDAO, other.habitoDAO);
    }
    
    
    /**
     * Retorna o hábito atualmente vinculado a este controller.
     *
     * @return o objeto {@link Habito} atual.
     */
    
    public Habito getHabito() {
        return habito;
    }
}
