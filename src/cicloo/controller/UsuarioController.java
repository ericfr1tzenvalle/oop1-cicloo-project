
package cicloo.controller;
import cicloo.dao.HabitoDAO;
import cicloo.dao.UsuarioDAO;
import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.List;


/**
 *
 * @author Eric
 */


public class UsuarioController {
    private Usuario usuario;
    private HabitoDAO habitoDAO;
    private UsuarioDAO usuarioDAO;

    public UsuarioController(Usuario usuario, HabitoDAO habitoDAO, UsuarioDAO usuarioDAO) {
        this.usuario = usuario;
        this.habitoDAO = habitoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    
    
    public void adicionarHabito(Habito h) {
        usuario.adicionarHabito(h);
        habitoDAO.salvar(h);
    }

    
    public void removerHabitoPorId(int id) {
       usuario.removerHabitoPorId(id);
       habitoDAO.deletar(id);
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
        usuarioDAO.atualizar(u);
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