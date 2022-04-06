package com.bcp.reto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcp.reto.entity.TipoCambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {

}
