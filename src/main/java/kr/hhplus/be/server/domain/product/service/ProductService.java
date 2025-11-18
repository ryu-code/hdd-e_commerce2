package kr.hhplus.be.server.domain.product.service;

import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ProductDto getProduct(Long productId);

    Product save(ProductDto productDto);
}
