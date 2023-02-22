package com.agenda.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Configuração geral da tabble ou da classa
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "tb_servico")
public class Servico {

    // Configuração dos argumentos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServico;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @OneToOne // Criação da chave estrangeira
    @JoinColumn(name = "idPrestador")
    private Usuario prestador;
}
