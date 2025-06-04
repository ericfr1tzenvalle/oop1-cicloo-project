package cicloo.controller;

import cicloo.dao.HabitoDAO;
import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;

/**
 *
 * @author Eric
 */

public class HabitoController {

    private final Habito habito;
    private final HabitoDAO habitoDAO;

    public HabitoController(Habito habito) {
        if (habito == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        this.habito = habito;
        this.habitoDAO = HabitoDAO.getInstancia();  // pega direto o Singleton
    }

    public void concluirHabito() {
        habito.marcarComoConcluido();
        habitoDAO.atualizar(habito);
    }

    public boolean isConcluido() {
        return habito.isConcluido();
    }

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

    public void resetarStreak() {
        habito.setStreak(0);
        habitoDAO.atualizar(habito);
    }

    public String exibirResumo() {
        return habito != null ? habito.toString() : "Nenhum hábito disponível";
    }

    public Habito getHabito() {
        return habito;
    }
}
