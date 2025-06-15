-- 사용자 테이블
CREATE TABLE user
(
    userId       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    email        VARCHAR(255) NOT NULL UNIQUE,
    passwordHash VARCHAR(255) NOT NULL,
    createdAt    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 메뉴 테이블
CREATE TABLE menu
(
    menuId      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    price       INT          NOT NULL,
    isAvailable BOOLEAN      NOT NULL DEFAULT TRUE,
    createdAt   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 주문 테이블
CREATE TABLE `order`
(
    orderId     BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId      BIGINT      NOT NULL,
    totalAmount INT         NOT NULL,
    status      VARCHAR(20) NOT NULL, -- 예: PENDING, PAID, FAILED
    createdAt   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX       idx_userId(userId),
    INDEX       idx_status(status)
);

-- 주문 아이템 테이블
CREATE TABLE orderItem
(
    orderItemId BIGINT AUTO_INCREMENT PRIMARY KEY,
    orderId     BIGINT   NOT NULL,
    menuId      BIGINT   NOT NULL,
    quantity    INT      NOT NULL,
    price       INT      NOT NULL,
    totalPrice  INT      NOT NULL,
    createdAt   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (orderId) REFERENCES `order` (orderId),
    FOREIGN KEY (menuId) REFERENCES menu (menuId)
);

-- 결제 테이블 (external PG 가정 없이 로컬 테스트용)
CREATE TABLE payment
(
    paymentId BIGINT AUTO_INCREMENT PRIMARY KEY,
    orderId   BIGINT      NOT NULL,
    method    VARCHAR(30) NOT NULL, -- ex: CARD, KAKAO_PAY
    amount    INT         NOT NULL,
    status    VARCHAR(20) NOT NULL, -- INIT, SUCCESS, FAILED
    createdAt DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (orderId) REFERENCES `order` (orderId),
    INDEX     idx_orderId(orderId),
    INDEX     idx_status(status)
);