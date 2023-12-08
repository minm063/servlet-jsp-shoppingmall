package com.nhnacademy.shoppingmall.controller.admin.product;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/delete.do")
public class ProductDeletePostController implements BaseController {

    private final ProductService service = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("id");
        if (Objects.isNull(productId)) {
            return "/redirect:/index.do";
        }

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("latestProduct".equals(cookie.getName())) {
                    List<String> splitValue = new ArrayList<>(List.of(cookie.getValue().split("a")));

                    for (int i = 0; i < splitValue.size(); i++) {
                        if (splitValue.get(i).equals(productId)) {
                            splitValue.remove(i);
                            break;
                        }
                    }

                    String cookieValue = String.join("a", splitValue);
                    cookie.setValue(cookieValue);
                    cookie.setMaxAge(72000);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }

        service.deleteProduct(Integer.parseInt(req.getParameter("id")));
        return "redirect:/admin/product.do";
    }
}
