package br.com.banco.entidade;


import br.com.banco.dto.TransferenciaDto;
import br.com.banco.util.Transacao;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transferencia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private LocalDateTime data_transferencia;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Transacao tipo;

    private String nome_operador_transacao;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta_id;

    public static TransferenciaDto conversorDeTransferencia(Transferencia transferencia){
       TransferenciaDto transferenciaDto = new TransferenciaDto(
               transferencia.getId(),
               transferencia.getData_transferencia().toLocalDate(),
               transferencia.getValor().setScale(2, RoundingMode.HALF_UP),
               transferencia.getTipo(),
               transferencia.getNome_operador_transacao(),
               transferencia.getConta_id().getId_conta()
               );

       return transferenciaDto;
    }

}


