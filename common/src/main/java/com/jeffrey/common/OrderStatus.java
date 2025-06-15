package com.jeffrey.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED("주문생성"), // order 생성
    CONFIRMED("주문확인"), // order consumer 확인
    CANCELLED("주문취소"), // 사용자 취소
    COMPLETED("주문완료"); // 결제 완료

    private final String description;
}
