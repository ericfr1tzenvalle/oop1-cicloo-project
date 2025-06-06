package cicloo.view;

import cicloo.model.Usuario;
import java.util.Scanner;

public class UsuarioView {
    private final Scanner sc = new Scanner(System.in);
    private final Usuario usuario;

    public UsuarioView(Usuario usuario) {
        this.usuario = usuario;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu Usuário ---");
            System.out.println("Usuário logado: " + usuario.getNome()); 
            System.out.println("1. Ver Perfil");
            System.out.println("2. Estatísticas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> exibirPerfil();
                case 2 -> exibirEstatisticas();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirPerfil() {
        System.out.println("Nome: " + usuario.getNome());
        // Outras infos do usuário
    }

    private void exibirEstatisticas() {
        System.out.println("Exibindo estatísticas para o usuário " + usuario.getNome());
        // implementar estatísticas
    }
}
