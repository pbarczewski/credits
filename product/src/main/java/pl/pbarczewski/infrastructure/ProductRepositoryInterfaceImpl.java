package pl.pbarczewski.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pbarczewski.domain.ProductRepositoryInterface;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.infrastructure.repository.ProductJpaRepository;
import java.util.List;

public class ProductRepositoryInterfaceImpl implements ProductRepositoryInterface {

    private ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductRepositoryInterfaceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<ProductModel> getProductModel() {
        return null;
    }

    @Override
    public String createProduct() {
        return null;
    }
}
