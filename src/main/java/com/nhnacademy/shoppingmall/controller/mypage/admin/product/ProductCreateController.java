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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/admin/product/create.do")
public class ProductCreateController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl());
    private final static String DEFAULT_PATH = "/Users/mj/nhn-cia/java-servlet-jsp-shoppingmall/src/main/webapp/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productNumber = String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 1000000000L));
        String productName = req.getParameter("productName");
        String description = req.getParameter("description");

        Part part = req.getPart("productImage");
        System.out.println(part);
        String productImage = getFilename(part);
        System.out.println("product image " + productImage);
        String imagePath = null;
        if (!productImage.isEmpty()) {
            System.out.println("empty?");
            part.write(DEFAULT_PATH + productImage);
            imagePath = DEFAULT_PATH + productImage;
        }

        String thumbnailPath = DEFAULT_PATH + "no-image.png";
        part = req.getPart("thumbnail");
        String thumbnail = getFilename(part);
        System.out.println("thumbnail " + thumbnail);
        if (!thumbnail.isEmpty()) {
            part.write(DEFAULT_PATH + thumbnail);
            thumbnailPath = DEFAULT_PATH + thumbnail;
        }


        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(Integer.parseInt(req.getParameter("category1")));
        treeSet.add(Integer.parseInt(req.getParameter("category2")));
        treeSet.add(Integer.parseInt(req.getParameter("category3")));

        if (Objects.nonNull(productName) && Objects.nonNull(req.getParameter("unitCost"))) {
            BigDecimal unitCost = new BigDecimal(req.getParameter("unitCost"));
            Product product = new Product(productNumber, productName, unitCost, description, imagePath, thumbnailPath);
            productService.saveProduct(product);

            int productId = productService.getIndex();
            if (treeSet.size() == 1 && treeSet.last() == 0) {
                return "redirect:/mypage/admin/product/detail.do";
            }
            for (Integer categoryId : treeSet) {
                if (categoryId != 0) {
                    productCategoryService.saveProductCategory(new ProductCategory(productId, categoryId));
                }
            }

            return "redirect:/mypage/admin.do";
        }

        return "redirect:/mypage/admin/product/detail.do";
    }

    private String getFilename(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] split = contentDisp.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }
}
