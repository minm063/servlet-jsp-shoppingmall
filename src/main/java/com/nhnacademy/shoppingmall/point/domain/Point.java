package com.nhnacademy.shoppingmall.point.domain;

import java.time.LocalDateTime;

public class Point {
    private int pointId;
    private int pointChanged;
    private LocalDateTime pointDate;
    private String userId;

    public Point(int pointId, int pointChanged, LocalDateTime pointDate, String userId) {
        this.pointId = pointId;
        this.pointChanged = pointChanged;
        this.pointDate = pointDate;
        this.userId = userId;
    }

    public Point(int pointChanged, LocalDateTime pointDate, String userId) {
        this.pointChanged = pointChanged;
        this.pointDate = pointDate;
        this.userId = userId;
    }

    public int getPointId() {
        return pointId;
    }

    public int getPointChanged() {
        return pointChanged;
    }

    public LocalDateTime getPointDate() {
        return pointDate;
    }

    public String getUserId() {
        return userId;
    }
}
