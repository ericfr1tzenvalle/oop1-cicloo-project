package cicloo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa um usuário do sistema Cicloo, contendo informações de login,
 * hábitos associados e conquistas desbloqueáveis.
 * Cada usuário pode possuir múltiplos hábitos e conquistas.
 * 
 * @author Eric
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private final ArrayList<Habito> habitos;
    private final List<Conquista> conquistas;

    /**
     * Construtor para criar um novo usuário.
     *
     * @param nome Nome do usuário.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @throws IllegalArgumentException se qualquer campo for inválido.
     */
    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.habitos = new ArrayList<>();
        this.conquistas = new ArrayList<>();
    }

    /**
     * @return Nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome Nome a ser definido (mínimo 3 caracteres).
     * @throws IllegalArgumentException se o nome for inválido.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        }
        this.nome = nome;
    }

    /**
     * @return Email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email Email válido (ex: usuario@dominio.com).
     * @throws IllegalArgumentException se o email for nulo ou inválido.
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
        this.email = email;
    }

    /**
     * @return Senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha Senha com pelo menos 6 caracteres, contendo letras e números.
     * @throws IllegalArgumentException se a senha não atender aos critérios.
     */
    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }
        if (!senha.matches(".*[A-Za-z].*") || !senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Senha deve conter letras e números");
        }
        this.senha = senha;
    }

    /**
     * @return ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do usuário.
     *
     * @param id Novo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Adiciona um hábito à lista do usuário.
     *
     * @param h Hábito a ser adicionado.
     */
    public void adicionarHabito(Habito h) {
        if (h != null) {
            habitos.add(h);
        }
    }

    /**
     * Remove um hábito da lista do usuário.
     *
     * @param h Hábito a ser removido.
     */
    public void removerHabito(Habito h) {
        if (h != null) {
            this.habitos.remove(h);
        }
    }

    /**
     * Lista todos os hábitos do usuário.
     *
     * @return Lista imutável de hábitos.
     */
    public List<Habito> listarHabitos() {
        return Collections.unmodifiableList(habitos);
    }

    /**
     * Carrega as conquistas iniciais do sistema para o usuário.
     *
     * @param conquistasIniciais Lista de conquistas pré-definidas.
     */
    public void carregarConquistasIniciais(List<Conquista> conquistasIniciais) {
        this.conquistas.addAll(conquistasIniciais);
    }

    /**
     * Retorna uma cópia imutável da lista de conquistas do usuário.
     *
     * @return Lista imutável de conquistas.
     */
    public List<Conquista> getConquistas() {
        return Collections.unmodifiableList(this.conquistas);
    }

    /**
     * Atualiza os dados básicos do usuário com base em outro objeto.
     *
     * @param outro Usuário com os novos dados.
     */
    public void atualizarCampos(Usuario outro) {
        setNome(outro.getNome());
        setEmail(outro.getEmail());
        setSenha(outro.getSenha());
    }
    
     /**
     * Gera um código hash para o objeto Usuário.
     * * @return O código hash.
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.senha);
        hash = 59 * hash + Objects.hashCode(this.habitos);
        hash = 59 * hash + Objects.hashCode(this.conquistas);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.habitos, other.habitos)) {
            return false;
        }
        return Objects.equals(this.conquistas, other.conquistas);
    }

    /**
     * Retorna uma representação em String do usuário, mostrando seus principais atributos.
     * * @return Uma String formatada com os detalhes do usuário.
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
    
    
    
}
