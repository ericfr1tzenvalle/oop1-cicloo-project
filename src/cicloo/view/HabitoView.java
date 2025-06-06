package cicloo.view;

import cicloo.model.Usuario;
import cicloo.dao.HabitoDAO;

import java.util.Scanner;

public class HabitoView {
    private final Scanner sc = new Scanner(System.in);
    private final Usuario usuario;
    private final HabitoDAO habitoDAO = HabitoDAO.getInstancia();

    public HabitoView(Usuario usuario) {
        this.usuario = usuario;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu Hábitos ---");
            System.out.println("Usuário: " + usuario.getNome());
            System.out.println("1. Criar Hábito");
            System.out.println("2. Listar Hábitos");
            System.out.println("3. Atualizar Hábito");
            System.out.println("4. Concluir Hábito");
            System.out.println("5. Deletar Hábito");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarHabito();
                case 2 -> listarHabitos();
                case 3 -> atualizarHabito();
                case 4 -> concluirHabito();
                case 5 -> deletarHabito();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void criarHabito() {
        System.out.println("Criando hábito para o usuário: " + usuario.getNome());
    }

    private void listarHabitos() {
        System.out.println("Listando hábitos do usuário: " + usuario.getNome());
    }

    private void atualizarHabito() {
        System.out.println("Atualizar hábito (implementar...)");
    }

    private void concluirHabito() {
        System.out.println("Concluir hábito (implementar...)");
    }

    private void deletarHabito() {
        System.out.println("Deletar hábito (implementar...)");
    }
}
