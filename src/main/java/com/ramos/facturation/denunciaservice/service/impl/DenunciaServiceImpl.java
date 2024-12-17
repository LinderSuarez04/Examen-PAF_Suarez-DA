package com.ramos.facturation.denunciaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramos.facturation.denunciaservice.entity.Denuncia;
import com.ramos.facturation.denunciaservice.exception.GeneralServiceException;
import com.ramos.facturation.denunciaservice.exception.NoDataFoundException;
import com.ramos.facturation.denunciaservice.exception.ValidateServiceException;
import com.ramos.facturation.denunciaservice.repository.DenunciaRepository;
import com.ramos.facturation.denunciaservice.service.DenunciaService;
import com.ramos.facturation.denunciaservice.validator.DenunciaValidator;

@Service
public class DenunciaServiceImpl implements DenunciaService{
	@Autowired
	public DenunciaRepository repository;

	@Override
    @Transactional(readOnly = true)
    public List<Denuncia> findAll(Pageable pageable) {
        try {
            return repository.findAll(pageable).toList();
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener la lista de denuncias", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Denuncia findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No se encontró la denuncia con ID: " + id));
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener la denuncia por ID", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> findByDni(String dni) {
        try {
            List<Denuncia> denuncias = repository.findByDni(dni);
            if (denuncias.isEmpty()) {
                throw new NoDataFoundException("No se encontraron denuncias para el usuario con DNI: " + dni);
            }
            return denuncias;
        } catch (NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener las denuncias por DNI", e);
        }
    }

    @Override
    @Transactional
    public Denuncia create(Denuncia denuncia) {
        try {
            DenunciaValidator.validate(denuncia);
            return repository.save(denuncia);
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al registrar la denuncia", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        try {
        	if (!repository.existsById(id)) {
                throw new NoDataFoundException("No existe la Denuncia con ID: " + id);
            }
            repository.deleteById(id);
            return true;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar la denuncia", e);
        }
    }
	

}
