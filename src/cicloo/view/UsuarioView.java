package cicloo.view;

import cicloo.controller.UsuarioController;
import cicloo.model.Conquista;
import cicloo.model.Habito;
import cicloo.model.Usuario;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por exibir o menu de opções relacionadas ao usuário,
 * como perfil, estatísticas e conquistas e (Posteriormente será aprimorado com Desafios).
 */
public class UsuarioView {

    private final Scanner sc = new Scanner(System.in);
    private final Usuario usuario;
    private final UsuarioController uController;

    /**
     * Construtor da classe UsuarioView.
     *
     * @param usuario o usuário logado que será utilizado para exibição de informações.
     */
    public UsuarioView(Usuario usuario) {
        this.usuario = usuario;
        this.uController = new UsuarioController(usuario);
    }

    /**
     * Exibe o menu do usuário com opções de visualização de perfil, estatísticas e conquistas.
     * Permanece em loop até o usuário optar por voltar ao menu principal.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("O que você gostaria de ver, " + usuario.getNome() + "?");
            System.out.println("-------------------------");
            System.out.println("1. Ver Meu Perfil");
            System.out.println("2. Ver Minhas Estatísticas");
            System.out.println("3. Ver Minhas Conquistas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1 -> exibirPerfil();
                    case 2 -> exibirEstatisticas();
                    case 3 -> exibirConquistas();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número.");
                sc.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o perfil do usuário logado, incluindo nome, e-mail e hábitos ativos.
     */
    private void exibirPerfil() {
        List<Habito> habitos = uController.listarHabitos();

        System.out.println("\n--- Perfil do Usuário ---");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());

        if (habitos.isEmpty()) {
            System.out.println("Nenhum hábito ativo no momento.");
        } else {
            System.out.println("Total de hábitos ativos: " + habitos.size());
            int contador = 1;
            for (Habito h : habitos) {
                System.out.println(contador++ + ". " + h.getNome());
            }
        }

        aguardarEnter();
    }

    /**
     * Exibe todas as conquistas desbloqueadas pelo usuário.
     */
    private void exibirConquistas() {
        System.out.println("\n--- MINHAS CONQUISTAS ---");
        List<Conquista> conquistas = usuario.getConquistas();

        if (conquistas.isEmpty()) {
            System.out.println("Nenhuma conquista no sistema.");
            aguardarEnter();
            return;
        }

        for (Conquista c : conquistas) {
            System.out.println("- " + c.toString());
        }

        aguardarEnter();
    }

    /**
     * Exibe estatísticas do progresso do usuário como XP total,
     * quantidade de hábitos concluídos, hábito de maior nível e maior streak.
     */
    private void exibirEstatisticas() {
        System.out.println("\n--- Minhas Estatísticas de Progresso ---");

        int totalXP = uController.getTotalXP();
        int concluidosHoje = uController.quantidadeDeHabitosConcluidos();
        Habito maiorNivel = uController.getHabitoMaiorNivel();
        Habito maiorStreak = uController.getHabitoMaiorStreak();

        System.out.println("Pontos de Experiência (XP) Totais: " + totalXP);
        System.out.println("Hábitos Concluídos no Período Atual: " + concluidosHoje);

        if (maiorNivel != null) {
            System.out.println("Seu Hábito de Maior Nível: '" + maiorNivel.getNome() + "' (Nível " + maiorNivel.getLevel() + ")");
        } else {
            System.out.println("Seu Hábito de Maior Nível: Nenhum hábito criado ainda.");
        }

        if (maiorStreak != null && maiorStreak.getStreak() > 0) {
            System.out.println("Seu Recorde de Streak: " + maiorStreak.getStreak() + " dias com o hábito '" + maiorStreak.getNome() + "'");
        } else {
            System.out.println("Seu Recorde de Streak: 0 \nComece uma sequência para registrar seu recorde!");
        }

        System.out.println("----------------------------------------");
        aguardarEnter();
    }

    /**
     * Aguarda o usuário pressionar Enter antes de continuar a execução.
     */
    private void aguardarEnter() {
        System.out.println("\n(Pressione Enter para continuar...)");
        sc.nextLine();
    }
}
