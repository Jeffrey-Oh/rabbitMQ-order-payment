package com.jeffrey.paymentconsumer.application.service;

import com.jeffrey.common.enums.PaymentMethod;
import com.jeffrey.commondto.rabbitmq.dto.CompletedPaymentMessage;
import com.jeffrey.commondto.rabbitmq.dto.PaymentMessage;
import com.jeffrey.paymentconsumer.application.usecase.PaymentUseCase;
import com.jeffrey.port.out.PaymentMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentMessagePublisher paymentMessagePublisher;

    @Override
    public void processed(PaymentMessage paymentMessage) {
        // 1. 결제 가능 여부 판단 (가결제)
        PaymentMethod paymentMethod = paymentMessage.paymentMethod();
        if (!isPaymentPossible(paymentMethod)) {
            // 결제 불가능한 경우 예외 처리 또는 메시지 발행
            throw new IllegalArgumentException("결제 불가능한 결제 방법입니다: " + paymentMethod);
        }

        // 2. 가결제 성공 시 실결제 실행
        boolean paymentSuccess = executePayment(paymentMethod);

        // 3. 결제 성공 시 결제 완료 메시지 발행
        if (paymentSuccess) {
            CompletedPaymentMessage completedPaymentMessage = CompletedPaymentMessage.from(paymentMessage.orderId());
            paymentMessagePublisher.publishPaymentCompleted(completedPaymentMessage);
        } else {
            // 결제 실패 시 예외 처리 또는 메시지 발행
            throw new RuntimeException("결제 실패: " + paymentMethod);
        }
    }

    private boolean isPaymentPossible(PaymentMethod paymentMethod) {
        // 결제 가능 여부를 판단하는 로직을 구현
        // 예: 특정 결제 수단에 대한 제한 사항 확인
        switch (paymentMethod) {
            case CREDIT_CARD:
                // 신용카드 결제 로직
                break;
            case KAKAO_PAY:
                // 카카오페이 결제 로직
                break;
            case NAVER_PAY:
                // 네이버페이 결제 로직
                break;
            case BANK_TRANSFER:
                // 은행 이체 결제 로직
                break;
            default:
                // 지원하지 않는 결제 방법
                throw new IllegalArgumentException("지원하지 않는 결제 방법입니다: " + paymentMethod);
        }
        return true; // 임시로 true 반환, 실제 로직 구현 필요
    }

    private boolean executePayment(PaymentMethod paymentMethod) {
        // 실제 결제 실행 로직을 구현
        // 예: 결제 API 호출 등
        switch (paymentMethod) {
            case CREDIT_CARD:
                // 신용카드 결제 로직
                break;
            case KAKAO_PAY:
                // 카카오페이 결제 로직
                break;
            case NAVER_PAY:
                // 네이버페이 결제 로직
                break;
            case BANK_TRANSFER:
                // 은행 이체 결제 로직
                break;
            default:
                // 지원하지 않는 결제 방법
                throw new IllegalArgumentException("지원하지 않는 결제 방법입니다: " + paymentMethod);
        }
        return true; // 임시로 true 반환, 실제 로직 구현 필요
    }

}
