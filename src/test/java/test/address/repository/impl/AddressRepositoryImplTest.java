package test.address.repository.impl;


import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AddressRepositoryImplTest {
    AddressRepository addressRepository = new AddressRepositoryImpl();

    Address testAddress;

    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
        testAddress = new Address(1, "nhn-address", "test-user");
        addressRepository.save(testAddress);
    }

    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @Order(1)
    @DisplayName("address 등록")
    void save() {
        Address address1 = new Address(2, "nhn-address2", "test-user");
        Address address2 = new Address(2, "nhn-address3", "test-user");
        Address address3 = new Address(2, "nhn-address4", "test-user2");

        int result1 = addressRepository.save(address1);
        int result2 = addressRepository.save(address2);
        int result3 = addressRepository.save(address3);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result1),
                () -> Assertions.assertEquals(1, result2),
                () -> Assertions.assertEquals(1, result3),
                () -> Assertions.assertEquals(address1,
                        addressRepository.findAddressByAddressId(address1.getAddressId()).get()),
                () -> Assertions.assertEquals(address2,
                        addressRepository.findAddressByAddressId(address2.getAddressId()).get()),
                () -> Assertions.assertEquals(address3,
                        addressRepository.findAddressByAddressId(address3.getAddressId()).get())
        );
    }

    @Test
    @DisplayName("address 조회 by userId")
    void findAddressByUserId() {
        List<Address> addressList = addressRepository.findAddressListByUserId(testAddress.getUserId());
//        Assertions.assertEquals(testAddress, add);
    }
}
