package com.xaverry.labo_1_jose_chaverri.mappers;

import com.xaverry.labo_1_jose_chaverri.dto.CategoriaDTO;
import com.xaverry.labo_1_jose_chaverri.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    CategoriaDTO toDto(Categoria categoria);

    List<CategoriaDTO> toDtoList(List<Categoria> categorias);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
