package com.agenda.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Configuração dos atributos com a nossa tabela no banco
@AllArgsConstructor // Anotação para a criação de construtores com todos os argumentos
@NoArgsConstructor // Anotação para criação de construtor sem argumentos
@Data // Anotação para criação dos métodos Get e Sters automaticamente através da //
      // dependencia LOMBOK
@Entity(name = "tb_tipousuario") // É necessário colocar esse @Entity para especificar para o Spring Boot que
                                 // essa classe será uma tabela no banco
public class TipoUsuario {

    @Id // Essa anaotação é para dizer que meus atributos terão ID's
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Isso é para gerar ID's automaticamente
    private int idTipoUsuario;

    // Valores padrão caso eu não use o @Column (name == nome do tributo, nullable
    // == true, length == 200)
    @Column(name = "nome", unique = true, nullable = false, length = 45) // Especificamos esses argumentos para poder
                                                                         // usar os valores por nós especificados
    private String nome;

}
