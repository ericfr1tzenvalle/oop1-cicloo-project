
package cicloo.model;

import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

/**
 * Classe que representa um hábito dentro do sistema.
 * Cada hábito possui nome, recorrência, prioridade, streak, XP, nível e outras métricas
 * para acompanhar o progresso do usuário.
 * * @author Eric
 */
public class Habito {

    /**
     * Constante base para o cálculo de XP necessário para o próximo nível.
     */
    private static final int XP_BASE_POR_NIVEL = 30;

    /**
     * Identificador único do hábito.
     */
    private int id;
    /**
     * Nome do hábito.
     */
    private String nome;
    /**
     * Categoria à qual o hábito pertence.
     */
    private Categoria categoria;
    /**
     * Frequência com que o hábito deve ser realizado (DIARIA, SEMANAL, etc.).
     */
    private Recorrencia recorrencia;
    /**
     * Nível de prioridade do hábito (BAIXA, MEDIA, ALTA).
     */
    private Prioridade prioridade;
    /**
     * Data da última vez que o hábito foi marcado como concluído.
     */
    private LocalDate ultimaConclusao;
    /**
     * Nível atual do hábito, que aumenta com o ganho de XP.
     */
    private int level;
    /**
     * Status de conclusão do hábito para o período atual.
     */
    private boolean concluido;
    /**
     * Pontos de experiência acumulados para o nível atual.
     */
    private int xp;
    /**
     * Sequência de conclusões consecutivas do hábito.
     */
    private int streak;
    /**
     * Número total de vezes que o hábito foi concluído.
     */
    private int totalConclusoes;

    /**
     * Construtor do hábito.
     *
     * @param nome Nome do hábito.
     * @param categoria Categoria do hábito.
     * @param recorrencia Recorrência (DIARIA, SEMANAL, etc).
     * @param prioridade Prioridade (BAIXA, MÉDIA, ALTA).
     */
    public Habito(String nome, Categoria categoria, Recorrencia recorrencia, Prioridade prioridade) {
        setNome(nome);
        setCategoria(categoria);
        setRecorrencia(recorrencia);
        setPrioridade(prioridade);
        this.concluido = false;
        this.xp = 0;
        this.streak = 0;
        this.level = 1;
        this.totalConclusoes = 0;
    }

    /**
     * Verifica se a sequência (streak) do hábito foi quebrada com base na recorrência e datas.
     * Se for quebrada, a streak é zerada.
     */
    public void verificarEresetarStreak() {
        if (this.ultimaConclusao == null) return;

        LocalDate hoje = LocalDate.now();
        long diasDesdeUltimaConclusao = ChronoUnit.DAYS.between(this.ultimaConclusao, hoje);
        boolean streakQuebrado = switch (this.recorrencia) {
            case DIARIA -> diasDesdeUltimaConclusao > 1;
            case SEMANAL -> diasDesdeUltimaConclusao > 7;
            case MENSAL -> hoje.isAfter(this.ultimaConclusao.plusMonths(1));
            case ANUAL -> hoje.isAfter(this.ultimaConclusao.plusYears(1));
            default -> false;
        };

        if (streakQuebrado) {
            System.out.println("-> Alerta: A sequência (streak) do hábito '" + this.nome + "' foi zerada por inatividade.");
            this.setStreak(0);
        }
    }

    /**
     * Verifica se o hábito pode ser concluído hoje com base em sua recorrência.
     *
     * @return true se puder ser concluído hoje, false caso contrário.
     */
    private boolean podeConcluirHoje() {
        if (this.ultimaConclusao == null) return true;

        LocalDate hoje = LocalDate.now();
        return switch (this.recorrencia) {
            case DIARIA -> !this.ultimaConclusao.isEqual(hoje);
            case SEMANAL -> {
                WeekFields wf = WeekFields.of(Locale.getDefault());
                yield hoje.get(wf.weekOfWeekBasedYear()) != this.ultimaConclusao.get(wf.weekOfWeekBasedYear()) ||
                       hoje.getYear() != this.ultimaConclusao.getYear();
            }
            case MENSAL -> hoje.getMonth() != this.ultimaConclusao.getMonth() || hoje.getYear() != this.ultimaConclusao.getYear();
            case ANUAL -> hoje.getYear() != this.ultimaConclusao.getYear();
            case PERSONALIZADO -> !this.ultimaConclusao.isEqual(hoje);
        };
    }

