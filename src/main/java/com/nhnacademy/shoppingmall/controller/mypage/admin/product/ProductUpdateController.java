package com.nhnacademy.shoppingmall.controller.mypage.admin.product;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductCategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.math.BigDecimal;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/admin/product/update.do")
public class ProductUpdateController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = new Product(
                productId,
                req.getParameter("productNumber"),
                req.getParameter("productName"),
                new BigDecimal(req.getParameter("unitCost")),
                req.getParameter("description"),
                req.getParameter("productImage"),
                req.getParameter("thumbnail")
        );

        productService.updateProduct(product);

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(Integer.parseInt(req.getParameter("category1")));
        treeSet.add(Integer.parseInt(req.getParameter("category2")));
        treeSet.add(Integer.parseInt(req.getParameter("category3")));
        treeSet.remove(0);

        // update -> product, product_category update
//        int count = productCategoryService.countByProductId(productId);
//        if (treeSet.size() == count) {
//            for (Integer categoryId : treeSet) {
//                productCategoryService.updateProductCategory(new ProductCategory(productId, categoryId));
//            }
//        } else if (treeSet.size() < count) { // 저장돼있던 수가 더 많음
//            productCategoryService.countByProductId(productId);
//        } else { // category가 추가됨
//            for (Integer categoryId : treeSet) {
//                ProductCategory productCategory = new ProductCategory(productId, categoryId);
//                if (Boolean.FALSE.equals(productCategoryService.productCategoryExist(productCategory))) {
//                    productCategoryService.updateProductCategory(productCategory);
//                }
//            }
//        }
//        for (Integer categoryId : treeSet) {
//            ProductCategory productCategory = new ProductCategory(productId, categoryId);
//            // count == origin -> update, count < origin -> delete, count > origin -> save
//            // categoryId로 productId를 갖고올 수 있으면 (in service, if)
//            if (Boolean.TRUE.equals(productCategoryService.productCategoryExist(productCategory))) {
//                System.out.println("here");
//                productCategoryService.updateProductCategory(productCategory);
//            } else {
//                System.out.println("no, here");
//                System.out.println(categoryId);
//                productCategoryService.saveProductCategory(productCategory);
//            }
//        }
        productCategoryService.deleteProductCategoryByProductId(productId);
        for (Integer categoryId : treeSet) {
            ProductCategory productCategory = new ProductCategory(productId, categoryId);
            productCategoryService.saveProductCategory(productCategory);
        }
        return "redirect:/mypage/admin.do";
    }
}
