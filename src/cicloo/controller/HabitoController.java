

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

    private Habito habito;
    private HabitoDAO habitoDAO;

    public HabitoController(Habito habito, HabitoDAO habitoDAO) {
         if (habito == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        this.habito = habito;
        this.habitoDAO = habitoDAO;
    }

   

    public void concluirHabito() {
        habito.marcarComoConcluido();
        habitoDAO.atualizar(habito);
    }

    public boolean isConcluido() {
        return habito.isConcluido();
    }

    public void atualizarHabito(String n, Categoria c, Prioridade p, Recorrencia r) {
    if (n != null) habito.setNome(n);
    if (c != null) habito.setCategoria(c);
    if (p != null) habito.setPrioridade(p);
    if (r != null) habito.setRecorrencia(r);
    habitoDAO.atualizar(habito);
    
    }

    public void resetarStreak() {
        habito.setStreak(0);
        habitoDAO.atualizar(habito);
    }

    public String exibirResumo() {
        return habito.toString();
    }

    public Habito getHabito() {
        return habito;
    }
}
