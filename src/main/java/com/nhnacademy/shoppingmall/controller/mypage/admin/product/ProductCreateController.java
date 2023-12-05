package com.nhnacademy.shoppingmall.controller.mypage.admin.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/admin/product/create.do")
public class ProductCreateController implements BaseController {

    private final ProductService service = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productNumber = String.valueOf(ThreadLocalRandom.current().nextLong(10000000000L, 100000000000L));
        String productName = req.getParameter("productName");
        String description = req.getParameter("description");
        String productImage = req.getParameter("productImage");
        String thumbnail = req.getParameter("thumbnail");

        if (Objects.nonNull(productName) && Objects.nonNull(req.getParameter("unitCost"))) {
            BigDecimal unitCost = new BigDecimal(req.getParameter("unitCost"));
            Product product = new Product(productNumber, productName, unitCost, description, productImage, thumbnail);
            service.saveProduct(product);
            return "redirect:/mypage/admin.do";
        }

        return "redirect:/mypage/admin/product/detail.do";
    }
}
