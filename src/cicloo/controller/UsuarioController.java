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
    
    public void adicionarHabito(Habito h){
       usuario.getHabito().add(h);
    }
   
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
   
    public Habito buscarHabitoPeloNome(String nome){
        ArrayList<Habito> habitos = usuario.getHabito();
        for(Habito h: habitos){
            if(h.getNome().equalsIgnoreCase(nome)){
                return h;
            }
        }
        return null;
    }
   
    public ArrayList<Habito> listarHabitos(){
        return usuario.getHabito();
    }
   
    
    public void atualizarUsuario(String nome, String email, String senha){
        if(nome != null && email != null && senha != null){
            this.usuario.setNome(nome);
            this.usuario.setEmail(email);
            this.usuario.setSenha(senha);
        }
        
    }
   
    public boolean verificaSenha(String senha){
        if(usuario.getSenha().equals(senha)){
            return true;
        }
        return false;
    }
   
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
