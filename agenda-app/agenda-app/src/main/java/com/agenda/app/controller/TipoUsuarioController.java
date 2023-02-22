package com.agenda.app.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.agenda.app.model.TipoUsuario;
import com.agenda.app.repository.TipoUsuarioRepository;

// Anottation obrigatória para deixar claro que essa classe é um controller e assim poder usar a API
@RestController
@RequestMapping(value = "/tipousuarios") // Anotação padrão para evitar repetições nos mappings
public class TipoUsuarioController {

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> obterTipoUsuarioPeloId() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<TipoUsuario>> obterTipoUsuarioPeloId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findById(id));
    }

    // finder próprio
    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<TipoUsuario> obterTipoUsuarioPeloNome(@PathVariable("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findByNome(nome));
    }

    @GetMapping(value = "/nome/letra/{letra}")
    public ResponseEntity<TipoUsuario> obterTipoUsuarioPelaLetra(@PathVariable("letra") String letra) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findByNomeLike(letra + "%"));
    }

    @GetMapping(value = "/nome/tipo/{id}/{nome}")
    public ResponseEntity<TipoUsuario> obterTipoUsuarioPeloIdENome(@PathVariable("id") int id,
            @PathVariable("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.findByIdTipoUsuarioAndNome(id, nome));
    }

    @PostMapping
    public ResponseEntity<Object> criarNovoTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioRepository.save(tipoUsuario));
        } catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de usuário já existente. " + d.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar tipo de usuário." + e.getMessage());
        }
    }

    // //Estudar esse metodo
    // @PutMapping
    // public TipoUsuario atualizarTipoDeUsuario(@RequestBody TipoUsuario
    // tipoUsuario){
    // TipoUsuario tipoUsuarioBD =
    // tipoUsuarioRepository.findById(tipoUsuario.getIdTipoUsuario()).get();
    // tipoUsuarioBD.setNome(tipoUsuario.getNome());
    // return tipoUsuarioRepository.save(tipoUsuarioBD);
    // }

    @PutMapping
    public ResponseEntity<TipoUsuario> atualizarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUsuarioRepository.save(tipoUsuario));
    }

    // Foi utilizado uma String como retorno pois o metodo delete é um VOID e por
    // isso o professor usou uma String para poder ter uma menssagem para o user
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarTipoUsuarioPeloId(@PathVariable("id") int id) {
        tipoUsuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de usuário deletado com sucesso!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletarTodosOsTiposDeUsuario() {
        tipoUsuarioRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os registros deletados com sucesso!");
    }

    // Criando uma instância da interface, precisa ser colocado com o @Autowired por
    // uma interface não consegue ser instanciado.
    @Autowired // Ponto de injeção, tem que ser criado em cada classe respectivamente, ele
               // permite acessar o métodos o repository
    private TipoUsuarioRepository tipoUsuarioRepository;

}
