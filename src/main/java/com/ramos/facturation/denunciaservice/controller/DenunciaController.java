package com.ramos.facturation.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramos.facturation.denunciaservice.entity.Denuncia;
import com.ramos.facturation.denunciaservice.service.DenunciaService;

@RestController
@RequestMapping("/v1/Denuncia")
public class DenunciaController {
	@Autowired
	private DenunciaService denunciaService;
	
	@GetMapping
    public ResponseEntity<List<Denuncia>> getAll() {
        List<Denuncia> denuncias = denunciaService.findAll();
        return new ResponseEntity<>(denuncias, HttpStatus.OK);
    }

    // Obtener una denuncia por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> findById(@PathVariable("id") int id) {
        Denuncia denuncia = denunciaService.findById(id);
        if (denuncia != null) {
            return ResponseEntity.ok(denuncia);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Buscar denuncias por DNI
    @GetMapping("/buscar")
    public ResponseEntity<List<Denuncia>> findByDni(@RequestParam("dni") String dni) {
        List<Denuncia> denuncias = denunciaService.findByDni(dni);
        if (!denuncias.isEmpty()) {
            return ResponseEntity.ok(denuncias);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Crear una nueva denuncia
    @PostMapping
    public ResponseEntity<Denuncia> create(@RequestBody Denuncia denuncia) {
        Denuncia createdDenuncia = denunciaService.create(denuncia);
        return new ResponseEntity<>(createdDenuncia, HttpStatus.CREATED);
    }


    // Eliminar una denuncia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = denunciaService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
