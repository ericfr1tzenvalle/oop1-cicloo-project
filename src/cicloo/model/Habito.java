/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cicloo.model;

import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;
import java.time.LocalDate;

/**
 *
 * @author 2024020020
 */
public class Habito {
    private String nome;
    private Categoria categoria;
    private Recorrencia recorrencia;
    private Prioridade prioridade;
    private LocalDate ultimaConclusao;
    private int level;
    private boolean concluido;
    private int xp;
    private int streak;

    public Habito(String nome, Categoria categoria, Recorrencia recorrencia, Prioridade prioridade) {
        setNome(nome);
        setCategoria(categoria);
        setRecorrencia(recorrencia);
        setPrioridade(prioridade);
        
        this.concluido = false;
        this.xp = 0;
        this.streak = 0;
        this.level = 1;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getUltimaConclusao() {
        return ultimaConclusao;
    }

    private void setUltimaConclusao(LocalDate ultimaConclusao) {
        this.ultimaConclusao = ultimaConclusao;
    }
    
    public void marcarComoConcluido(){
        LocalDate hoje = LocalDate.now();
        if(ultimaConclusao == null || !ultimaConclusao.equals(hoje)){
            streak++;
        }
        ultimaConclusao = hoje;
        xp += 10;
        concluido = true;
    }
    
    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
        throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if(nome.length() < 3){
        throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        }
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if(categoria == null){
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        this.categoria = categoria;
    }

    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Recorrencia recorrencia) {
       if(recorrencia == null){
            throw new IllegalArgumentException("Recorrencia nao pode ser nula");
        }
        this.recorrencia = recorrencia;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        if(prioridade == null){
            throw new IllegalArgumentException("Prioridade nao pode ser nula");
        }
        this.prioridade = prioridade;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
    
    @Override
public String toString() {
    return "Habito{" +
            "nome='" + nome + '\'' +
            ", categoria=" + categoria +
            ", recorrencia=" + recorrencia +
            ", prioridade=" + prioridade +
            ", ultimaConclusao=" + (ultimaConclusao != null ? ultimaConclusao : "Nunca") +
            ", level=" + level +
            ", concluido=" + concluido +
            ", xp=" + xp +
            ", streak=" + streak +
            '}';
}
    
}
