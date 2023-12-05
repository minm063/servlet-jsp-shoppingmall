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

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/admin/category/create.do")
public class CategoryCreateController implements BaseController {

    private final CategoryService service = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryName = req.getParameter("categoryName");

        if (Objects.nonNull(categoryName)) {
            service.saveCategory(new Category(categoryName));
            return "redirect:/mypage/admin.do";
        }

        return "redirect:/mypage/admin/category/detail.do";
    }
}
