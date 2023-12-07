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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = {"/index.do"})
public class IndexController implements BaseController {

    /**
     * 1. 전체 상품(카테고리 없음) -> select * from product (order by ?)
     * 2. 최근 본 상품 -> Cookie에 json?
     * 3. 카테고리별 -> select * from product join productCategory, category where categoryName=?
     */

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final String VIEW_PATH = "/resources/";
    private final int PAGE_SIZE = 20;


    public IndexController() {
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("latestProduct".equals(cookie.getName())) {
                    log.info("found cookie in index");
                    String value = cookie.getValue();
                    log.info("value is {}", value);
                    String[] productCookie = value.split("a");
                    List<Product> latestProductId = new ArrayList<>();
                    Stream.of(productCookie)
                            .forEach(x -> latestProductId.add(productService.getProduct(Integer.parseInt(x))));
                    req.setAttribute("latestProduct", latestProductId);
                }
            }
        }

        int page = (Objects.isNull(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page")));

        List<Category> categoryList = categoryService.getCategories();
        Page<Product> products = productService.getProductsOnPageByPath(Math.toIntExact(page), PAGE_SIZE, VIEW_PATH);

        int total = productService.getTotalCount();
        total = (total == 0) ? 1 : total;
        total = (total % PAGE_SIZE == 0) ? total / PAGE_SIZE : (total / PAGE_SIZE) + 1;

        int currentBlock = page / PAGE_SIZE + 1;
        int startPage = (currentBlock - 1) * PAGE_SIZE + 1;
        int endPage = total < PAGE_SIZE ? total : startPage + PAGE_SIZE - 1;

        req.setAttribute("products", products);
        req.setAttribute("category", categoryList);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);

        return "shop/main/index";
    }
}