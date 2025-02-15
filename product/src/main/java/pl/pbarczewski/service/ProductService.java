package pl.pbarczewski.service;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.ProductServiceInterface;
import pl.pbarczewski.domain.model.ProductModel;

import java.util.List;


@Service
public class ProductService implements ProductServiceInterface {

    @Override
    public List<ProductModel> getProductModel() {
        return null;
    }

    @Override
    public String createProduct() {
        return null;
    }
}
