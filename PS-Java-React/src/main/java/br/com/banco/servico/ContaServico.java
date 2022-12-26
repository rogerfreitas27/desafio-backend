package br.com.banco.servico;

import br.com.banco.entidade.Conta;
import br.com.banco.exception.ObjetoNotFoundException;
import br.com.banco.repositorio.ContaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ContaServico {


    @Autowired
    private ContaRepositorio contaRepositorio;


    public Page<Conta> findAll(Pageable peageble)
    {
        Page<Conta> contas = contaRepositorio.findAll(peageble);

        if( contas.getTotalPages() < 1)
            throw  new ObjetoNotFoundException("Não há registros");

        return contas;
    }

    public Conta findById(Long id) {
        return contaRepositorio.findById(id).orElseThrow(() ->
                new ObjetoNotFoundException("Conta não encontrada"));
    }


}
