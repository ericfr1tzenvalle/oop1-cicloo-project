package cicloo.view;

import cicloo.controller.HabitoController;
import cicloo.controller.UsuarioController;
import cicloo.model.Usuario;
import cicloo.dao.HabitoDAO;
import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela interface de hábitos no console.
 * Permite ao usuário interagir com o sistema para criar, listar, atualizar,
 * concluir e deletar hábitos.
 */
public class HabitoView {

    private final Scanner sc = new Scanner(System.in);
    private final Usuario usuario;
    private final UsuarioController uController;
    private final HabitoController hController = new HabitoController();

    /**
     * Construtor da interface de hábitos.
     *
     * @param usuario O usuário autenticado que está interagindo com o sistema.
     */
    public HabitoView(Usuario usuario) {
        this.usuario = usuario;
        this.uController = new UsuarioController(usuario);
    }

    /**
     * Exibe o menu principal de ações relacionadas aos hábitos.
     */
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

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                sc.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> criarHabito();
                case 2 -> listarHabitos();
                case 3 -> atualizarHabito();
                case 4 -> concluirHabito();
                case 5 -> deletarHabito();
                case 0 -> { System.out.print("Voltando ao menu principal"); animacaoSimples();}
                
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    /**
     * Lida com a criação de um novo hábito para o usuário atual.
     */
    private void criarHabito() {
        try {
            System.out.println("\nCriando um novo hábito para o usuário: " + usuario.getNome());

            System.out.print("Nome do Hábito: ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode estar vazio.");
                return;
            }

            System.out.println("\nEscolha uma Categoria:");
            Categoria[] categorias = Categoria.values();
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }
            int opcCategoria = lerOpcaoValida("categoria", categorias.length);
            Categoria categoria = categorias[opcCategoria - 1];

            System.out.println("\nEscolha a Recorrência:");
            Recorrencia[] recorrencias = Recorrencia.values();
            for (int i = 0; i < recorrencias.length; i++) {
                System.out.println((i + 1) + ". " + recorrencias[i]);
            }
            int opcRec = lerOpcaoValida("recorrência", recorrencias.length);
            Recorrencia recorrencia = recorrencias[opcRec - 1];

            System.out.println("\nEscolha a Prioridade:");
            Prioridade[] prioridades = Prioridade.values();
            for (int i = 0; i < prioridades.length; i++) {
                System.out.println((i + 1) + ". " + prioridades[i]);
            }
            int opcPrio = lerOpcaoValida("prioridade", prioridades.length);
            Prioridade prioridade = prioridades[opcPrio - 1];

            System.out.print("\nCriando hábito");
            animacaoSimples();

            System.out.println();
            Habito novoHabito = new Habito(nome, categoria, recorrencia, prioridade);
            uController.adicionarHabito(novoHabito);

