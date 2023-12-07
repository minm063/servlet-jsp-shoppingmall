package com.nhnacademy.shoppingmall.controller.user.address;


import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/address/update.do")
public class AddressUpdateController implements BaseController {

    AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int addressId = Integer.parseInt(req.getParameter("id"));
        log.info("update controller) addressId = {} ", addressId);

        if (Objects.isNull(addressService.getAddress(addressId))) {
            log.info("null");
            return "redirect:/mypage.do";
        }

        req.setAttribute("address", addressService.getAddress(addressId));
        return "shop/mypage/user/address_detail";
    }
}
