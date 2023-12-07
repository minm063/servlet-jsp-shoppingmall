package com.nhnacademy.shoppingmall.controller.index;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/index/product.do")
public class ProductIndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private static final String VIEW_PATH = "/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductByPath(productId, VIEW_PATH);

        req.setAttribute("product", product);
        return "shop/main/product";
    }
}
