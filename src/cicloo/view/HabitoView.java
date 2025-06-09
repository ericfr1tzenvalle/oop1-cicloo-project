package cicloo.view;

import cicloo.controller.HabitoController;
import cicloo.model.Usuario;
import cicloo.dao.HabitoDAO;
import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;
import java.util.List;

import java.util.Scanner;

public class HabitoView {

    private final Scanner sc = new Scanner(System.in);
    private final Usuario usuario;
    private final HabitoDAO habitoDAO = HabitoDAO.getInstancia();
    private final HabitoController hController = new HabitoController();

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

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Entrada inválida.");
                sc.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1 ->
                    criarHabito();
                case 2 ->
                    listarHabitos();
                case 3 ->
                    atualizarHabito();
                case 4 ->
                    concluirHabito();
                case 5 ->
                    deletarHabito();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void criarHabito() {
        try {
            System.out.println("\nCriando um novo hábito para o usuário: " + usuario.getNome());

            System.out.print("Nome do Hábito: ");
            String nome = sc.nextLine();

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
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }

            System.out.println();
            Habito novoHabito = new Habito(nome, categoria, recorrencia, prioridade);
            usuario.adicionarHabito(novoHabito);

            System.out.println("Hábito criado com sucesso!");
            System.out.println("Você está cada vez mais perto de se tornar sua melhor versão!");

        } catch (InterruptedException e) {
            System.out.println("Algo deu errado ao criar o hábito.");
            Thread.currentThread().interrupt();
        }
    }

    private void listarHabitos() {
        System.out.println("Listando hábitos do usuário: " + usuario.getNome());
        List<Habito> habitos = usuario.listarHabitos();
        for (Habito h : habitos) {
            System.out.println(h.toString());
        }

    }

    private void atualizarHabito() {
        try {
            List<Habito> habitos = usuario.listarHabitos();

            if (habitos.isEmpty()) {
                System.out.println("Nenhum hábito encontrado para atualizar.");
                return;
            }

            System.out.println("\nLista de hábitos:");
            for (Habito h : habitos) {
                System.out.println("ID: " + h.getId() + " - " + h.getNome());
            }

            System.out.print("\nDigite o ID do hábito que deseja atualizar: ");
            int idSelecionado = Integer.parseInt(sc.nextLine());

            Habito habitoSelecionado = null;
            for (Habito h : habitos) {
                if (h.getId() == idSelecionado) {
                    habitoSelecionado = h;
                    break;
                }
            }

            if (habitoSelecionado == null) {
                System.out.println("Hábito com ID informado não encontrado.");
                return;
            }

            System.out.println("\nAtualizando o hábito: " + habitoSelecionado.getNome());

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
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }

            System.out.println();
            hController.setHabito(habitoSelecionado);
            hController.atualizarHabito(habitoSelecionado.getNome(), novaCategoria, novaPrioridade, novaRecorrencia);
            System.out.println("Hábito atualizado com sucesso!");

        } catch (InterruptedException e) {
            System.out.println("Algo deu errado ao atualizar o hábito.");
            Thread.currentThread().interrupt();
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. O ID deve ser um número inteiro.");
        }
    }

    private void concluirHabito() {
        try {
            List<Habito> habitos = usuario.listarHabitos();
            if (habitos.isEmpty()) {
                System.out.println("Não há habitos para concluir.");
                return;
            }
            System.out.println("\nLista de hábitos:");
            for (Habito h : habitos) {
                System.out.println("ID: " + h.getId() + " - " + h.getNome() + " - " + h.isConcluido());
            }
            System.out.print("\nDigite o ID do hábito que deseja atualizar: ");
            int idSelecionado = Integer.parseInt(sc.nextLine());
            Habito habitoSelecionado = null;
            for (Habito h : habitos) {
                if (h.getId() == idSelecionado) {
                    habitoSelecionado = h;
                    break;
                }
            }
            if(habitoSelecionado == null){
                System.out.println("Hábito com ID informado não encontrado.");
                return;
            }
            if(habitoSelecionado.isConcluido()){
                System.out.println("Hábito ja foi concluido.");
                return;
            }
            System.out.print("\nConcluindo hábito");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            hController.setHabito(habitoSelecionado);
            hController.concluirHabito();
            System.out.println("Hábito concluido!");
            
            

            
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. O ID deve ser um número inteiro.");
        } catch (InterruptedException e) {
            System.out.println("Algo deu errado ao atualizar o hábito.");
            Thread.currentThread().interrupt();
        }

    }

    private void deletarHabito() {
        try{
            List<Habito> habitos = usuario.listarHabitos();
            
            if(habitos.isEmpty()){
                System.out.println("Não há habitos para deletar.");
                return;
            }
            
            System.out.println("\nLista de hábitos:");
            for (Habito h : habitos) {
                System.out.println("ID: " + h.getId() + " - " + h.getNome() + " - " + h.isConcluido());
            }
            System.out.print("\nDigite o ID do hábito que deseja deletar: ");
            int idSelecionado = Integer.parseInt(sc.nextLine());
            Habito habitoSelecionado = null;
            for (Habito h : habitos) {
                if (h.getId() == idSelecionado) {
                    habitoSelecionado = h;
                    break;
                }
            }
            if(habitoSelecionado == null){
                System.out.println("Hábito com ID informado não encontrado.");
                return;
            }
            System.out.print("\nDeletando hábito");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            usuario.removerHabitoPorId(idSelecionado);
            
            
            
        }catch(NumberFormatException e){
            System.out.println("Entrada inválida. O ID deve ser um número inteiro.");
        } catch (InterruptedException e) {
            System.out.println("Algo deu errado ao atualizar o hábito.");
            Thread.currentThread().interrupt();
        }

    }

    private int lerOpcaoValida(String nomeCampo, int limiteMaximo) {
        int opcao = -1;
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
}
