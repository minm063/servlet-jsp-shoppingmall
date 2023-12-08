package com.nhnacademy.shoppingmall.controller.admin.product;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductCategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/create.do")
public class ProductCreatePostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl(), new ProductRepositoryImpl(),
                    new CategoryRepositoryImpl());
    private final String DEFAULT_PATH =
            "/Users/mj/nhn-cia/java-servlet-jsp-shoppingmall/src/main/webapp/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String productNumber = String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 1000000000L));
        Map<String, String> map = new HashMap<>();

        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        // 파일 처리 로직
                        String fileName = null;
                        if (item.getFieldName().equals("thumbnail") || item.getFieldName().equals("productImage")) {
                            fileName = "no-image.png";
                        }
                        if (!item.getName().isEmpty() && item.getName() != null) {
                            fileName = new File(LocalDateTime.now() + "_" + item.getName()).getName();
                            log.info(DEFAULT_PATH + fileName);
                            if (!new File(DEFAULT_PATH + fileName).exists()) {
                                item.write(new File(DEFAULT_PATH + fileName));
                            }
                        }
                        log.info(item.getFieldName(), fileName);
                        map.put(item.getFieldName(), fileName);
                    } else {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
                        log.info("{}, {}", fieldName, fieldValue);
                        map.put(fieldName, fieldValue);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(Integer.valueOf(map.get("category1")));
        treeSet.add(Integer.valueOf(map.get("category2")));
        treeSet.add(Integer.valueOf(map.get("category3")));

        if (Objects.nonNull(map.get("productName")) && Objects.nonNull(map.get("unitCost"))) {
            if (treeSet.size() == 1 && treeSet.last() == 0) {
                return "redirect:/admin/product/detail.do";
            }
            BigDecimal unitCost = new BigDecimal(map.get("unitCost"));
            Product product = new Product(productNumber, map.get("productName"), unitCost, Integer.parseInt(map.get("stock")),
                    map.get("description"),
                    map.get("productImage"), map.get("thumbnail"));
            productService.saveProduct(product);

            int productId = productService.getIndex();
            for (Integer categoryId : treeSet) {
                if (categoryId != 0) {
                    productCategoryService.saveProductCategory(new ProductCategory(productId, categoryId));
                }
            }

            return "redirect:/admin/menu.do";
        }
        return "redirect:/admin/product/detail.do";
    }
}
