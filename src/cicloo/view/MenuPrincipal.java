package cicloo.view;

import cicloo.model.Usuario;
import cicloo.dao.UsuarioDAO;

import java.util.Scanner;

public class MenuPrincipal {
    private static final Scanner sc = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Cicloo!");
        Usuario u1 = new Usuario("Eric", "ericfritzenvalle@Hotmail.com", "123456789a");
        usuarioDAO.salvar(u1);
        Usuario usuarioLogado = fazerLogin();

        if (usuarioLogado == null) {
            System.out.println("Usuário não encontrado. Encerrando o programa.");
            return;
        }

        int opcao = -1;
        do {
            System.out.println("\n<--- Menu Principal --->");
            System.out.println("1. Usuário");
            System.out.println("2. Hábitos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    UsuarioView usuarioView = new UsuarioView(usuarioLogado);
                    usuarioView.exibirMenu();
                }
                case 2 -> {
                    HabitoView habitoView = new HabitoView(usuarioLogado);
                    habitoView.exibirMenu();
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    private static Usuario fazerLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String nome = sc.nextLine();

        Usuario usuario = usuarioDAO.buscarPorNome(nome);

        return usuario;
    }
}
