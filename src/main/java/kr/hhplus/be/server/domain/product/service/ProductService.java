package kr.hhplus.be.server.domain.product.service;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.entity.ProductDto;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    void checkProduct(Long userPoint, Long productId, int totalAmount);

    int productPrice(Long productId);

    ProductDto getProduct(Long productId);
}
