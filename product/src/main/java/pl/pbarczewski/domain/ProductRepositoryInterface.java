package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.ProductModel;

import java.util.List;

public interface ProductRepositoryInterface {
    List<ProductModel> getProductModel();
    String createProduct();
}
