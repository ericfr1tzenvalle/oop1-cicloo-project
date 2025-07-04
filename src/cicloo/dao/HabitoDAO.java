package cicloo.dao;

import cicloo.model.Habito;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por gerenciar a persistência em memória dos objetos {@link Habito}.
 * Implementa o padrão Singleton e a interface genérica {@code DAO<Habito>}.
 * 
 * Esta DAO simula um banco de dados armazenando hábitos em uma lista.
 * Ideal para testes ou sistemas sem persistência em disco.
 * 
 * @author Eric
 */
public class HabitoDAO implements DAO<Habito> {

    /**
     * Instância única da classe (padrão Singleton).
     */
    private static HabitoDAO instancia;

    /**
     * Lista que armazena os hábitos em memória.
     */
    private List<Habito> habitos = new ArrayList<>();

    /**
     * Contador utilizado para gerar IDs únicos para os hábitos.
     */
    private int contador = 0;

    /**
     * Construtor privado para impedir múltiplas instâncias (Singleton).
     */
    private HabitoDAO() {}

    /**
     * Retorna a instância única da classe {@code HabitoDAO}.
     * 
     * @return a instância única.
     */
    public static HabitoDAO getInstancia() {
        if (instancia == null) {
            instancia = new HabitoDAO();
        }
        return instancia;
    }

    /**
     * Salva um novo hábito na lista e atribui um ID único a ele.
     *
     * @param h o hábito a ser salvo.
     * @throws IllegalArgumentException se o hábito for {@code null}.
     */
    @Override
    public void salvar(Habito h) {
        if (h == null) {
            throw new IllegalArgumentException("Habito não pode ser nulo");
        }
        h.setId(contador++);
        habitos.add(h);
    }

    /**
     * Busca um hábito pelo seu ID.
     *
     * @param id o ID do hábito.
     * @return o hábito correspondente ou {@code null} se não encontrado.
     */
    @Override
    public Habito buscarPorId(int id) {
        if (id < 0) {
            return null;
        }
        for (Habito h : habitos) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    /**
     * Retorna todos os hábitos armazenados.
     *
     * @return uma nova lista contendo todos os hábitos.
     */
    @Override
    public List<Habito> buscarTodos() {
        return new ArrayList<>(habitos);
    }

    /**
     * Atualiza os dados básicos de um hábito existente.
     *
     * @param habitoAtualizar o hábito com os novos dados.
     * @throws IllegalArgumentException se o hábito for {@code null}.
     */
    @Override
    public void atualizar(Habito habitoAtualizar) {
        if (habitoAtualizar == null) {
            throw new IllegalArgumentException("Habito não pode ser nulo");
        }
        for (Habito h : habitos) {
            if (h.getId() == habitoAtualizar.getId()) {
                h.atualizarCamposBasicos(habitoAtualizar);
                return;
            }
        }
    }


    /**
     * Remove um hábito da lista com base no seu ID.
     *
     * @param id o ID do hábito a ser removido.
     */
    @Override
    public void deletar(int id) {
        if (id < 0) {
            return;
        }
        habitos.removeIf(h -> h.getId() == id);
    }
}
