package com.nhnacademy.shoppingmall.point.service.impl;

import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.impl.PointRepositoryImpl;
import com.nhnacademy.shoppingmall.point.service.PointService;

public class PointServiceImpl implements PointService {

    private final PointRepositoryImpl pointRepository;

    public PointServiceImpl(PointRepositoryImpl pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public void save(Point point) {
        pointRepository.save(point);
    }
}
