CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL COMMENT '가입일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';


CREATE TABLE `product`
(
    `product_id`     INT           NOT NULL AUTO_INCREMENT COMMENT '제품 아이디',
    `product_number` VARCHAR(10)   NOT NULL COMMENT '제품 번호',
    `product_name`   VARCHAR(120) NOT NULL COMMENT '제품 이름',
    `unit_cost`      DECIMAL(15)   NOT NULL COMMENT '가격',
    `description`    TEXT          NULL COMMENT '설명',
    `product_image`  VARCHAR(30)  NULL COMMENT '제품 이미지',
    `thumbnail`      VARCHAR(30)  NULL COMMENT '제품 썸네일',

    CONSTRAINT pk_product PRIMARY KEY (product_id)
);


CREATE TABLE `category`
(
    `category_id`   INT         NOT NULL AUTO_INCREMENT COMMENT '카테고리 아이디',
    `category_name` VARCHAR(50) NOT NULL COMMENT '카테고리 이름',

    CONSTRAINT pk_category PRIMARY KEY (category_id),
    UNIQUE (category_name)
);



CREATE TABLE `review`
(
    `review_id`  INT         NOT NULL AUTO_INCREMENT COMMENT '리뷰 아이디',
    `rating`     INT         NOT NULL COMMENT '별점',
    `comments`   TEXT        NULL COMMENT '리뷰 내용',
    `product_id` INT         NOT NULL COMMENT '제품 아이디',
    `user_id`    VARCHAR(50) NOT NULL COMMENT '아이디',

    CONSTRAINT pk_review PRIMARY KEY (review_id),
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_review_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE `user_address`
(
    `address_id` INT          NOT NULL AUTO_INCREMENT COMMENT '주소 아이디',
    `address`    VARCHAR(255) NOT NULL COMMENT '주소',
    `user_id`    VARCHAR(50)  NOT NULL COMMENT '아이디',

    CONSTRAINT pk_user_address PRIMARY KEY (address_id),
    CONSTRAINT fk_user_address_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);


CREATE TABLE `order`
(
    `order_id`   INT         NOT NULL AUTO_INCREMENT COMMENT '주문 아이디',
    `order_date` DATETIME    NOT NULL COMMENT '주문일자',
    `ship_date`  DATETIME    NOT NULL COMMENT '배송일자',
    `user_id`    VARCHAR(50) NOT NULL COMMENT '아이디',
    `address_id` INT         NULL COMMENT '주소 아이디',

    CONSTRAINT pk_order PRIMARY KEY (order_id),
    CONSTRAINT fk_order_users_address FOREIGN KEY (address_id) REFERENCES user_address (address_id),
    CONSTRAINT fk_order_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE `order_detail`
(
    `order_id`   INT         NOT NULL COMMENT '주문 아이디',
    `product_id` INT         NOT NULL COMMENT '제품 아이디',
    `quantity`   INT         NOT NULL COMMENT '수량',
    `unit_cost`  DECIMAL(15) NOT NULL COMMENT '가격',

    CONSTRAINT pk_order_detail PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_detail_order FOREIGN KEY (order_id) REFERENCES `order` (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_detail_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE `cart`
(
    `record_id`    INT         NOT NULL AUTO_INCREMENT COMMENT '장바구니 아이디',
    `quantity`     INT         NOT NULL COMMENT '수량',
    `date_created` DATETIME    NOT NULL COMMENT '생성 일자',
    `product_id`   INT         NOT NULL COMMENT '제품 아이디',
    `user_id`      VARCHAR(50) NOT NULL COMMENT '아이디',

    CONSTRAINT pk_cart PRIMARY KEY (record_id),
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE `user_point`
(
    `point_id`      INT         NOT NULL AUTO_INCREMENT COMMENT '포인트 아이디',
    `point_changed` INT         NOT NULL COMMENT '증감 포인트',
    `point_date`    DATETIME    NOT NULL COMMENT '사용 일자',
    `user_id`       VARCHAR(50) NOT NULL COMMENT '아이디',

    CONSTRAINT pk_users_point PRIMARY KEY (point_id),
    CONSTRAINT fk_user_point_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);



CREATE TABLE `product_category`
(
    `product_id`  INT NOT NULL COMMENT '제품 아이디',
    `category_id` INT NOT NULL COMMENT '카테고리 아이디',

    CONSTRAINT pk_product_category PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_product_category_product FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_product_category_category FOREIGN KEY (category_id) REFERENCES category (category_id)
);
