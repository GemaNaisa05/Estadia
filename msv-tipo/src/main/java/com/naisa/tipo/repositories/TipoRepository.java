package com.naisa.tipo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naisa.tipo.models.Tipo;


@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

}
