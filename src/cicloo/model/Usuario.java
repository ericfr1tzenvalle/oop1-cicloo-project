/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model;
import cicloo.model.Habito;
import java.util.ArrayList;

/**
 *
 * @author 2024020020
 */
public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Habito> habitos;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.habitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.contains("@")){
             throw new IllegalArgumentException("Email deve conter @");
        }
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email não pode ser nulo");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Habito> getHabito() {
        return habitos;
    }

    public void setHabito(ArrayList<Habito> habitos) {
        this.habitos = habitos;
    }
    
    
    
}
