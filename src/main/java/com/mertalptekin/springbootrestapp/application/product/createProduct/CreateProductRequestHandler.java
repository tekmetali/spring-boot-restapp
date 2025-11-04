package com.mertalptekin.springbootrestapp.application.product.createProduct;


import com.mertalptekin.springbootrestapp.application.product.IProductRequestHandler;
import com.mertalptekin.springbootrestapp.domain.entity.Product;
import com.mertalptekin.springbootrestapp.domain.service.product.IProductService;
import com.mertalptekin.springbootrestapp.infra.service.IEmailSender;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

// ProductService -> Create, UPDATE, DELETE Class düzeyinde bunların ayrılması OCP dir.
// Her bir Operasyonun kendi koduna sahip olması sadece kendi sorumluluğunda olması SRP dir.

@Component
public class CreateProductRequestHandler implements IProductRequestHandler<ProductCreateRequest> {

    private final IProductService productService;
    private final IEmailSender emailSender;

    public CreateProductRequestHandler(IProductService productService,IEmailSender emailSender) {
        this.productService = productService;
        this.emailSender = emailSender;
    }

    // OCP açık kapalı prensibine uygundur. TDD ile yazılabilir. Her bir use case için ayrı handler yazıldığından dolayı Unit Test yazımı kolaydır. SRP tek sorumluluk prensibine uygundur. Sadece ürün oluşturma işlemi ile ilgilenir.
    // Application Katmanın görevi use case'leri yönetmektir. ve business logic içermez.
    // Business logic domain katmanında yer alır.
    // Bu handler bir use case'i temsil eder.
    // Ürün oluşturma işlemini yönetir.
    // request verisini alır, gerekli işlemleri yapar ve sonucu döner.
    // İşlem sonrasında email gönderme işlemi de yapılır.
    // Use Case -> Ürün kaydı girdiliğinde benzer kategorideki ürünleri takip eden müşterilere email gönder.
    @Override
    public void handle(ProductCreateRequest request) {

        Product entity = new Product();
        BeanUtils.copyProperties(request, entity);
        // Bean kopyalama işlemi yapıldıktan sonra servise gönderiyoruz.
        this.productService.addProduct(entity); // Domain
        // email gönderme işlemi
        // Infra
        emailSender.sendEmail("test@test.com","Yeni Ürün Eklendi","Yeni ürün eklendi: " + entity.getName());

    }

}
