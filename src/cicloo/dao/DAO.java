/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cicloo.dao;
import java.util.List;
/**
 *
 * @author Eric
 */
public interface DAO<T> {
    void salvar(T t);
    T buscarPorId(int id);
    List<T> buscarTodos();
    void atualizar(T t);
    void deletar(int id);
    
}
