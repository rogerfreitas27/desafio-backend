package br.com.banco.controle;


import br.com.banco.entidade.Conta;
import br.com.banco.servico.ContaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/banco/conta")
public class ContaControle {

    @Autowired
    private ContaServico contaServico;


    @GetMapping("/{id}")
    @Operation(summary = "Endpoint para buscar conta por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrado") })
    public ResponseEntity<Conta> findById(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok( contaServico.findById(id));
    }
}
