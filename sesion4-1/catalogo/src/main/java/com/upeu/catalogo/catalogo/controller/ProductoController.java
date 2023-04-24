package com.upeu.catalogo.catalogo.controller;

import com.upeu.catalogo.catalogo.entity.Producto;
import com.upeu.catalogo.catalogo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> list() {
        return ResponseEntity.ok().body(productoService.list());
    }

    @PostMapping()
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @PutMapping()
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.update(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok().body(productoService.listById(id).get());
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id) {
        productoService.deleteById(id);
        return "Deleted Successfully";
    }
}
