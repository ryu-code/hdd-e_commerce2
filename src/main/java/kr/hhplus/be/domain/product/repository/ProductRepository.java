package kr.hhplus.be.domain.product.repository;

import kr.hhplus.be.domain.product.entity.Product;
import kr.hhplus.be.domain.product.entity.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    ProductDto findProductById(Long productId);

    Product save(ProductDto productDto);
}
