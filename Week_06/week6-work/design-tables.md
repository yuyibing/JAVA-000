##### （必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

###### 用户表


```
CREATE TABLE `t_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `nickname` varchar(45) DEFAULT NULL COMMENT '用户昵称',
  `sex` int DEFAULT NULL COMMENT '用户性别',
  `birthday` bigint DEFAULT NULL COMMENT '用户生日',
  `is_member` int DEFAULT NULL COMMENT '是否为会员：0-不是；1-是',
  `status` int DEFAULT NULL COMMENT '用户状态：0-无效；1-有效',
  `register_time` bigint DEFAULT NULL COMMENT '用户注册时间',
  `phone` varchar(45) DEFAULT NULL COMMENT '用户联系电话',
  `password` varchar(45) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'
```

###### 订单表

```
CREATE TABLE `t_order` (
  `id` bigint NOT NULL COMMENT '订单ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `order_num` varchar(45) DEFAULT NULL COMMENT '订单号',
  `order_price` float DEFAULT NULL COMMENT '订单总价',
  `order_time` bigint DEFAULT NULL COMMENT '订购时间',
  `status` int DEFAULT NULL COMMENT '有效订单：0-无效订单；1-有效订单',
  `address_id` int DEFAULT NULL COMMENT '送货地址ID',
  `order_desc` varchar(1000) DEFAULT NULL COMMENT '订单描述',
  `user_mark` varchar(500) DEFAULT NULL COMMENT '用户订单备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_num_UNIQUE` (`order_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户订单表'
```

###### 商品表

```
CREATE TABLE `t_item` (
  `id` bigint NOT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `item_price` float DEFAULT NULL,
  `item_detail` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `item_pic` varchar(200) DEFAULT NULL COMMENT '商品图片地址',
  `create_time` bigint DEFAULT NULL COMMENT '商品创建时间',
  `status` int DEFAULT NULL COMMENT '商品状态：0-下架；1-未下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表'
```

###### 用户地址表

```
CREATE TABLE `t_user_address` (
  `id` bigint NOT NULL,
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `address` varchar(200) DEFAULT NULL COMMENT '用户收货地址',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户收货姓名',
  `phone` varchar(45) DEFAULT NULL COMMENT '收货用户号码',
  `is_default` int DEFAULT NULL COMMENT '是否默认地址：0-不是默认地址；1-是默认地址',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表'
```

###### 订单详情表

```
CREATE TABLE `t_order_detail` (
  `id` bigint NOT NULL,
  `order_num` varchar(45) DEFAULT NULL COMMENT '订单号',
  `item_id` int DEFAULT NULL COMMENT '商品ID',
  `item_num` int DEFAULT NULL COMMENT '商品数量',
  `total_price` float DEFAULT NULL COMMENT '订单中单商品总价',
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情表'
```



