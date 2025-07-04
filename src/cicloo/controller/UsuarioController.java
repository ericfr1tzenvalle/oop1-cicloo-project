package cicloo.controller;

import cicloo.model.Conquista;
import cicloo.dao.HabitoDAO;
import cicloo.dao.UsuarioDAO;
import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.List;
import java.util.Objects;

/**
 * Controlador responsável pela lógica de negócio relacionada ao usuário.
 * Gerencia hábitos, conquistas e estatísticas do usuário.
 * Atua como intermediário entre o modelo {@link Usuario} e a camada de visualização.
 * 
 * Utiliza as classes {@link HabitoDAO} e {@link UsuarioDAO} para persistência em memória.
 * 
 * @author Eric
 */
public class UsuarioController {

    private Usuario usuario;
    private HabitoDAO habitoDAO;
    private UsuarioDAO usuarioDAO;

    /**
     * Construtor que recebe o usuário logado.
     *
     * @param usuario o usuário logado no sistema.
     * @throws IllegalArgumentException se o usuário for {@code null}.
     */
    public UsuarioController(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        this.usuario = usuario;
        this.habitoDAO = HabitoDAO.getInstancia();
        this.usuarioDAO = UsuarioDAO.getInstancia();
    }

    /**
     * Adiciona um novo hábito ao usuário e o salva no DAO.
     *
     * @param h o hábito a ser adicionado.
     * @throws IllegalArgumentException se o hábito for {@code null}.
     */
    public void adicionarHabito(Habito h) throws IllegalArgumentException {
        if (h == null) {
            throw new IllegalArgumentException("Hábito não pode ser nulo");
        }
        usuario.adicionarHabito(h);
        habitoDAO.salvar(h);
    }

    /**
     * Verifica e reseta streaks vencidos em todos os hábitos do usuário.
     */
    public void verificarStreaks() {
        for (Habito h : this.usuario.listarHabitos()) {
            h.verificarEresetarStreak();
        }
    }

    /**
     * Verifica todas as conquistas do sistema e desbloqueia as que o usuário alcançou.
     * São consideradas as conquistas padrão do Cicloo.
     */
    public void verificarEdesbloquearConquistas() {
        Conquista primeiroPasso = buscarConquista("Primeiro Passo");
        if (primeiroPasso != null && !primeiroPasso.isDesbloqueada()) {
            if (!usuario.listarHabitos().isEmpty()) {
                primeiroPasso.desbloquear();
                notificarDesbloqueio(primeiroPasso);
            }
        }

        Conquista colecionador = buscarConquista("Colecionador");
        if (colecionador != null && !colecionador.isDesbloqueada()) {
            if (usuario.listarHabitos().size() >= 5) {
                colecionador.desbloquear();
                notificarDesbloqueio(colecionador);
            }
        }

        for (Habito habito : usuario.listarHabitos()) {
            Conquista persistencia = buscarConquista("Persistencia de Aco");
            if (persistencia != null && !persistencia.isDesbloqueada()) {
                if (habito.getTotalConclusoes() >= 5) {
                    persistencia.desbloquear();
                    notificarDesbloqueio(persistencia);
                }
            }

            Conquista mestreRotina = buscarConquista("Mestre da Rotina");
            if (mestreRotina != null && !mestreRotina.isDesbloqueada()) {
                if (habito.getStreak() >= 7) {
                    mestreRotina.desbloquear();
                    notificarDesbloqueio(mestreRotina);
                }
            }

            Conquista novatoPromissor = buscarConquista("Novato Promissor");
            if (novatoPromissor != null && !novatoPromissor.isDesbloqueada()) {
                if (habito.getLevel() >= 5) {
                    novatoPromissor.desbloquear();
                    notificarDesbloqueio(novatoPromissor);
                }
            }
        }
    }

