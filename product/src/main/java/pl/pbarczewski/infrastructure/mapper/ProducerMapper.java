package pl.pbarczewski.infrastructure.mapper;

import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.infrastructure.model.Product;

public class ProducerMapper {


    public static Product convertToProduct(ProductModel productModel, String creditNumber) {
        return Product
                .builder()
                .creditNumber(creditNumber)
                .name(productModel.getName())
                .value(productModel.getValue())
                .build();
    }

    /*public static ProductModel convertToProductModel() {
        return ProductModel
                .builder()
                .creditNumber(creditNumber)
                .name(productModel.getName())
                .value(productModel.getValue())
                .build();
    }*/
}
