package com.nhnacademy.shoppingmall.controller.mypage.admin.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/admin/category.do")
public class CategoryController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final int PAGE_SIZE = 10;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = (Objects.isNull(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page")));
        Page<Category> categoryList = categoryService.getCategoryOnPage(page, PAGE_SIZE);
        int total = categoryService.getTotalCount();

        total = (total == 0) ? 1 : total;
        total = (total % PAGE_SIZE == 0) ? total / PAGE_SIZE : (total / PAGE_SIZE) + 1;

        int currentBlock = page / PAGE_SIZE + 1;
        int startPage = (currentBlock - 1) * PAGE_SIZE + 1;
        int endPage = total < PAGE_SIZE ? total : startPage + PAGE_SIZE - 1;

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("page", page);

        return "shop/mypage/admin/category";
    }
}