    /**
     * Marca o hábito como concluído, caso possível, atualizando streak, XP e data.
     */
    public void marcarComoConcluido() {
        if (!podeConcluirHoje()) {
            System.out.println("\nHábito '" + this.nome + "' já foi concluído neste período (" + this.recorrencia.toString() + "). Bom trabalho!");
            return;
        }

        this.streak++;
        this.totalConclusoes++;
        int xpGanho = calcularXpGanho();
        this.xp += xpGanho;
        this.ultimaConclusao = LocalDate.now();

        System.out.println("\nHábito '" + this.nome + "' concluído! Você ganhou " + xpGanho + " XP!");
        verificarEvolucaoDeNivel();
    }

    /**
     * Calcula a quantidade de XP a ser adicionada ao concluir o hábito.
     *
     * @return Quantidade de XP baseada na recorrência.
     */
    private int calcularXpGanho() {
        return switch (this.recorrencia) {
            case DIARIA -> 20;
            case SEMANAL -> 50;
            case MENSAL -> 150;
            case ANUAL, PERSONALIZADO -> 300;
            default -> 10;
        };
    }

    /**
     * Verifica se o hábito evolui de nível e realiza a evolução se aplicável.
     */
    private void verificarEvolucaoDeNivel() {
        int xpNecessario = getXpParaProximoNivel();
        while (this.xp >= xpNecessario) {
            this.level++;
            this.xp -= xpNecessario;
            System.out.println("LEVEL UP! Seu hábito '" + this.nome + "' evoluiu para o Nível " + this.level + "!");
            xpNecessario = getXpParaProximoNivel();
        }
    }

    /**
     * Retorna a quantidade de XP necessária para atingir o próximo nível.
     * Usamos o calculo do level do habito e o xp_base dele.
     * @return XP necessário.
     */
    public int getXpParaProximoNivel() {
        return this.level * XP_BASE_POR_NIVEL;
    }

    /**
     * Atualiza os campos básicos de um hábito com base em outro.
     *
     * @param outro O hábito com dados atualizados.
     */
    public void atualizarCamposBasicos(Habito outro) {
        setNome(outro.getNome());
        setCategoria(outro.getCategoria());
        setPrioridade(outro.getPrioridade());
        setRecorrencia(outro.getRecorrencia());
    }
    

    // Getters e Setters com Javadoc

    /**
     * @return ID do hábito.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do hábito.
     * @param id Novo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Nome do hábito.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do hábito.
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio");
        if (nome.length() < 3) throw new IllegalArgumentException("Nome deve ter ao menos 3 caracteres");
        this.nome = nome;
    }
     /**
     * Busca a categoria do hábito.
     *
     * @return Categoria.
     */

    public Categoria getCategoria() {
        return categoria;
    }
    
    /**
     * Define a categoria do hábito.
     *
     * @param categoria A nova categoria para o hábito.
     */
    public void setCategoria(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("Categoria não pode ser nula");
        this.categoria = categoria;
    }

    /**
     * Retorna a recorrência do hábito.* @return A recorrência do hábito.
     * @return
     */
    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    /**
     * Define a recorrência do hábito.* @param recorrencia A nova recorrência para o hábito.
     * @param recorrencia
     */
    public void setRecorrencia(Recorrencia recorrencia) {
        if (recorrencia == null) throw new IllegalArgumentException("Recorrência não pode ser nula");
        this.recorrencia = recorrencia;
    }
    
    /**
     * Retorna a prioridade do hábito.* @return A prioridade do hábito.
     * @return
     */
    public Prioridade getPrioridade() {
        return prioridade;
    }

    /**
     * Define a prioridade do hábito.* @param prioridade A nova prioridade para o hábito.
     * @param prioridade
     */
    public void setPrioridade(Prioridade prioridade) {
        if (prioridade == null) throw new IllegalArgumentException("Prioridade não pode ser nula");
        this.prioridade = prioridade;
    }

