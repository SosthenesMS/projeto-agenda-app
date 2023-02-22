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
import com.agenda.app.model.Usuario;
import com.agenda.app.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    // Forma menos semântica de fazer um .findAll()
    // @GetMapping
    // public List<Usuario> buscarUsuario(){
    // return usuarioRepository.findAll();
    // }

    // Forma semântica mais indicada para fazer um .findAll()
    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodosOsUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    /******************************************************************************************************/

    // Forma menos semântica de se fazer um .findById.
    // @GetMapping(value = "/{id}")
    // public Optional<Usuario> buscarUsuario(@PathVariable ("id") int id){
    // return usuarioRepository.findById(id);
    // }

    // Forma mais semântica de se fazer um .findById.
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuarioPeloId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(id));
    }

    /******************************************************************************************************/

    // Forma menos semântica de se fazer um .post.
    // @PostMapping
    // public Usuario criarNovoUsuario(@RequestBody Usuario usuario){
    // return usuarioRepository.save(usuario);
    // }

    // Forma mais indicada e semântica para fazer um post.
    @PostMapping
    public ResponseEntity<Object> criarNovoUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar o novo usuário! " + e.getMessage());
        }
    }

    /******************************************************************************************************/

    // Forma menos indicada e menos semântica de fazer um put.
    // @PutMapping
    // public Usuario atualizarUsuario(@RequestBody Usuario usuario){
    // Usuario usuarioBD = usuarioRepository.findById(usuario.getIdUsuario()).get();
    // usuarioBD.setNome(usuario.getNome()); //Atualiza o nome
    // usuarioBD.setSobrenome(usuario.getSobrenome());
    // usuarioBD.setEmail(usuario.getEmail());
    // usuarioBD.setSenha(usuario.getSenha());
    // usuarioBD.setTipoUsuario(usuario.getTipoUsuario()); //Como atualizar o tipo
    // de usuario

    // return usuarioRepository.save(usuarioBD); // No próprio retorno eu faço a
    // persistencia no banco
    // }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
    }

    /******************************************************************************************************/

    // //Forma menos semântica para fazer um delete
    // @DeleteMapping(value = "/{id}")
    // public String deletarUsuario(@PathVariable ("id") int id){
    // usuarioRepository.deleteById(id);
    // return "Nome de usuário deletado com sucesso!";
    // }

    // @DeleteMapping // OK
    // public String deletarTodosOsUsuarios(){
    // usuarioRepository.deleteAll();
    // return "Todos os usuários deletados com sucesso!";
    // }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarUsuarioPeloId(@PathVariable("id") int id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }

    @DeleteMapping
    public ResponseEntity<String> deletarTodosOsUsusarios() {
        usuarioRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Todos os usuários deletados com sucesso!");
    }

    // Finder próprio criado no UsuarioRepository
    @GetMapping(value = "/tipo/{id}")
    public ResponseEntity<List<Usuario>> obterUsuariosPeloTipo(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByTipoUsuario(id));
    }

    @GetMapping(value = "/email/{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuarioPeloEmail(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByEmail(id));
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

}
