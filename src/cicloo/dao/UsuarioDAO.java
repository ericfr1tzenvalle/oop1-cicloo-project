/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.dao;

import cicloo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class UsuarioDAO implements DAO<Usuario> {
    
    private List<Usuario> usuarios = new ArrayList<>();
    private int contador = 0;

    @Override
    public void salvar(Usuario u) {
        if(u == null) return;
        for(Usuario usuario: usuarios){
            if(usuario.getEmail().equals(u.getEmail())){
                return;
            }
        }
        
        u.setId(contador++);
        usuarios.add(u);
        
    }

    @Override
    public Usuario buscarPorId(int id) {
        for(Usuario u: usuarios){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public void atualizar(Usuario usuarioAtualizar) {
        if(usuarioAtualizar == null) return;
        for(Usuario u: usuarios){
            if(u.getId() == usuarioAtualizar.getId()){
                u.atualizarCampos(usuarioAtualizar);
                return;
            }
        }
    }

    @Override
    public void deletar(int id) {
        if(id < 0) return;
        for(Usuario u: usuarios){
            if(u.getId() == id){
                usuarios.remove(u);
                return;
            }
        }
    }
    
}
