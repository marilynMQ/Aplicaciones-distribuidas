package com.upeu.catalogo.catalogo.repository;

import com.upeu.catalogo.catalogo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
