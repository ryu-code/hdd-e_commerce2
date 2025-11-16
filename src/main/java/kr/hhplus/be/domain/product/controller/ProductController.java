package kr.hhplus.be.domain.product.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.domain.product.entity.ProductDto;
import kr.hhplus.be.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId, HttpServletRequest httpServletRequest) {

        return productService.getProduct(productId);
    }
}
