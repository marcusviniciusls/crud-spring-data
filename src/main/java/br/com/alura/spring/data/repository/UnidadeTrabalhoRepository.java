package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.entities.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> {
}
