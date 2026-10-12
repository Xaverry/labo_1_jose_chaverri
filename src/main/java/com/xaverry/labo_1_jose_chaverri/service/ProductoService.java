package com.xaverry.labo_1_jose_chaverri.service;

import com.xaverry.labo_1_jose_chaverri.dto.ProductoDTO;
import com.xaverry.labo_1_jose_chaverri.mappers.ProductoMapper;
import com.xaverry.labo_1_jose_chaverri.model.Categoria;
import com.xaverry.labo_1_jose_chaverri.model.Producto;
import com.xaverry.labo_1_jose_chaverri.repository.CategoriaRepository;
import com.xaverry.labo_1_jose_chaverri.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;


    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMapper = productoMapper;
    }

    public Producto create(ProductoDTO productoDTO) {

        Producto producto = productoMapper.toEntity(productoDTO);

        Categoria categoriaEncontrada = categoriaRepository.findById(productoDTO.categoriaId()).orElseThrow();

        producto.setCategoria(categoriaEncontrada);

        return productoRepository.save(producto);
    }

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    @Transactional
    public boolean delete(Long id){
        if (!productoRepository.existsById(id)){
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

    public Optional<Producto> findById(Long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> update(Long id,ProductoDTO productoDTO){

        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoDTO.nombre());
            producto.setDescripcion(productoDTO.descripcion());
            producto.setPrecio(productoDTO.precio());
            producto.setCantidadStock(productoDTO.cantidadStock());
            Categoria categoria = categoriaRepository.findById(productoDTO.categoriaId()).orElseThrow();
            producto.setCategoria(categoria);

            return productoRepository.save(producto);
        });
    }

}

/*
@Transactional



    @Transactional
    public Optional<Producto> update(Long id, ProductoDTO datos){
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(datos.getNombre());
            producto.setDescripcion(datos.getDescripcion());
            producto.setPrecio(datos.getPrecio());
            producto.setCantidadStock(datos.getCantidadStock());
            producto.setCategoria(datos.getCategoria());

            return productoRepository.save(producto);
        });
    }

 */