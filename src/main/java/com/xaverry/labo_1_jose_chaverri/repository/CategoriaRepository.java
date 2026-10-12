package com.xaverry.labo_1_jose_chaverri.repository;

import com.xaverry.labo_1_jose_chaverri.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
