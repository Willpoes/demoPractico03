package com.example.demoPractico03.controller;

import com.example.demoPractico03.dto.ProductDTO;
import com.example.demoPractico03.exception.BadRequestException;
import com.example.demoPractico03.repository.Product;
import com.example.demoPractico03.repository.ProductRepository;
import com.example.demoPractico03.service.ProductService;
//import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;
    private final ProductService productService;



    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    // GET: listar todos los productos
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET: buscar producto por id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // POST: crear nuevo producto
    // dto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    //DTO
    @PostMapping("/nap")
    public ProductDTO createProductVersion2(@RequestBody ProductDTO productdto) {
        return this.productService.guardarProductoVersion(productdto);
    }

    @GetMapping("/cookie")
    public String getOneCookie(@CookieValue(value="User-Token", defaultValue="desconocido") String token){
        //log.info("aqui estamos en una peticion");
        log.info("el vlaor del cookie es () ", token);
        return ("el vlaor del cookie es () " + token);
    }

    //encabezado
    @GetMapping("/multiHeader")
    public String getMultiHeader(
            @RequestHeader(value="User-Agent", defaultValue = "es-Pe") String userAgent,
            @RequestHeader(value="Accept-Language", defaultValue = "fr-default") String languageAgent
    ){
        //log.info("aqui estamos en una peticion");
        log.info("el vlaor de useragent {} y el valor del lengauje es  {} ", userAgent,languageAgent);
        return ("el vlaor del cookie es " + userAgent + " lenguaje >" + languageAgent);
    }

    //PJQL
    @GetMapping("/filter")
    public List<Product> getFilterByName(@RequestParam("name") String name){

        return this.productService.getProductFilterByName(name);
    }

    //cuerpoos a nivel respuesta
    @GetMapping("/testv1/{id}")
    public ResponseEntity<String> getTestv1(@PathVariable Long id) throws BadRequestException {
        if(id==null || id==0){
            throw new BadRequestException("el id no puede ser vacio");
        }
        String result = this.productService.getOnePatient(id);

        return ResponseEntity.ok().body(result);
    }

    //*post y test
    @PostMapping("/testv1/create/new")
    public ResponseEntity<ProductDTO> createProductVersionTest(@RequestBody ProductDTO productdto) {

        if (productdto.getNombre() == null || productdto.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre no puede estar vacío");
        }
        if (productdto.getPrecio() <= 0) {
            throw new BadRequestException("El precio debe ser mayor que 0");
        }

        ProductDTO savedProduct = this.productService.guardarProductoVersion(productdto);
        return ResponseEntity.ok(savedProduct);
    }
}
