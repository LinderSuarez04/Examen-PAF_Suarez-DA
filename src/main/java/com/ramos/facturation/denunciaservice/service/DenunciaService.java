package com.ramos.facturation.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ramos.facturation.denunciaservice.entity.Denuncia;

public interface DenunciaService {
	public List<Denuncia> findAll(Pageable pageable);
	public Denuncia create(Denuncia denuncia);
    public Denuncia findById(int id);
    public List<Denuncia> findByDni(String dni);
    public boolean delete(int id);
}
