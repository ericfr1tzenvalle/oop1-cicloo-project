package cicloo.dao;

import cicloo.model.Habito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class HabitoDAO implements DAO<Habito> {

    private List<Habito> habitos = new ArrayList<>();
    private int contador = 0;

    @Override
    public void salvar(Habito h) {
        if (h == null) {
            throw new IllegalArgumentException("Habito não pode ser nulo");
        }
        h.setId(contador++);
        habitos.add(h);
    }

    @Override
    public Habito buscarPorId(int id) {
        if (id < 0) {
            return null;
        }
        for (Habito h : habitos) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    @Override
    public List<Habito> buscarTodos() {
        return new ArrayList<>(habitos);
    }

    @Override
    public void atualizar(Habito habitoAtualizar) {
        if (habitoAtualizar == null) {
            throw new IllegalArgumentException("Habito não pode ser nulo");
            
        }
        for (Habito h : habitos) {
            if (h.getId() == habitoAtualizar.getId()) {
                h.atualizarCamposBasicos(habitoAtualizar);
                return;
            }
        }
    }

    @Override
    public void deletar(int id) {
        if (id < 0) {
            return;
        }
        habitos.removeIf(h -> h.getId() == id);
    }
}
