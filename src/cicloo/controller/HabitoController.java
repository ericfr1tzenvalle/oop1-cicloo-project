/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.controller;

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
  
    public HabitoController(Habito habito) {
        this.habito = habito;
    }

    public void concluirHabito() {
        habito.marcarComoConcluido();
    }

    public boolean verificaConclusao() {
        return habito.isConcluido();
    }

    public void atualizarHabito(String n, Categoria c, Prioridade p, Recorrencia r) {
        if (n != null && c != null && p != null && r != null) {
            habito.setCategoria(c);
            habito.setNome(n);
            habito.setPrioridade(p);
            habito.setRecorrencia(r);
        }
    }

    public void exibirResumo() {
        System.out.println(habito);
    }
    
    public void resetarStreak() {
        habito.setStreak(0);
    }
}
