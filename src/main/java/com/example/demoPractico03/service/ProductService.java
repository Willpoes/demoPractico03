package com.example.demoPractico03.service;

import com.example.demoPractico03.dto.ProductDTO;
import com.example.demoPractico03.repository.Product;
import com.example.demoPractico03.repository.ProductRepository;
import com.example.demoPractico03.util.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //oinyeccion
    @Autowired
    private ProductMapper mapper;

    public Product buscarProducto(long id) {
        return productRepository.findById(id).map(p -> {
            log.info("Producto encontrado: {}", p);
            return p;
        }).orElseGet(() -> {
            log.warn("No se encontró producto con id {}", id);
            return null;
        });
    }

    public List<Product> listarProductos() {
        List<Product> lista = productRepository.findAll();
        log.info("Listado de productos: {}", lista);
        return lista;
    }

    public Product guardarProducto(Product product) {
        log.info("Guardando producto recibido: {}", product);
        return productRepository.save(product);
    }
    public ProductDTO guardarProductoVersion(ProductDTO dto) {
        log.info("Guardando producto recibido: {}", dto);
        Product producto = this.productRepository.save(this.mapper.productDtoToProduct(dto));
        return this.mapper.productToProductDTO(producto);
    }

    //
    public List<Product> getProductFilterByName(String nombre){
        List<Product> listProduct = this.productRepository.buscarPorNombre(nombre);
        log.info("total lista ", listProduct.stream().count());
        return listProduct;
    }

    //***************
    //test throw
    public String getOnePatient(Long id){
        Optional<Product> p = this.productRepository.findById(id);
        String nombre = p.get().getNombre();
        return nombre;
    }

    /*public ProductDTO guardarProductoVersion(ProductDTO dto) {
        Product p = new Product(dto.getNombre(), dto.getPrecio());
        Product saved = productRepository.save(p);
        return new ProductDTO(saved.getNombre(), saved.getPrecio());
    }*/
}
