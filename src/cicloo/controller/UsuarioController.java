/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.controller;

import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class UsuarioController {
    private Usuario usuario;
    
    public UsuarioController(Usuario usuario){
    this.usuario = usuario;
    }
    // TODO: Criar método adicionarHabito(Habito habito)// Usuario que gerencia os habitos
    public void adicionarHabito(Habito h){
       usuario.getHabito().add(h);
    }
    // TODO: Criar método removerHabito(Habito habito ou int id)
    public void removerHabito(String nome){
        ArrayList<Habito> habitos = usuario.getHabito();
        Habito habitoParaRemover = null;
        
        for(Habito h: habitos){
            if(h.getNome().equalsIgnoreCase(nome)){
                habitoParaRemover = h;
                break;
                
            }
        }
        
        if(habitoParaRemover != null){
            habitos.remove(habitoParaRemover);
        }
    }
    // TODO: Criar método para buscar um hábito pelo ID ou nome // Metodo pra localizar habito
    public Habito buscarHabitoPeloNome(String nome){
        ArrayList<Habito> habitos = usuario.getHabito();
        for(Habito h: habitos){
            if(h.getNome().equalsIgnoreCase(nome)){
                return h;
            }
        }
        return null;
    }
    // TODO: Criar método para listar todos os hábitos do usuário // Todos os habitos dele
    public ArrayList<Habito> listarHabitos(){
        return usuario.getHabito();
    }
    // TODO: Criar método para atualizar informações do usuário (nome, email, senha) 
    
    public void atualizarUsuario(String nome, String email, String senha){
        if(nome != null && email != null && senha != null){
            this.usuario.setNome(nome);
            this.usuario.setEmail(email);
            this.usuario.setSenha(senha);
        }
        
    }
    // TODO: Criar método de autenticação/validação de senha // Uma simples autenticação de senha
    //nao sei
    public boolean verificaSenha(String senha){
        if(usuario.getSenha().equals(senha)){
            return true;
        }
        return false;
    }
    // TODO: Criar método para calcular a quantidade de hábitos concluídos
    public int quantidadeDeHabitosConcluidos(){
        ArrayList<Habito> habitos = usuario.getHabito();
        int contador = 0;
        for(Habito h: habitos){
            if(h.isConcluido() == true){
                contador++;
            }
        }
        return contador;
    }
    // TODO: Criar método para calcular streaks ou progresso geral // Esse provavelmente no Habito.
}
