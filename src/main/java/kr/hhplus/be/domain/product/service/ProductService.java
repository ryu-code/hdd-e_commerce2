package kr.hhplus.be.domain.product.service;

import kr.hhplus.be.domain.product.entity.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ProductDto getProduct(Long productId);
}
