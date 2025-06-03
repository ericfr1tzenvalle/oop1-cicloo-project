package cicloo.controller;

import cicloo.dao.HabitoDAO;
import cicloo.dao.UsuarioDAO;
import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.List;

/**
 * @author Eric
 */

public class UsuarioController {

    private Usuario usuario;
    private HabitoDAO habitoDAO;
    private UsuarioDAO usuarioDAO;

    public UsuarioController(Usuario usuario, HabitoDAO habitoDAO, UsuarioDAO usuarioDAO) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        this.usuario = usuario;
        this.habitoDAO = habitoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public void adicionarHabito(Habito h) throws IllegalArgumentException {
        if (h == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        usuario.adicionarHabito(h);
        habitoDAO.salvar(h);
    }

    public void removerHabitoPorId(int id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        Habito habito = buscarHabitoPorId(id);
        if (habito == null) {
            throw new IllegalArgumentException("Hábito não encontrado com ID: " + id);
        }
        usuario.removerHabitoPorId(id);
        habitoDAO.deletar(id);
    }

    public Habito buscarHabitoPorId(int id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        Habito habito = usuario.buscarHabitoPorId(id);
        if (habito == null) {
            throw new IllegalArgumentException("Hábito não encontrado com ID: " + id);
        }
        return habito;
    }

    public Habito buscarHabitoPeloNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        Habito habito = usuario.buscarHabito(nome);
        if (habito == null) {
            throw new IllegalArgumentException("Hábito não encontrado com nome: " + nome);
        }
        return habito;
    }

    public List<Habito> listarHabitos() {
        return usuario.listarHabitos();
    }

    public void atualizarUsuario(Usuario u) throws IllegalArgumentException {
        if (u == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (usuarioDAO.buscarPorId(u.getId()) == null) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + u.getId());
        }
        usuario.atualizarCampos(u);
        usuarioDAO.atualizar(u);
    }

    public boolean verificaSenha(String senha) throws IllegalArgumentException {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha inválida");
        }
        return usuario.getSenha() != null && usuario.getSenha().equals(senha);
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
