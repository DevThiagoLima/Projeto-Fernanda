package controller;

import model.Cliente;
import service.ClienteService;
import service.AgendamentoService;

import java.time.LocalDate;
import java.util.Scanner;

public class ClienteController {
    private ClienteService clienteService = new ClienteService();
    private AgendamentoService agendamentoService = new AgendamentoService();
    private Scanner scanner = new Scanner(System.in);

    public void executar() {
        System.out.println("Bem-vindo ao Sistema de Agendamentos");

        System.out.println("Cadastro de Cliente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt(); scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Idade Gestacional (em semanas): ");
        int idadeGestacional = scanner.nextInt(); scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(nome, idade, cpf, telefone, idadeGestacional, senha);
        clienteService.cadastrarCliente(cliente);

        System.out.println("\nLogin:");
        System.out.print("Nome: ");
        String loginNome = scanner.nextLine();
        System.out.print("Senha: ");
        String loginSenha = scanner.nextLine();

        if (clienteService.login(loginNome, loginSenha)) {
            int opcao = 0;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Realizar Agendamento");
                System.out.println("2. Visualizar Data Agendada");
                System.out.println("3. Desmarcar Agendamento");
                System.out.println("4. Ver Meus Dados");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt(); scanner.nextLine();

                Cliente logado = clienteService.getClienteLogado();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a data (AAAA-MM-DD): ");
                        String dataStr = scanner.nextLine();
                        agendamentoService.realizarAgendamento(logado, LocalDate.parse(dataStr));
                        clienteService.atualizarArquivo();
                        break;
                    case 2:
                        System.out.println(logado.getAgendamento() != null
                                ? logado.getAgendamento()
                                : "Nenhum agendamento encontrado.");
                        break;
                    case 3:
                        agendamentoService.desmarcarAgendamento(logado);
                        clienteService.atualizarArquivo();
                        System.out.println("Agendamento cancelado.");
                        break;
                    case 4:
                        System.out.println("Nome: " + logado.getNome());
                        System.out.println("Idade: " + logado.getIdade());
                        System.out.println("CPF: " + logado.getCpf());
                        System.out.println("Telefone: " + logado.getTelefone());
                        System.out.println("Idade Gestacional: " + logado.getIdadeGestacional() + " semanas");
                        System.out.println("Agendamento: " + (logado.getAgendamento() != null
                                ? logado.getAgendamento()
                                : "Nenhum"));
                        break;
                    case 5:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 5);
        } else {
            System.out.println("Login inválido.");
        }
    }
}