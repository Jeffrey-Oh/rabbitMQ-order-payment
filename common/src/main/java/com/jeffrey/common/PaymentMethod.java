package com.jeffrey.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CREDIT_CARD("신용카드"),
    KAKAO_PAY("카카오페이"),
    NAVER_PAY("네이버페이"),
    BANK_TRANSFER("은행 이체");

    private final String description;
}
