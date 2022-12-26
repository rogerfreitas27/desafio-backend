package br.com.banco.servico;


import br.com.banco.dto.TransferenciaDto;
import br.com.banco.entidade.Transferencia;
import br.com.banco.exception.ObjetoNotFoundException;
import br.com.banco.exception.RegraNegocioException;
import br.com.banco.repositorio.TransferenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransferenciaServico {

    @Autowired
    private TransferenciaRepositorio tranferenciaRepositorio;

    @Autowired
    private ContaServico contaServico;


    public Page<TransferenciaDto> buscarTodosPorConta(Long id, Pageable peageble)
    {
        return tranferenciaRepositorio.buscarTodosPorConta(id,peageble)
                                              .map(Transferencia::conversorDeTransferencia);
    }

    public Page<TransferenciaDto> buscarTransferenciaPorPeriodo(Long id,LocalDate inicio,
                                                             LocalDate fim,
                                                                Pageable peageble)
    {
        if(fim.isBefore(inicio))
            throw  new RegraNegocioException("Ordem das datas inválidas");

        LocalDateTime dataEHoraFinal = fim.atTime(23, 59, 59);
        Page<TransferenciaDto> transferencias =
                            tranferenciaRepositorio.buscarTransferenciaPorPeriodo
                                            (id,inicio, dataEHoraFinal,peageble)
                                                                .map(Transferencia::conversorDeTransferencia);

        if(transferencias.getContent().size()==0)
            throw  new ObjetoNotFoundException("Não há registros para este periodo");

        return transferencias;
    }

    public Page<TransferenciaDto> buscarPorOperador(String operador,Pageable peageble)
    {
       Page<TransferenciaDto> transferencias = tranferenciaRepositorio.
                                                    buscarPorOperador(operador.toUpperCase(),peageble)
                                                                    .map(Transferencia::conversorDeTransferencia);

         if(transferencias.getContent().size()==0)
             throw  new ObjetoNotFoundException("Não há transações com este operador");

        return transferencias;
    }

    public Page<TransferenciaDto> buscarPorFiltro(String operador,LocalDate inicio,
                                                                                LocalDate fim,Pageable peageble)
    {
        if(fim.isBefore(inicio))
            throw  new RegraNegocioException("Ordem das datas inválidas");

        LocalDateTime dataEHoraFinal = fim.atTime(23, 59, 59);
        Page<TransferenciaDto> transferencias =
                                 tranferenciaRepositorio.buscarPorFiltro
                                                  (operador.toUpperCase(),inicio,dataEHoraFinal,peageble)
                                                                    .map(Transferencia::conversorDeTransferencia);

        if(transferencias.getContent().size()==0)
            throw  new ObjetoNotFoundException("Não há registros com estes parametros");

        return transferencias;

    }

}
