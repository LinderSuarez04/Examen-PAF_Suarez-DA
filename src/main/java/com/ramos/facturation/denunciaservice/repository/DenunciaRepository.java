package com.ramos.facturation.denunciaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.facturation.denunciaservice.entity.Denuncia;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
	List<Denuncia> findByDni(String dni);
}
