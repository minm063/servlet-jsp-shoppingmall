package com.nhnacademy.shoppingmall.point.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.impl.PointRepositoryImpl;
import com.nhnacademy.shoppingmall.point.service.PointService;
import java.util.ArrayList;

public class PointServiceImpl implements PointService {

    private final PointRepositoryImpl pointRepository;

    public PointServiceImpl(PointRepositoryImpl pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public void save(Point point) {
        pointRepository.save(point);
    }

    @Override
    public Page<Point> getPointByUserIdOnPage(String userId, int page, int pageSize) {
        if (pointRepository.totalCount(userId) == 0) {
            return new Page<>(new ArrayList<>(), 0);
        }

        return pointRepository.findPointByUserIdOnPage(userId, page, pageSize);
    }

    @Override
    public int getTotalCount(String userId) {
        return pointRepository.totalCount(userId);
    }
}
