package com.jeffrey.domain.model;

import com.jeffrey.common.PaymentMethod;
import com.jeffrey.common.PaymentStatus;
import com.jeffrey.domain.model.shared.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends CreatedDate {
    private Long paymentId;
    private Order order;
    private PaymentMethod method;
    private Long amount;
    private PaymentStatus status;
}
