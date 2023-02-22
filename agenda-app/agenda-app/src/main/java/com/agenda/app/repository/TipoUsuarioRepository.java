package com.agenda.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agenda.app.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

    // Este é um finder que eu criei, para uma rota expecífica que eu vou criar.
    TipoUsuario findByNome(String nome);

    TipoUsuario findByNomeLike(String nome); // Criação de um finder proprio

    TipoUsuario findByIdTipoUsuarioAndNome(int id, String nome); // Criação de um finder proprio

}
