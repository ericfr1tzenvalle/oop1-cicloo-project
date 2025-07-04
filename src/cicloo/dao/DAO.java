package cicloo.dao;

import java.util.List;

/**
 * Interface genérica para operações básicas de persistência de dados.
 * Define os métodos CRUD (Create, Read, Update, Delete) que devem ser
 * implementados pelas classes DAO específicas.
 *
 * @param <T> o tipo de objeto que será manipulado pela DAO.
 * 
 * @author Eric
 */
public interface DAO<T> {

    /**
     * Salva um novo objeto no repositório.
     *
     * @param t o objeto a ser salvo.
     */
    void salvar(T t);

    /**
     * Busca um objeto pelo seu ID.
     *
     * @param id o identificador único do objeto.
     * @return o objeto correspondente, ou {@code null} se não encontrado.
     */
    T buscarPorId(int id);

    /**
     * Retorna todos os objetos do repositório.
     *
     * @return uma lista com todos os objetos.
     */
    List<T> buscarTodos();

    /**
     * Atualiza um objeto existente no repositório.
     *
     * @param t o objeto com os dados atualizados.
     */
    void atualizar(T t);

    /**
     * Remove um objeto do repositório com base no ID.
     *
     * @param id o identificador do objeto a ser removido.
     */
    void deletar(int id);
    
    
    
}
