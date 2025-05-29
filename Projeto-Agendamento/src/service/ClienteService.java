package service;

import model.Agendamento;
import model.Cliente;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();
    private Cliente clienteLogado;
    private final String CAMINHO_ARQUIVO = "clientes.txt";

    public ClienteService() {
        carregarClientesDoArquivo();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        salvarClienteEmArquivo(cliente);
    }

    public boolean login(String nome, String senha) {
        for (Cliente c : clientes) {
            if (c.getNome().equals(nome) && c.getSenha().equals(senha)) {
                clienteLogado = c;
                return true;
            }
        }
        return false;
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void salvarClienteEmArquivo(Cliente cliente) {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO, true)) {
            writer.write(cliente.getNome() + ";" +
                         cliente.getIdade() + ";" +
                         cliente.getCpf() + ";" +
                         cliente.getTelefone() + ";" +
                         cliente.getIdadeGestacional() + ";" +
                         cliente.getSenha() + ";" +
                         (cliente.getAgendamento() != null ? cliente.getAgendamento().getData() : "null") +
                         "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarArquivo() {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO, false)) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getNome() + ";" +
                             cliente.getIdade() + ";" +
                             cliente.getCpf() + ";" +
                             cliente.getTelefone() + ";" +
                             cliente.getIdadeGestacional() + ";" +
                             cliente.getSenha() + ";" +
                             (cliente.getAgendamento() != null ? cliente.getAgendamento().getData() : "null") +
                             "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarClientesDoArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 7) {
                    Cliente cliente = new Cliente(
                        partes[0],
                        Integer.parseInt(partes[1]),
                        partes[2],
                        partes[3],
                        Integer.parseInt(partes[4]),
                        partes[5]
                    );

                    if (!"null".equals(partes[6])) {
                        cliente.setAgendamento(new Agendamento(LocalDate.parse(partes[6])));
                    }

                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}