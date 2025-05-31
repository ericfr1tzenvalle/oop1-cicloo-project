package cicloo.controller;

import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;

public class HabitoController {

    private Habito habito;

    public HabitoController(Habito habito) {
        if (habito == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        this.habito = habito;
    }

    public void concluirHabito() {
        habito.marcarComoConcluido();
    }

    public boolean isConcluido() {
        return habito.isConcluido();
    }

    public void atualizarHabito(String n, Categoria c, Prioridade p, Recorrencia r) {
        if (n != null && c != null && p != null && r != null) {
            Habito habitoAtualizado = new Habito(n, c, r, p);
            habito.atualizarCamposBasicos(habitoAtualizado);
        }
    }

    public void resetarStreak() {
        habito.setStreak(0);
    }

    public void exibirResumo() {
        System.out.println(habito);
    }

    public Habito getHabito() {
        return habito;
    }
}
