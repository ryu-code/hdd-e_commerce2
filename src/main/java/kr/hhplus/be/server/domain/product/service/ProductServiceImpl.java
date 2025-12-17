package kr.hhplus.be.server.domain.product.service;

import kr.hhplus.be.global.error.BusinessException;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public void checkProduct(Long userPoint, Long productId, int totalAmount) {

        if (productId == null) throw new BusinessException.InputDataNullException();

        ProductDto product = productRepository.findProductById(productId);

        if (totalAmount > product.getStock()) throw new BusinessException.CantNotPurchaseException("재고가 부족합니다.");
        if (userPoint < product.getPrice() * totalAmount) throw new BusinessException.CantNotPurchaseException("사용자의 포인트가 부족합니다.");

        purchase(productId, product.getStock(), totalAmount);
    }

    public int productPrice(Long productId) {
        if (productId < 0L) throw new BusinessException.InvalidValueException();

        return productRepository.findProductById(productId).getPrice();
    }

    private void purchase(Long productId, int stock, int totalAmount) {
        Product product = new Product();

        product.setProductId(productId);
        product.setStock(stock - totalAmount);

        productRepository.save(product);
    }

    public ProductDto getProduct(Long productId) {
        return productRepository.findProductById(productId);
    }
}
