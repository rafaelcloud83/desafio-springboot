package edu.rafael.desafiospringboot.repository;

import edu.rafael.desafiospringboot.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