    /**
     * Busca uma conquista específica na lista do usuário.
     *
     * @param nome o nome da conquista.
     * @return a conquista correspondente ou {@code null} se não encontrada.
     */
    private Conquista buscarConquista(String nome) {
        for (Conquista c : usuario.getConquistas()) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Exibe uma notificação no console sobre a conquista desbloqueada.
     *
     * @param conquista a conquista que foi desbloqueada.
     */
    private void notificarDesbloqueio(Conquista conquista) {
        System.out.println("\n*********************************");
        System.out.println("  CONQUISTA DESBLOQUEADA!");
        System.out.println("  " + conquista.getNome());
        System.out.println("  " + conquista.getDescricao());
        System.out.println("*********************************");
    }

    /**
     * Calcula a soma total de XP de todos os hábitos do usuário.
     *
     * @return a quantidade total de experiência (XP).
     */
    public int getTotalXP() {
        int totalXP = 0;
        for (Habito h : usuario.listarHabitos()) {
            if (h.getXp() > 0) {
                totalXP += h.getXp();
            }
        }
        return totalXP;
    }

    /**
     * Retorna o hábito com maior nível entre os do usuário.
     *
     * @return o hábito de maior nível ou {@code null} se não houver hábitos.
     */
    public Habito getHabitoMaiorNivel() {
        List<Habito> habitos = usuario.listarHabitos();
        if (habitos.isEmpty()) {
            return null;
        }
        Habito habitoMaiorNivel = habitos.get(0);
        for (Habito h : habitos) {
            if (h.getLevel() > habitoMaiorNivel.getLevel()) {
                habitoMaiorNivel = h;
            }
        }
        return habitoMaiorNivel;
    }

    /**
     * Retorna o hábito com maior streak atual.
     *
     * @return o hábito com maior sequência de dias ou {@code null} se não houver hábitos.
     */
    public Habito getHabitoMaiorStreak() {
        List<Habito> habitos = usuario.listarHabitos();
        if (habitos.isEmpty()) {
            return null;
        }
        Habito habitoMaiorStreak = habitos.get(0);
        for (Habito h : habitos) {
            if (h.getStreak() > habitoMaiorStreak.getStreak()) {
                habitoMaiorStreak = h;
            }
        }
        return habitoMaiorStreak;
    }

    /**
     * Remove um hábito do usuário com base no ID.
     *
     * @param id o identificador do hábito.
     * @throws IllegalArgumentException se o ID for inválido ou hábito não encontrado.
     */
    public void removerHabitoPorId(int id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        Habito habitoParaRemover = buscarHabitoPorId(id);

        if (habitoParaRemover == null) {
            throw new IllegalArgumentException("Hábito não encontrado com ID: " + id);
        }

        habitoDAO.deletar(id);
        usuario.removerHabito(habitoParaRemover);
    }

    /**
     * Busca um hábito do usuário pelo ID.
     *
     * @param id o identificador do hábito.
     * @return o hábito correspondente ou {@code null} se não encontrado.
     */
    public Habito buscarHabitoPorId(int id) {
        if (id < 0) {
            return null;
        }

        for (Habito h : usuario.listarHabitos()) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    /**
     * Busca um hábito do usuário pelo nome (case-insensitive).
     *
     * @param nome o nome do hábito.
     * @return o hábito correspondente.
     * @throws IllegalArgumentException se o nome for inválido ou não encontrado.
     */
    public Habito buscarHabitoPeloNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        for (Habito h : usuario.listarHabitos()) {
            if (h.getNome().equalsIgnoreCase(nome)) {
                return h;
            }
        }
        throw new IllegalArgumentException("Hábito não encontrado com nome: " + nome);
    }

    /**
     * Retorna a lista de todos os hábitos do usuário.
     *
     * @return lista de hábitos.
     */
    public List<Habito> listarHabitos() {
        return usuario.listarHabitos();
    }

    /**
     * Atualiza os dados de um usuário.
     *
     * @param u o objeto {@link Usuario} atualizado.
     * @throws IllegalArgumentException se o usuário for inválido ou não existir no DAO.
     */
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

    /**
     * Verifica se a senha informada confere com a do usuário logado.
     *
     * @param senha a senha a ser verificada.
     * @return {@code true} se a senha for válida, senão {@code false}.
     * @throws IllegalArgumentException se a senha for nula ou vazia.
     */
    public boolean verificaSenha(String senha) throws IllegalArgumentException {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha inválida");
        }
        return usuario.getSenha() != null && usuario.getSenha().equals(senha);
    }

    /**
     * Retorna a quantidade de hábitos ainda não concluídos pelo usuário.
     *
     * @return número de hábitos pendentes.
     */
    
    
    
    
    public int quantidadeDeHabitosConcluidos() {
        int contador = 0;
        for (Habito h : usuario.listarHabitos()) {
            if (!h.isConcluido()) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.habitoDAO);
        hash = 67 * hash + Objects.hashCode(this.usuarioDAO);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioController other = (UsuarioController) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.habitoDAO, other.habitoDAO)) {
            return false;
        }
        return Objects.equals(this.usuarioDAO, other.usuarioDAO);
    }
    
    
    
}
