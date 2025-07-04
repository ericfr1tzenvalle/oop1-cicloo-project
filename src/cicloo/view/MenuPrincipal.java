package cicloo.view;

import cicloo.controller.UsuarioController;
import cicloo.model.Usuario;
import cicloo.dao.UsuarioDAO;
import cicloo.model.Conquista;
import cicloo.model.Habito;
import cicloo.model.enums.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal responsável por iniciar o sistema Cicloo.
 * Permite o login, cadastro de usuários e direcionamento ao menu principal.
 */
public class MenuPrincipal {

    private static final Scanner sc = new Scanner(System.in);
    private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Popular dados de teste
        popularDadosIniciais();

        exibirBoasVindas();

        while (true) {
            int opcaoInicial = exibirMenuInicial();
            switch (opcaoInicial) {
                case 1 -> {
                    Usuario usuarioLogado = fazerLogin();

                    if (usuarioLogado != null) {
                        System.out.println("\nLogin bem-sucedido! Olá " + usuarioLogado.getNome() + "!");

                        UsuarioController uController = new UsuarioController(usuarioLogado);
                        uController.verificarStreaks();
                        uController.verificarEdesbloquearConquistas();

                        aguardarEnter();
                        loopPrincipalApp(usuarioLogado);

                    } else {
                        System.out.println("\nUsuário ou senha incorretos. Por favor, tente novamente.");
                        aguardarEnter();
                    }
                }
                case 2 -> cadastrarUsuario();
                case 0 -> {
                    System.out.println("\nFim da jornada por hoje! Volte ao Cicloo para continuar evoluindo!");
                    return;
                }
                default -> System.out.println("Opção inválida. Por favor, escolha uma das opções do menu.");
            }
        }
    }

    /**
     * Menu principal exibido após o login do usuário.
     *
     * @param usuarioLogado o usuário autenticado.
     */
    private static void loopPrincipalApp(Usuario usuarioLogado) {
        int opcao = -1;
        do {
            System.out.println("\n --- MENU PRINCIPAL --- ");
            System.out.println("Logado como: " + usuarioLogado.getNome());
            System.out.println("---------------------------------");
            System.out.println("1. Meu Perfil e Estatísticas");
            System.out.println("2. Meus Hábitos");
            System.out.println("0. Sair da Conta");
            System.out.print("Escolha sua próxima ação: ");

            try {
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
                    case 0 -> System.out.println("\nSaindo da sua conta...");
                    default -> System.out.println("\nOpção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número.");
                sc.nextLine();
                opcao = -1;
            }

        } while (opcao != 0);
    }

    /**
     * Exibe o menu inicial com opções de login, cadastro ou saída.
     *
     * @return a opção escolhida pelo usuário.
     */
    private static int exibirMenuInicial() {
        System.out.println("\n--- BEM-VINDO AO CICLOO ---");
        System.out.println("1. Entrar (Login)");
        System.out.println("2. Criar Conta (Cadastro)");
        System.out.println("0. Sair do Programa");
        System.out.print("Escolha sua jornada: ");
        try {
            int opcao = sc.nextInt();
            sc.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    /**
     * Realiza o processo de login solicitando nome de usuário e senha.
     *
     * @return o objeto {@link Usuario} se o login for bem-sucedido; {@code null} caso contrário.
     */
    private static Usuario fazerLogin() {
        System.out.println("\n--- TELA DE LOGIN ---");
        System.out.print("Digite seu nome de usuário: ");
        String nome = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        Usuario usuario = usuarioDAO.buscarPorNome(nome);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }

        return null;
    }

    /**
     * Realiza o cadastro de um novo usuário no sistema.
     */
    private static void cadastrarUsuario() {
        System.out.println("\n --- CRIANDO UMA NOVA CONTA --- ");
        try {
            System.out.print("Digite seu nome de usuário: ");
            String nome = sc.nextLine();

            System.out.print("Digite seu e-mail: ");
            String email = sc.nextLine();

            System.out.print("Crie uma senha (mínimo 6 caracteres, com letras e números): ");
            String senha = sc.nextLine();

            if (usuarioDAO.buscarPorEmail(email) != null) {
                throw new IllegalArgumentException("Já existe um usuário com esse e-mail.");
            }

            Usuario novoUsuario = new Usuario(nome, email, senha);
            novoUsuario.carregarConquistasIniciais(popularConquistas());
            usuarioDAO.salvar(novoUsuario);

            System.out.println("\nUsuário '" + nome + "' criado com sucesso!");
            System.out.println("Agora você pode fazer o login e iniciar sua jornada de hábitos!");
            aguardarEnter();

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro no cadastro: " + e.getMessage());
            System.out.println("Por favor, tente novamente.");
            aguardarEnter();
        }
    }

    /**
     * Exibe a mensagem de boas-vindas inicial do sistema.
     */
    private static void exibirBoasVindas() {
        System.out.println("=================================================");
        System.out.println(" C I C L O O - Transforme sua Rotina");
        System.out.println("=================================================");
    }

    /**
     * Aguarda o usuário pressionar Enter antes de continuar.
     */
    private static void aguardarEnter() {
        System.out.println("\n(Pressione Enter para continuar...)");
        sc.nextLine();
    }

    /**
     * Popula o sistema com dados iniciais para testes.
     * Cria um usuário padrão com alguns hábitos e conquistas.
     */
    private static void popularDadosIniciais() {
        if (usuarioDAO.buscarPorNome("eric") == null) {
            Usuario u1 = new Usuario("eric", "eric@email.com", "123456a");
            UsuarioController controller = new UsuarioController(u1);
            u1.carregarConquistasIniciais(popularConquistas());
            controller.adicionarHabito(new Habito("Beber 2L de água", Categoria.SAUDE, Recorrencia.DIARIA, Prioridade.ALTA));
            controller.adicionarHabito(new Habito("Ler 1 capítulo de um livro", Categoria.ESTUDO, Recorrencia.DIARIA, Prioridade.MEDIA));
            controller.adicionarHabito(new Habito("Fazer exercício físico", Categoria.SAUDE, Recorrencia.SEMANAL, Prioridade.ALTA));
            controller.adicionarHabito(new Habito("Organizar finanças do mês", Categoria.FINANCAS, Recorrencia.MENSAL, Prioridade.BAIXA));
            usuarioDAO.salvar(u1);
        }
    }

    /**
     * Cria e retorna a lista de conquistas padrões que serão atribuídas ao usuário.
     *
     * @return uma lista com conquistas iniciais.
     */
    private static List<Conquista> popularConquistas() {
        List<Conquista> catalogoDeConquistas = new ArrayList<>();
        catalogoDeConquistas.add(new Conquista("Primeiro Passo", "Criar seu primeiro hábito."));
        catalogoDeConquistas.add(new Conquista("Persistência de Aço", "Concluir um hábito 5 vezes."));
        catalogoDeConquistas.add(new Conquista("Mestre da Rotina", "Manter um streak de 7 dias em qualquer hábito."));
        catalogoDeConquistas.add(new Conquista("Novato Promissor", "Alcançar o Nível 5 em qualquer hábito."));
        catalogoDeConquistas.add(new Conquista("Colecionador", "Ter 5 hábitos ativos ao mesmo tempo."));
        return catalogoDeConquistas;
    }
}
