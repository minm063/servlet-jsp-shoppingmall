package com.nhnacademy.shoppingmall.controller.user.address;


import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/address/updateAction.do")
public class AddressUpdateFormController implements BaseController {

    AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int addressId = Integer.parseInt(req.getParameter("addressId"));
        String address = req.getParameter("address");

        log.info("address update form {}, {}", addressId, address);

        addressService.update(addressId, address);
        return "redirect:/mypage/address.do";
    }
}
