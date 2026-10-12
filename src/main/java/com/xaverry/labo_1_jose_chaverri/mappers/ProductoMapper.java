package com.xaverry.labo_1_jose_chaverri.mappers;


import com.xaverry.labo_1_jose_chaverri.dto.ProductoDTO;
import com.xaverry.labo_1_jose_chaverri.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = CategoriaMapper.class)
public interface ProductoMapper {

    @Mapping(source = "categoria.id",target = "categoriaId")
    ProductoDTO toDto(Producto producto);

    List<ProductoDTO> toDtoList(List<Producto> productos);

    @Mapping(target = "categoria",ignore = true)
    Producto toEntity(ProductoDTO productoDTO);

}
