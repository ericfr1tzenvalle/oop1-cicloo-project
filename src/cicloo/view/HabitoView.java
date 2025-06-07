package cicloo.view;

import cicloo.model.Usuario;
import cicloo.dao.HabitoDAO;
import cicloo.model.Habito;
import cicloo.model.enums.Categoria;
import cicloo.model.enums.Prioridade;
import cicloo.model.enums.Recorrencia;

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
    try {
        System.out.println("\nCriando um novo hábito para o usuário: " + usuario.getNome());

        System.out.print("Nome do Hábito: ");
        String nome = sc.nextLine();

       
        System.out.println("\nEscolha uma Categoria:");
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categorias[i]);
        }
        int opcCategoria;
        do {
            System.out.print("Digite o número da categoria: ");
            opcCategoria = sc.nextInt();
        } while (opcCategoria < 1 || opcCategoria > categorias.length);
        Categoria categoria = categorias[opcCategoria - 1];

        
        System.out.println("\nEscolha a Recorrência:");
        Recorrencia[] recorrencias = Recorrencia.values();
        for (int i = 0; i < recorrencias.length; i++) {
            System.out.printf("%d. %s%n", i + 1, recorrencias[i]);
        }
        int opcRec;
        do {
            System.out.print("Digite o número da recorrência: ");
            opcRec = sc.nextInt();
        } while (opcRec < 1 || opcRec > recorrencias.length);
        Recorrencia recorrencia = recorrencias[opcRec - 1];

        
        System.out.println("\nEscolha a Prioridade:");
        Prioridade[] prioridades = Prioridade.values();
        for (int i = 0; i < prioridades.length; i++) {
            System.out.printf("%d. %s%n", i + 1, prioridades[i]);
        }
        int opcPrio;
        do {
            System.out.print("Digite o número da prioridade: ");
            opcPrio = sc.nextInt();
        } while (opcPrio < 1 || opcPrio > prioridades.length);
        Prioridade prioridade = prioridades[opcPrio - 1];

        sc.nextLine(); 

        
        System.out.print("\nCriando hábito");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }

        Habito novoHabito = new Habito(nome, categoria, recorrencia, prioridade);
        usuario.adicionarHabito(novoHabito);
        habitoDAO.salvar(novoHabito);
        

        

        System.out.println("\n✅ Hábito criado com sucesso!");
        System.out.println("💪 Você está cada vez mais perto de se tornar sua melhor versão!");
    } catch (InterruptedException e) {
        System.out.println("❌ Algo deu errado ao criar o hábito.");
        Thread.currentThread().interrupt();
    }
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
