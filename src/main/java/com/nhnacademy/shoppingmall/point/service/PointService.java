package com.nhnacademy.shoppingmall.point.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.point.domain.Point;

public interface PointService {
    void save(Point point);

    Page<Point> getPointByUserIdOnPage(String userId, int page, int pageSize);

    int getTotalCount(String userId);
}
