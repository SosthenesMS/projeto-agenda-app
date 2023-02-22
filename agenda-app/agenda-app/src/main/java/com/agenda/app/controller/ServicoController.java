package com.agenda.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agenda.app.model.Servico;
import com.agenda.app.repository.ServicoRepository;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

    @GetMapping
    public ResponseEntity<List<Servico>> obterTodosOsServicos() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Servico>> obterServicoPeloId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> criarNovoServico(@RequestBody Servico servico) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.save(servico));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar o serviço" + e.getMessage());
        }
    }

    // @PutMapping
    // public Servico atualizarServico(@RequestBody Servico servico){
    // Servico servicoDB = servicoRepository.findById(servico.getIdServico()).get();
    // servicoDB.setNome(servico.getNome());
    // servicoDB.setDescricao(servico.getDescricao());
    // // servicoDB.setUsuario(servico.getUsuario()); //Comando para atualizar o
    // idUsuario
    // return servicoRepository.save(servicoDB);
    // }

    @PutMapping
    public ResponseEntity<Servico> atualizarServico(@RequestBody Servico servico) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.save(servico));
    }

    @DeleteMapping(value = "/{id}")
    public String deletarServico(@PathVariable("id") int id) {
        servicoRepository.deleteById(id);
        return "Servico deletado com sucesso!";
    }

    @DeleteMapping
    public String deletarTodosOsServicos() {
        servicoRepository.deleteAll();
        return "Todos os servicos deletados com sucesso!";
    }

    @Autowired
    private ServicoRepository servicoRepository;

}