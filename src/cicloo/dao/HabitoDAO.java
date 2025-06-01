/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        if (h != null) {
            h.setId(contador++);
            habitos.add(h);
        }

    }

    @Override
    public Habito buscarPorId(int id) {
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
            return;
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
