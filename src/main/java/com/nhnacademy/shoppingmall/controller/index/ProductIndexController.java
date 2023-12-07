package com.nhnacademy.shoppingmall.controller.index;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/index/product.do")
public class ProductIndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private static final String VIEW_PATH = "/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductByPath(productId, VIEW_PATH);
        req.setAttribute("product", product);

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            String value = "";
            for (Cookie cookie : cookies) {
                if ("latestProduct".equals(cookie.getName())) {
                    log.info("found Cookie!!!");
                    value = cookie.getValue();
                    List<String> splitValue = new ArrayList<>(List.of(value.split("a")));

                    // 중복 검사
                    for (int i = 0; i < splitValue.size(); i++) {
                        log.info("check: {}, {}", splitValue.get(i), productId);
                        if (splitValue.get(i).equals(String.valueOf(productId))) {
                            splitValue.remove(i);
                            break;
                        }
                    }
                    log.info("size : {}", splitValue.size());
                    if (splitValue.size() >= 4) {
                        log.info("over 5");
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = splitValue.size() - 4; i < splitValue.size(); i++) {
                            log.info("추가합니다 : {}", splitValue.get(i));
                            stringBuilder.append(splitValue.get(i)).append("a");
                        }
                        stringBuilder.append(productId).append("a");
                        cookie.setValue(stringBuilder.toString());
                    } else {
                        log.info("add until 5");
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String string : splitValue) {
                            stringBuilder.append(string).append("a");
                        }
                        stringBuilder.append(product.getProductId()).append("a");
                        cookie.setValue(stringBuilder.toString());
                    }
                    cookie.setMaxAge(72000);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
            if (value.isEmpty()) {
                log.info("create cookie !!!");
                Cookie cookie = new Cookie("latestProduct", product.getProductId() + "a");
                cookie.setMaxAge(72000);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }

        return "shop/main/product";
    }
}
