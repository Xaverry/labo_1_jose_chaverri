package com.xaverry.labo_1_jose_chaverri.controllers;


import com.xaverry.labo_1_jose_chaverri.dto.ProductoDTO;
import com.xaverry.labo_1_jose_chaverri.dto.ProductoDetalleDTO;
import com.xaverry.labo_1_jose_chaverri.mappers.ProductoMapper;
import com.xaverry.labo_1_jose_chaverri.model.Producto;
import com.xaverry.labo_1_jose_chaverri.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
@Slf4j
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public ProductoController(ProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO){

        log.info("Creando producto");

        Producto productoNuevo =productoService.create(productoDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productoNuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(productoMapper.toDto(productoNuevo));
    }

    @GetMapping("/")
    public List<ProductoDTO> findAll(){
        log.info("Listando todos los productos");
        return productoMapper.toDtoList(productoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.info("Eliminando producto con id " + id);
        return productoService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id){
        log.info("Buscando producto por id " + id);
        return ResponseEntity.of(productoService.findById(id).map(productoMapper::toDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        log.info("Actualizando producto con id " + id);
        return ResponseEntity.of(productoService.update(id, productoDTO).map(productoMapper::toDto));
    }
}
