package com.mertalptekin.springbootrestapp.presentation.controller;


import com.mertalptekin.springbootrestapp.application.product.IProductRequestHandler;
import com.mertalptekin.springbootrestapp.application.product.createProduct.CreateProductRequest;
import com.mertalptekin.springbootrestapp.application.product.createProduct.CreateProductResponse;
import com.mertalptekin.springbootrestapp.application.product.deleteProduct.DeleteProductRequest;
import com.mertalptekin.springbootrestapp.application.product.deleteProduct.DeleteProductResponse;
import com.mertalptekin.springbootrestapp.application.product.discountPrice.DiscountPriceRequest;
import com.mertalptekin.springbootrestapp.application.product.discountPrice.DiscountPriceResponse;
import com.mertalptekin.springbootrestapp.application.product.getProductById.GetProductByIdRequest;
import com.mertalptekin.springbootrestapp.application.product.getProductById.GetProductByIdResponse;
import com.mertalptekin.springbootrestapp.application.product.updateProduct.UpdateProductRequest;
import com.mertalptekin.springbootrestapp.application.product.updateProduct.UpdateProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/products") // Genel product endpoint'i
public class ProductController {



    // DIP'e uygun olarak handler interface'i üzerinden bağımlılık enjekte ediliyor.
    // Presentation katmanı, Application katmanına bağımlıdır. Application katmanındaki handler interface'ini kullanılara Application katmanındanı çağrı zayıf bağımlılık oluşturulmuş olur.
    private final IProductRequestHandler<CreateProductRequest, CreateProductResponse> productCreateRequestHandler;
    private final IProductRequestHandler<GetProductByIdRequest, GetProductByIdResponse> getProductByIdRequestHandler;
    private final IProductRequestHandler<DeleteProductRequest, DeleteProductResponse> deleteProductRequestHandler;
    private final IProductRequestHandler<UpdateProductRequest, UpdateProductResponse> updateProductRequestHandler;
    private final IProductRequestHandler<DiscountPriceRequest, DiscountPriceResponse> discountPriceRequestHandler;

    public ProductController(IProductRequestHandler<CreateProductRequest,CreateProductResponse> productCreateRequestHandler, IProductRequestHandler<GetProductByIdRequest, GetProductByIdResponse> getProductByIdRequestHandler, IProductRequestHandler<DeleteProductRequest, DeleteProductResponse> deleteProductRequestHandler, IProductRequestHandler<UpdateProductRequest, UpdateProductResponse> updateProductRequestHandler, IProductRequestHandler<DiscountPriceRequest, DiscountPriceResponse> discountPriceRequestHandler) {
        this.getProductByIdRequestHandler = getProductByIdRequestHandler;
        this.productCreateRequestHandler = productCreateRequestHandler;
        this.deleteProductRequestHandler = deleteProductRequestHandler;
        this.updateProductRequestHandler = updateProductRequestHandler;
        this.discountPriceRequestHandler = discountPriceRequestHandler;
    }

    // @PathVariable ile URL'den id parametresi alınır.ve required olarak işaretlenir.

    @GetMapping("{id}")
    public ResponseEntity<GetProductByIdResponse> getProductById(@PathVariable("id") Integer id) {
        // Ürün getirme işlemleri burada yapılacak

        var request = new GetProductByIdRequest(id);
        var response = this.getProductByIdRequestHandler.handle(request);

        return ResponseEntity.ok(response);
    }

    // Controller katmanında sadece istekleri karşılayıp ilgili handler'a yönlendirme yapılır.
    // POST /api/products
    // Görevi ProductEntity ile ilgili useCaselere isteklerin yönlendirilmesidir.
    // Post,PUT işlemlerde RequestBody'nin validayonunu otomatik yönetmektir.
    // Event ve Request işlemleri için genel olarak modern yaklaşımlarda record yapısı tercih edilmektedir.
    // POST işlemlerinde genel yapı 201 Created dönmektir.
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        // Ürün oluşturma işlemleri burada yapılacak

        var response = this.productCreateRequestHandler.handle(request);
        var uri = URI.create("/api/products/" + response.getId()); // Oluşturulan kaynağın URI'si

        return ResponseEntity.created(uri).body(response);
    }

    // 204 response update and delete operation;

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("id") Integer id) {

        var request = new DeleteProductRequest(id);
        var response = this.deleteProductRequestHandler.handle(request);
        return  ResponseEntity.ok(response);
    }

    // Genel olarak tüm anlanları güncelleme işlemleri için PUT kullanılır.
    @PutMapping("{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody UpdateProductRequest request) {

        // parameter olarak gelen id ile request içindeki id'nin aynı olup olmadığını kontrol et
        if (!id.equals(request.id())) {
            return ResponseEntity.badRequest().build(); // status code 400 Bad Request döner
        }

        var response = this.updateProductRequestHandler.handle(request);
        // ResponseEntity.noContent().build(); 204 No Content döner.

        // Ürün güncelleme işlemleri burada yapılacak
        return ResponseEntity.ok(response);
    }

    // Belirli alanlarda güncelleme işlemleri için PATCH kullanılır.
    // api/products/{id}/discount
    // İndirim uygulama endpointi
    @PatchMapping("{id}/discount")
    public ResponseEntity<DiscountPriceResponse> discount(@PathVariable("id") Integer id, @RequestBody DiscountPriceRequest request) {
        // parameter olarak gelen id ile request içindeki id'nin aynı olup olmadığını kontrol et
        if (!id.equals(request.id())) {
            return ResponseEntity.badRequest().build();
        }

        var response = this.discountPriceRequestHandler.handle(request);
        // ResponseEntity.noContent().build(); 204 No Content döner.

        // Ürün güncelleme işlemleri burada yapılacak
        return ResponseEntity.ok(response);
    }


}
