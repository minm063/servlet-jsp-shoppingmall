package com.nhnacademy.shoppingmall.controller.mypage.admin.product;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/admin/product/update.do")
public class ProductUpdateController implements BaseController {

    private final ProductService service = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Product product = new Product(
                Integer.parseInt(req.getParameter("productId")),
                req.getParameter("productNumber"),
                req.getParameter("productName"),
                new BigDecimal(req.getParameter("unitCost")),
                req.getParameter("description"),
                req.getParameter("productImage"),
                req.getParameter("thumbnail")
        );

        service.updateProduct(product);
        return "redirect:/mypage/admin.do";
    }
}
