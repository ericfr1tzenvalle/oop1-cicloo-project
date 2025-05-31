package cicloo.controller;

import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.List;

public class UsuarioController {
    private Usuario usuario;

    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void adicionarHabito(Habito h) {
        usuario.adicionarHabito(h);
    }

    
    public boolean removerHabitoPorId(int id) {
        return usuario.removerHabitoPorId(id);
    }

    
    public Habito buscarHabitoPorId(int id) {
        return usuario.buscarHabitoPorId(id);
    }
    
   
    public Habito buscarHabitoPeloNome(String nome) {
        return usuario.buscarHabito(nome);
    }

    public List<Habito> listarHabitos() {
        return usuario.listarHabitos();
    }

    public void atualizarUsuario(Usuario u) {
        usuario.atualizarCampos(u);
    }

    public boolean verificaSenha(String senha) {
        return usuario.getSenha().equals(senha);
    }

    public int quantidadeDeHabitosConcluidos() {
        int contador = 0;
        for (Habito h : usuario.listarHabitos()) {
            if (h.isConcluido()) {
                contador++;
            }
        }
        return contador;
    }
}