            System.out.println("Hábito criado com sucesso!");
            System.out.println("Você está cada vez mais perto de se tornar sua melhor versão!");
            uController.verificarEdesbloquearConquistas();

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao criar habito: " + e.getMessage());
            aguardarEnter();
        }
    }

    /**
     * Lista todos os hábitos do usuário atual.
     */
    private void listarHabitos() {
        System.out.println("\n--- MEUS HABITOS ---");
        List<Habito> habitos = uController.listarHabitos();

        if (habitos.isEmpty()) {
            System.out.println("Voce ainda nao tem nenhum habito. Que tal criar o primeiro?");
        } else {
            for (Habito h : habitos) {
                System.out.println("- " + h.toString());
            }
        }
        aguardarEnter();
    }

    /**
     * Permite o usuário selecionar um hábito e atualizá-lo.
     */
    private void atualizarHabito() {
        try {
            Habito habitoSelecionado = selecionarHabitoPeloId("atualizar");
            if (habitoSelecionado == null) {
                return;
            }
            System.out.println("\nAtualizando o hábito: " + habitoSelecionado.getNome());

            System.out.print("Novo nome (ou deixe em branco para manter o atual): ");
            String nome = sc.nextLine();
            if (nome.trim().isEmpty()) {
                nome = habitoSelecionado.getNome();
            }

            System.out.println("\nEscolha uma nova Categoria:");
            Categoria[] categorias = Categoria.values();
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }
            int opcCategoria = lerOpcaoValida("categoria", categorias.length);
            Categoria novaCategoria = categorias[opcCategoria - 1];

            System.out.println("\nEscolha uma nova Recorrência:");
            Recorrencia[] recorrencias = Recorrencia.values();
            for (int i = 0; i < recorrencias.length; i++) {
                System.out.println((i + 1) + ". " + recorrencias[i]);
            }
            int opcRec = lerOpcaoValida("recorrência", recorrencias.length);
            Recorrencia novaRecorrencia = recorrencias[opcRec - 1];

            System.out.println("\nEscolha uma nova Prioridade:");
            Prioridade[] prioridades = Prioridade.values();
            for (int i = 0; i < prioridades.length; i++) {
                System.out.println((i + 1) + ". " + prioridades[i]);
            }
            int opcPrio = lerOpcaoValida("prioridade", prioridades.length);
            Prioridade novaPrioridade = prioridades[opcPrio - 1];

            System.out.print("\nAtualizando hábito");
            animacaoSimples();
            System.out.println();
            hController.setHabito(habitoSelecionado);
            hController.atualizarHabito(nome, novaCategoria, novaPrioridade, novaRecorrencia);
            System.out.println("Hábito atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. O ID deve ser um número inteiro.");
        }
    }

    /**
     * Conclui um hábito selecionado pelo usuário.
     */
    private void concluirHabito() {
        System.out.println("\n--- CONCLUIR HABITO ---");
        Habito habitoSelecionado = selecionarHabitoPeloId("concluir");
        if (habitoSelecionado != null) {
            System.out.print("\nConcluindo habito");
            animacaoSimples();
            hController.setHabito(habitoSelecionado);
            hController.concluirHabito();
            uController.verificarEdesbloquearConquistas();
            aguardarEnter();
        }
    }

    /**
     * Deleta um hábito selecionado pelo usuário.
     */
    private void deletarHabito() {
        System.out.println("\n--- DELETAR HABITO ---");
        Habito habitoSelecionado = selecionarHabitoPeloId("deletar");
        if (habitoSelecionado != null) {
            System.out.print("\nDeletando habito");
            animacaoSimples();
            uController.removerHabitoPorId(habitoSelecionado.getId());
            System.out.println("\nHabito deletado com sucesso.");
            aguardarEnter();
        }
    }

    /**
     * Lê uma opção numérica válida entre 1 e o limite informado.
     *
     * @param nomeCampo     O nome do campo (para feedback ao usuário).
     * @param limiteMaximo  Valor máximo permitido.
     * @return A opção numérica válida.
     */
    private int lerOpcaoValida(String nomeCampo, int limiteMaximo) {
        int opcao;
        while (true) {
            System.out.print("Digite o número da " + nomeCampo + ": ");
            String entrada = sc.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
                if (opcao >= 1 && opcao <= limiteMaximo) {
                    return opcao;
                } else {
                    System.out.println("Numero fora do intervalo. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada Inválida. Digite um numero inteiro.");
            }
        }
    }

    /**
     * Mostra uma animação simples de ... no console.
     */
    private void animacaoSimples() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Pausa a execução até o usuário pressionar Enter.
     */
    private void aguardarEnter() {
        System.out.println("\n(Pressione Enter para continuar...)");
        sc.nextLine();
    }

    /**
     * Permite o usuário selecionar um hábito com base no ID digitado.
     *
     * @param acao A ação que será realizada com o hábito (ex: "deletar").
     * @return O hábito selecionado ou {@code null} se não for encontrado ou houver erro.
     */
    private Habito selecionarHabitoPeloId(String acao) {
        List<Habito> habitos = uController.listarHabitos();
        if (habitos.isEmpty()) {
            System.out.println("Voce nao tem habitos para " + acao + ".");
            aguardarEnter();
            return null;
        }
        System.out.println("\nLista de habitos disponiveis:");
        for (Habito h : habitos) {
            System.out.println("ID: " + h.getId() + " -> " + h.getNome());
        }
        System.out.print("\nDigite o ID do habito que deseja " + acao + ": ");
        try {
            int idSelecionado = sc.nextInt();
            sc.nextLine();

            Habito habitoEncontrado = uController.buscarHabitoPorId(idSelecionado);
            if (habitoEncontrado == null) {
                System.out.println("ID nao encontrado. Tente novamente.");
            }
            return habitoEncontrado;

        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida. O ID deve ser um numero.");
            sc.nextLine();
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
