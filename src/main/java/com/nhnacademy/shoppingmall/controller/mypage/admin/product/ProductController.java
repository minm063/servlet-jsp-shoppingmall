package com.nhnacademy.shoppingmall.controller.mypage.admin.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductCategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/admin/product.do")
public class ProductController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> list = productService.getProducts();
        List<Category> categories = categoryService.getCategories();

        List<List<String>> myCategoryName = new ArrayList<>();

        for (Product product : list) {
            List<ProductCategory> categoryList =
                    productCategoryService.getProductCategoryByProductId(product.getProductId());
            List<String> temp = new ArrayList<>();
            for (ProductCategory productCategory : categoryList) {
                int categoryId = productCategory.getCategoryId();
                String categoryName = categoryService.getCategory(categoryId).getCategoryName();
                temp.add(categoryName);
            }
            myCategoryName.add(temp);
        }

        req.setAttribute("myCategory", myCategoryName);
        req.setAttribute("category", categories);
        req.setAttribute("products", list);

        return "shop/mypage/admin/product";
    }
}
