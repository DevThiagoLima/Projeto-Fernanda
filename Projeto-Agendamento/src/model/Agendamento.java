package model;

import java.time.LocalDate;

public class Agendamento {
    private LocalDate data;

    public Agendamento(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data Agendada: " + data;
    }
}