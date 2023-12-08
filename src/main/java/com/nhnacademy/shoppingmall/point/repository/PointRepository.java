package com.nhnacademy.shoppingmall.point.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.point.domain.Point;

public interface PointRepository {
    int save(Point point);

    Page<Point> findPointByUserIdOnPage(String userId, int page, int pageSize);

    int totalCount(String userId);
}
