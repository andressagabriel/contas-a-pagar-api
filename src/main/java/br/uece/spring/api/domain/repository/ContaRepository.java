package br.uece.spring.api.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.uece.spring.api.domain.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Integer> {
    
    List<Conta> findAll();

    boolean existsByCodigo(int codigo);

    boolean existsByNome(String nome);

    Conta findByCodigo(int codigo);
}