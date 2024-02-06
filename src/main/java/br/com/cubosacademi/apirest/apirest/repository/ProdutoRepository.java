package br.com.cubosacademi.apirest.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cubosacademi.apirest.apirest.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
