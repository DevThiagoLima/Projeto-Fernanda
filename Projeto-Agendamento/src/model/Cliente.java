package model;

public class Cliente {
    private String nome;
    private int idade;
    private String cpf;
    private String telefone;
    private int idadeGestacional;
    private String senha;
    private Agendamento agendamento;

    public Cliente(String nome, int idade, String cpf, String telefone, int idadeGestacional, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idadeGestacional = idadeGestacional;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public int getIdadeGestacional() { return idadeGestacional; }
    public String getSenha() { return senha; }
    public Agendamento getAgendamento() { return agendamento; }
    public void setAgendamento(Agendamento agendamento) { this.agendamento = agendamento; }
}