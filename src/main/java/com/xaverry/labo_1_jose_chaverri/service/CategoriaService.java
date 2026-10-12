package com.xaverry.labo_1_jose_chaverri.service;

import com.xaverry.labo_1_jose_chaverri.model.Categoria;
import com.xaverry.labo_1_jose_chaverri.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria create(Categoria categoria){
        // -- Acá en el ejemplo de duennos, se indica Null para no sobreescribir, revisar si es necesario en mi lab
        //categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    //Tener presente que el transactional es para que no se completa all correctamente o no se guarda nada
    @Transactional
    public boolean delete(Long id){
        if (!categoriaRepository.existsById(id)){
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    @Transactional
    public Optional<Categoria> update(Long id, Categoria datos){
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(datos.getNombre());
            categoria.setDescripcion(datos.getDescripcion());
            return categoriaRepository.save(categoria);
        });
    }


}
