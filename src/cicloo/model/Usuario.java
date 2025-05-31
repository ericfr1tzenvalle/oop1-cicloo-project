/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model;

import cicloo.model.Habito;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 2024020020
 */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Habito> habitos;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.habitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
//Verificações: se o nome é nulo ou vazio e se tem pelo menos 3 caracteres.

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
//Verificações: se o email é nulo ou é vazio e se passa no formato esperado.

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

    public String getSenha() {
        return senha;
    }
//Verificamos se a senha é nula, se tem pelo menos 6 caracteres e se contem letras e numeros.

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

    public void adicionarHabito(Habito h) {
        if (h != null) {
            habitos.add(h);
        }
    }

    public void removerHabito(String nome) {
        habitos.removeIf(h -> h.getNome().equalsIgnoreCase(nome));
    }

    public Habito buscarHabito(String nome) {
        for (Habito h : habitos) {
            if (h.getNome().equalsIgnoreCase(nome)) {
                return h;
            }
        }
        return null;
    }

    public List<Habito> listarHabitos() {
        return Collections.unmodifiableList(habitos);
    }
    
    public void atualizarCampos(Usuario outro){
        setNome(outro.getNome());
        setEmail(outro.getEmail());
        setSenha(outro.getSenha());
    }
    public boolean removerHabitoPorId(int id){
         return habitos.removeIf(h -> h.getId() == id);
    }
    public Habito buscarHabitoPorId(int id){
        for(Habito h: habitos){
            if(h.getId() == id){
                return h;
            }
        }
        return null;
    }
}
