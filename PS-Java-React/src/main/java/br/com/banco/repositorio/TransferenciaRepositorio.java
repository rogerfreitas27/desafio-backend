package br.com.banco.repositorio;

import br.com.banco.entidade.Conta;
import br.com.banco.entidade.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface TransferenciaRepositorio extends JpaRepository<Transferencia,Long> {



    @Query(value="SELECT * from transferencia  t  WHERE t.conta_id=:id", nativeQuery = true)
    Page<Transferencia> buscarTodosPorConta(Long id, Pageable pageable);


    @Query(value = "SELECT * from  transferencia where conta_id=:id and  data_transferencia >=:inicio and   data_transferencia<=:fim ", nativeQuery = true)
    public Page<Transferencia> buscarTransferenciaPorPeriodo(@Param("id")Long id, @Param("inicio")LocalDate inicio,
                                                             @Param("fim") LocalDateTime fim, Pageable pageable);

    @Query(value = "SELECT * from  transferencia where  UPPER(nome_operador_transacao) LIKE  %:operador% ", nativeQuery = true)
    public Page<Transferencia> buscarPorOperador( @Param("operador")String  operador, Pageable pageable);


    @Query(value = "SELECT * from  transferencia where   UPPER(nome_operador_transacao) LIKE  %:operador% " +
            "and data_transferencia >=:inicio and data_transferencia<=:fim  ", nativeQuery = true)
    public Page<Transferencia> buscarPorFiltro( @Param("operador")String operador,
                                                @Param("inicio")LocalDate inicio,
                                                @Param("fim")LocalDateTime fim,
                                                Pageable pageable);
}
