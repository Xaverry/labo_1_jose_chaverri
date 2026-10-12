package com.xaverry.labo_1_jose_chaverri.dto;

public record ProductoDetalleDTO (
    Long id,
    String nombre,
    String descripcion,
    double precio,
    int cantidadStock,
    CategoriaDTO categoria
){
}
