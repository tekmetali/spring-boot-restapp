package com.mertalptekin.springbootrestapp.application.product;

public interface IProductRequestHandler<T extends IProductRequest> {

    void handle(T request);
}
