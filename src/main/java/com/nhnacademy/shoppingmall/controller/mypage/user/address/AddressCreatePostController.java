package com.nhnacademy.shoppingmall.controller.mypage.user.address;


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
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/user/address/create.do")
public class AddressCreatePostController implements BaseController {

    AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");
        HttpSession session = req.getSession(false);

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("userId"))) {
            return "redirect:/login.do";
        }

        String userId = String.valueOf(session.getAttribute("userId"));
        addressService.save(new Address(address, userId));
        return "redirect:/mypage/user/address.do";
    }
}
