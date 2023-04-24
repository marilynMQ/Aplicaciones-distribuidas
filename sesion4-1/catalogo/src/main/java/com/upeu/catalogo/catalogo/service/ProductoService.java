package com.upeu.catalogo.catalogo.service;

import com.upeu.catalogo.catalogo.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> list();

    public Producto save(Producto producto);

    public Producto update(Producto producto);

    public Optional<Producto> listById(Integer id);

    public void deleteById(Integer id);
}
