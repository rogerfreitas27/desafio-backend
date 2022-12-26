package br.com.banco.repositorio;


import br.com.banco.entidade.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepositorio extends JpaRepository<Conta,Long> {
}
