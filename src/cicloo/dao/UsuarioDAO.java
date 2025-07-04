/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.dao;

import cicloo.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eric
 */
public class UsuarioDAO implements DAO<Usuario> {
    private static UsuarioDAO instancia;
    private List<Usuario> usuarios = new ArrayList<>();
    private int contador = 0;
    private UsuarioDAO(){}
    
    public static UsuarioDAO getInstancia(){
        if(instancia == null){
            instancia = new UsuarioDAO();
        }
        return instancia;
    }
    @Override
    public void salvar(Usuario u) {
        if (u == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
            
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(u.getEmail())) {
                return;
            }
        }

        u.setId(contador++);
        usuarios.add(u);

    }

    @Override
    public Usuario buscarPorId(int id) {
        if(id < 0){
            return null;
        }
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    public Usuario buscarPorNome(String nome){
        if(nome.trim().isEmpty()){
            return null;
        }
        for(Usuario u: usuarios){
            if(u.getNome().toLowerCase().equals(nome.toLowerCase())){
                return u;
            }
        }
        return null;
    }
    public Usuario buscarPorEmail(String email){
        if(email.trim().isEmpty()){
            return null;
        }
        for(Usuario u: usuarios){
            if(u.getEmail().toLowerCase().equals(email)){
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
        if (usuarioAtualizar == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        }
        for (Usuario u : usuarios) {
            if (u.getId() == usuarioAtualizar.getId()) {
                u.atualizarCampos(usuarioAtualizar);
                return;
            }
        }
    }

    @Override
    public void deletar(int id) {
        if (id < 0) {
            return;
        }
        usuarios.removeIf(u -> u.getId() == id);
    }
    

}