    /**
     * Retorna a data da última conclusão do hábito.* @return A data da última conclusão.
     * @return
     */
    public LocalDate getUltimaConclusao() {
        return ultimaConclusao;
    }

    /**
     * Define a data da última conclusão. Este método é privado para garantir que a data
     * seja atualizada apenas através da lógica de {@link #marcarComoConcluido()}
     * esse método pode ser repensado no futuro pois no atual momento não está sendo usado.
     * * @param ultimaConclusao A data da última conclusão.
     */
    private void setUltimaConclusao(LocalDate ultimaConclusao) {
        this.ultimaConclusao = ultimaConclusao;
    }

    /**
     * Retorna o nível atual do hábito.* @return O nível do hábito.
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Define o nível do hábito.* @param level O novo nível do hábito.
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Verifica se o hábito está pronto para ser concluído no período atual.Retorna 'true' se o hábito ainda não foi concluído no período de sua recorrência,
 e 'false' caso contrário.
     * * @return 'true' se o hábito pode ser concluído, 'false' se já foi concluído no período.
     * @return
     */
    public boolean isConcluido() {
        return podeConcluirHoje();
    }
    
    /**
     * Define o estado de conclusão do hábito.* @param concluido O novo estado de conclusão.
     * @param concluido
     */
    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    /**
     * Retorna os pontos de experiência (XP) atuais do hábito.* @return A quantidade de XP.
     * @return
     */
    public int getXp() {
        return xp;
    }

    /**
     * Define os pontos de experiência (XP) do hábito.* @param xp A nova quantidade de XP.
     * @param xp
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Retorna a sequência (streak) atual de conclusões do hábito.* @return O valor da streak.
     * @return
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Define a sequência (streak) de conclusões do hábito.* @param streak O novo valor da streak.
     * @param streak
     */
    public void setStreak(int streak) {
        this.streak = streak;
    }

    /**
     * @return Quantidade total de vezes que o hábito foi concluído.
     */
    public int getTotalConclusoes() {
        return totalConclusoes;
    }
    
    /**
     * Gera um código hash para o objeto Habito.
     * * @return O código hash.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.categoria);
        hash = 19 * hash + Objects.hashCode(this.recorrencia);
        hash = 19 * hash + Objects.hashCode(this.prioridade);
        hash = 19 * hash + Objects.hashCode(this.ultimaConclusao);
        hash = 19 * hash + this.level;
        hash = 19 * hash + (this.concluido ? 1 : 0);
        hash = 19 * hash + this.xp;
        hash = 19 * hash + this.streak;
        hash = 19 * hash + this.totalConclusoes;
        return hash;
    }

    /**
     * Compara este Habito com outro objeto para verificar se são iguais.
     * A comparação é feita com base em todos os atributos do hábito.
     * * @param obj O objeto a ser comparado.
     * @return 'true' se os objetos forem iguais, 'false' caso contrário.
     */
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
        final Habito other = (Habito) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.level != other.level) {
            return false;
        }
        if (this.concluido != other.concluido) {
            return false;
        }
        if (this.xp != other.xp) {
            return false;
        }
        if (this.streak != other.streak) {
            return false;
        }
        if (this.totalConclusoes != other.totalConclusoes) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (this.recorrencia != other.recorrencia) {
            return false;
        }
        if (this.prioridade != other.prioridade) {
            return false;
        }
        return Objects.equals(this.ultimaConclusao, other.ultimaConclusao);
    }
    
    /**
     * Retorna uma representação em String do hábito, mostrando seus principais atributos.
     * * @return Uma String formatada com os detalhes do hábito.
     */
    @Override
    public String toString() {
        return " " + nome +
                " | Nível: " + level +
                " | XP: " + xp + "/" + getXpParaProximoNivel() +
                " | Streak: " + streak + 
                " | Recorrência: " + recorrencia.toString()+
                " | Prioridade: " + prioridade.toString() +
                " | Status (Concluído): " + (isConcluido() ? "Não" : "Sim");
    }
    
}


