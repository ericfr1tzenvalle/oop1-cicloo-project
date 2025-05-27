/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.dao;

import cicloo.model.Usuario;
import java.util.List;

/**
 *
 * @author Eric
 */
public class UsuarioDAO implements DAO<Usuario> {

    @Override
    public void salvar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Usuario buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Usuario> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void atualizar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
