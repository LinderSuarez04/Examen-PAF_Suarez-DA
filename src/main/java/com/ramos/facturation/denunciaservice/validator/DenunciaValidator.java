package com.ramos.facturation.denunciaservice.validator;

import com.ramos.facturation.denunciaservice.entity.Denuncia;
import com.ramos.facturation.denunciaservice.exception.ValidateServiceException;

public class DenunciaValidator {
	public static void validate(Denuncia denuncia) {
        // Validación del DNI
        if (denuncia.getDni() == null || denuncia.getDni().trim().isEmpty()) {
            throw new ValidateServiceException("El DNI es requerido");
        }
        if (denuncia.getDni().length() != 8) {
            throw new ValidateServiceException("El DNI debe tener 8 caracteres");
        }

        // Validación de la descripción
        if (denuncia.getDescripcion() == null || denuncia.getDescripcion().trim().isEmpty()) {
            throw new ValidateServiceException("La descripción es requerida");
        }
        if (denuncia.getDescripcion().length() > 500) {
            throw new ValidateServiceException("La descripción no debe tener más de 500 caracteres");
        }
    }
}
