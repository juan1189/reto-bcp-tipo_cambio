package com.bcp.reto.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcp.reto.entity.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Long>{
	
	 @Query(value = "SELECT m FROM Moneda m " +
	            " where (m.monedaNacional = true or m.codigo = :codigoMonedaOrigen or m.codigo = :codigoMonedaDestino) " +
	            " and m.estado = true " +
	            " order by m.monedaNacional desc")	
	List<Moneda> buscarMonedasPorTiposDeCambio(String codigoMonedaOrigen,String codigoMonedaDestino);
	 
	Optional<Moneda> findByIdAndEstadoIsTrue(Long id);
	 
	Optional<Moneda> findByCodigoAndEstadoIsTrue(String code);
	
	Optional<Moneda> findByMonedaNacionalIsTrueAndEstadoIsTrue();

	@Modifying
	@Transactional
	@Query("update Moneda m set m.estado=false where m.id=:id")
	void delete(@Param("id") Long id);
	

}
