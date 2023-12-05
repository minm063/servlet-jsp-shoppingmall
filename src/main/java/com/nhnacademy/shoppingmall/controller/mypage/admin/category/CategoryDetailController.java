package com.nhnacademy.shoppingmall.controller.mypage.admin.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/admin/category/detail.do")
public class CategoryDetailController implements BaseController {

    private final CategoryService service = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryId = req.getParameter("id");
        if (Objects.nonNull(categoryId)) {
            Category category = service.getCategory(Integer.parseInt(categoryId));
            req.setAttribute("category", category);
        }
        return "/shop/user/mypage/manage/category_detail";
    }
}
