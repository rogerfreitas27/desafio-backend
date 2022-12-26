package br.com.banco.dto;

import br.com.banco.util.Transacao;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferenciaDto {

    private Long id;

    private LocalDate data_transferencia;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Transacao tipo;

    private String nome_operador_transacao;

    private Long   conta_id;
}
