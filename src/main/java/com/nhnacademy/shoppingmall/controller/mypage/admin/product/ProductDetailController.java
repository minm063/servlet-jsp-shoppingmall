package com.nhnacademy.shoppingmall.controller.mypage.admin.product;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductCategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/admin/product/detail.do")
public class ProductDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl(), new ProductRepositoryImpl(),
                    new CategoryRepositoryImpl());
    private static final String VIEW_PATH = "/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (Objects.nonNull(req.getParameter("id"))) {

            int productId = Integer.parseInt(req.getParameter("id"));

            Product product = productService.getProductByPath(productId, VIEW_PATH);
            List<Integer> defaultCategory = productCategoryService.getCategoryByProductId(productId);
            req.setAttribute("defaultCategory", defaultCategory);
            req.setAttribute("product", product);
        } else {
            req.setAttribute("defaultCategory", List.of(0, 0, 0));
        }


        List<Category> categoryList = categoryService.getCategories();
        req.setAttribute("categoryList", categoryList);
        return "/shop/mypage/admin/product_detail";
    }
}
