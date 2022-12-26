package br.com.banco.entidade;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Conta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id_conta;

    @NotBlank
    @Size(min=3,max=255, message="Campo com no minimo 3 e no maximo 255 caracteres")
    private String nome_responsavel;
}
