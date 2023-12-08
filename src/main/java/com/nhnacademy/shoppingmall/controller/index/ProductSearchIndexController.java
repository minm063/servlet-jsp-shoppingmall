package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping(method = RequestMapping.Method.POST, value = "/index/search.do")
public class ProductSearchIndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final int PAGE_SIZE = 10;
    private final String VIEW_PATH = "/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productSearch = req.getParameter("search");
        int page = (Objects.isNull(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page")));

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("latestProduct".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    String[] productCookie = value.split("a");
                    List<Product> latestProductId = new ArrayList<>();
                    Stream.of(productCookie)
                            .forEach(x -> latestProductId.add(productService.getProduct(Integer.parseInt(x))));
                    req.setAttribute("latestProduct", latestProductId);
                }
            }
        }

        List<Category> categoryList = categoryService.getCategories();
        Page<Product> products =
                productService.getProductsOnPageBySearch(page, PAGE_SIZE, VIEW_PATH, productSearch);
        int total = productService.getTotalCountBySearch(productSearch);
        total = (total == 0) ? 1 : total;
        total = (total % PAGE_SIZE == 0) ? total / PAGE_SIZE : (total / PAGE_SIZE) + 1;

        int currentBlock = page / PAGE_SIZE + 1;
        int startPage = (currentBlock - 1) * PAGE_SIZE + 1;
        int endPage = total < PAGE_SIZE ? total : startPage + PAGE_SIZE - 1;

        req.setAttribute("products", products);
        req.setAttribute("category", categoryList);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("page", page);

        return "shop/main/index";
    }
}
