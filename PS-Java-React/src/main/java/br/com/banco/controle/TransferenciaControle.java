package br.com.banco.controle;


import br.com.banco.dto.TransferenciaDto;
import br.com.banco.servico.TransferenciaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/banco/transferencia")
public class TransferenciaControle {

    @Autowired
    private TransferenciaServico transferenciaServico;


    @GetMapping("buscarTransferenciaPorConta/{id}")
    @Operation(summary = "Endpoint para buscar todos as transferências de uma determinada conta" +
            ", a api devolve a lista de transferências paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com todas as transferencias"),
            @ApiResponse(responseCode = "404", description = "Não há registro")
    })
    public ResponseEntity<Page<TransferenciaDto>> buscarTransferenciaPorConta(
                                                @PathVariable("id") Long id,
                                                @PageableDefault(size = 10) Pageable peageable)
    {
        return ResponseEntity.ok(transferenciaServico.buscarTodosPorConta(id, peageable));
    }


    @GetMapping("buscarTransferenciaPorPeriodo/{id}/inicio/{inicio}/fim/{fim}")
    @Operation(summary = "Endpoint para buscar todas as transferências de acordo com" +
            "                                           um intervalo de datas a api devolve a lista paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com todas as transferencias do intervalo"),
            @ApiResponse(responseCode = "404", description = "Não há registros para este periodo")
    })
    public ResponseEntity<Page<TransferenciaDto>> buscarTransferenciaPorPeriodo(
                                            @PathVariable("id") Long id,
                                            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
                                            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim,
                                            @PageableDefault(size = 10) Pageable peageable)
    {
        return ResponseEntity.ok(transferenciaServico.buscarTransferenciaPorPeriodo(id, inicio, fim,peageable));
    }


    @GetMapping("buscarPorOperador/{operador}")
    @Operation(summary = "Endpoint para buscar todas as transferências de um operador especifico" +
                                                                                "a api devolve a lista paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com todas as transferencias do operador"),
            @ApiResponse(responseCode = "404", description = "Não há registros para este operador")
    })
    public ResponseEntity<Page<TransferenciaDto>> buscarPorOperador(@PathVariable("operador") String operador,
                                                                 @PageableDefault(size = 10) Pageable peageable)
    {
        return ResponseEntity.ok(transferenciaServico.buscarPorOperador(operador,peageable));
    }

    @GetMapping("buscarPorFiltro/{operador}/inicio/{inicio}/fim/{fim}")
    @Operation(summary = "Endpoint para buscar todas as transferências de um operador especifico" +
                                                " em um determinado periodo, a api devolve a lista paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com todas as transferencias de " +
                    "acordo com os parametros passados"),
            @ApiResponse(responseCode = "404", description = "Não há registros com estes parametros")
    })
    public ResponseEntity<Page<TransferenciaDto>> buscarPorFiltro(
                                @PathVariable("operador") String operador,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("inicio") LocalDate inicio,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("fim") LocalDate fim,
                                @PageableDefault(size = 10) Pageable peageable)
    {
        return ResponseEntity.ok(transferenciaServico.buscarPorFiltro(operador, inicio, fim,peageable));
    }


}
