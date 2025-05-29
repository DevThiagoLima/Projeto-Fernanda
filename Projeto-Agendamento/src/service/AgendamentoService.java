package service;

import model.Agendamento;
import model.Cliente;

import java.time.LocalDate;

public class AgendamentoService {

    public void realizarAgendamento(Cliente cliente, LocalDate data) {
        cliente.setAgendamento(new Agendamento(data));
    }

    public void desmarcarAgendamento(Cliente cliente) {
        cliente.setAgendamento(null);
    }

    public Agendamento verAgendamento(Cliente cliente) {
        return cliente.getAgendamento();
    }
}