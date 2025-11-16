package kr.hhplus.be.domain.product.service;

import kr.hhplus.be.domain.product.entity.ProductDto;
import kr.hhplus.be.domain.product.repository.ProductRepository;
import kr.hhplus.be.global.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @SneakyThrows
    public ProductDto getProduct(Long productId) {
        if (productId == null) throw ErrorException.inputDataNullException;

        return productRepository.findProductById(productId);
    }

}
