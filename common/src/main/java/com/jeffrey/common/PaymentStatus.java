package com.jeffrey.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PENDING("결제대기"), // 결제 가능 여부 판단 대기
    PAID("결제완료"), // 결제 성공 + order status COMPLETED
    FAILED("결제실패"); // 결제 실패

    private final String description;
}
