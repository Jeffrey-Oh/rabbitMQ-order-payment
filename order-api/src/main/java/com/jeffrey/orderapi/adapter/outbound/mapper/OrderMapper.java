package com.jeffrey.orderapi.adapter.outbound.mapper;

import com.jeffrey.domain.Order;
import com.jeffrey.domain.OrderItem;
import com.jeffrey.orderapi.entity.OrderEntity;
import com.jeffrey.orderapi.entity.OrderItemEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {CommonValueMapper.class, OrderItemMapper.class}
)
public interface OrderMapper {

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    Order toDomain(OrderEntity orderEntity);

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity toEntity(Order order);

    List<OrderItemEntity> toOrderItems(List<OrderItem> orderItems);

    @AfterMapping
    default void linkOrderItems(@MappingTarget OrderEntity order, Order domain) {
        List<OrderItemEntity> items = toOrderItems(domain.getOrderItems());
        for (OrderItemEntity item : items) {
            order.addItem(item);
        }
    }

}
