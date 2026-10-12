package com.xaverry.labo_1_jose_chaverri.controllers;

import com.xaverry.labo_1_jose_chaverri.dto.CategoriaDTO;
import com.xaverry.labo_1_jose_chaverri.mappers.CategoriaMapper;
import com.xaverry.labo_1_jose_chaverri.model.Categoria;
import com.xaverry.labo_1_jose_chaverri.service.CategoriaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@Slf4j
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO){
        log.info("Creando categoria");
        Categoria categoriaNueva = categoriaService.create(categoriaMapper.toEntity(categoriaDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaNueva.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoriaMapper.toDto(categoriaNueva));
    }

    @GetMapping("/")
    public List<CategoriaDTO> findAll(){
        log.info("Listando todas las categorias");
        return categoriaMapper.toDtoList(categoriaService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("Eliminando categoria con id " + id);
        return categoriaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
        log.info("Buscando categoria por id " + id);
        return ResponseEntity.of(categoriaService.findById(id).map(categoriaMapper::toDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        log.info("Actualizando categoria con id " + id);
        return ResponseEntity.of(categoriaService.update(id, categoriaMapper.toEntity(categoriaDTO)).map(categoriaMapper::toDto));
    }

}
