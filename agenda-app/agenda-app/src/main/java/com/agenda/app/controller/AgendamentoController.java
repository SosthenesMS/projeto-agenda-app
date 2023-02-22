package com.agenda.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agenda.app.model.Agendamento;
import com.agenda.app.repository.AgendamentoRepository;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

    @GetMapping
    public List<Agendamento> obterAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Agendamento obterAgendamentosPeloId(@PathVariable("id") int id) {
        return agendamentoRepository.findById(id).get();
    }

    @PostMapping
    public Agendamento criarUmNovoAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @PutMapping
    public Agendamento atualizarOAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoDB = agendamentoRepository.findById(agendamento.getIdAgendamento()).get(); // Por que usa
                                                                                                          // esse
                                                                                                          // .get()??
        agendamentoDB.setData(agendamento.getData());
        agendamentoDB.setHora(agendamento.getHora());
        return agendamentoRepository.save(agendamentoDB);
    }

    @DeleteMapping(value = "/{id}")
    public String deletarTodosOsAgendamentosPeloId(@PathVariable("id") int id) {
        agendamentoRepository.deleteById(id);
        return "Agendamento deletado com sucesso!";
    }

    @DeleteMapping
    public String deletarTodosOsAgendamentos() {
        agendamentoRepository.deleteAll();
        return "Todos os registros deletados com sucesso!";
    }

    @Autowired
    private AgendamentoRepository agendamentoRepository;
}
