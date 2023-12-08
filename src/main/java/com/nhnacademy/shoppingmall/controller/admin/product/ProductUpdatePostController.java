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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/update.do")
public class ProductUpdatePostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService =
            new ProductCategoryServiceImpl(new ProductCategoryRepositoryImpl(), new ProductRepositoryImpl(),
                    new CategoryRepositoryImpl());
    private final String DEFAULT_PATH =
            "/Users/mj/nhn-cia/java-servlet-jsp-shoppingmall/src/main/webapp/resources/";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
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
                            fileName = new File(
                                    UUID.randomUUID() + "_" + item.getName().replace("/resources/", "")).getName();
                            if (!new File(DEFAULT_PATH + fileName).exists()) {
                                item.write(new File(DEFAULT_PATH + fileName));
                            }
                        }
                        log.info("{}, {}", item.getFieldName(), fileName);
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

        if (Objects.nonNull(map.get("productName")) && Objects.nonNull(map.get("unitCost"))) {
            BigDecimal unitCost = new BigDecimal(map.get("unitCost"));
            String productImage =
                    "delete".equals(map.get("deletePreviousProductImage")) ? map.get("productImage") :
                            map.get("previousProductImage").replace("/resources/", "");
            String thumbnail =
                    "delete".equals(map.get("deletePreviousThumbnail")) ? map.get("thumbnail") :
                            map.get("previousThumbnail").replace("/resources/", "");


            // validation
            productService.updateProduct(Integer.parseInt(map.get("productId")), map.get("productName"), unitCost, Integer.parseInt(map.get("stock")), map.get("description"),
                    productImage, thumbnail);
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(Integer.valueOf(map.get("category1")));
        treeSet.add(Integer.valueOf(map.get("category2")));
        treeSet.add(Integer.valueOf(map.get("category3")));
        treeSet.remove(0);

        //update -> product, product_category update
//        int productId = Integer.parseInt(map.get("productId"));
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
        int productId = Integer.parseInt(map.get("productId"));
        productCategoryService.deleteProductCategoryByProductId(productId);
        for (Integer categoryId : treeSet) {
            ProductCategory productCategory = new ProductCategory(productId, categoryId);
            productCategoryService.saveProductCategory(productCategory);
        }
        return "redirect:/admin/product.do";
    }
}
