package com.jeffrey.storage.adapter.outbound.mapper;

import com.jeffrey.domain.Order;
import com.jeffrey.domain.OrderItem;
import com.jeffrey.storage.entity.OrderEntity;
import com.jeffrey.storage.entity.OrderItemEntity;
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

    List<OrderItem> toDomainOrderItems(List<OrderItemEntity> orderItems);

    @AfterMapping
    default void linkEntityOrderItems(@MappingTarget Order domain, OrderEntity order) {
        List<OrderItem> items = toDomainOrderItems(order.getOrderItems());
        for (OrderItem item : items) {
            domain.addItem(item);
        }
    }

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    OrderEntity toEntity(Order order);

    List<OrderItemEntity> toEntityOrderItems(List<OrderItem> orderItems);

    @AfterMapping
    default void linkEntityOrderItems(@MappingTarget OrderEntity order, Order domain) {
        List<OrderItemEntity> items = toEntityOrderItems(domain.getOrderItems());
        for (OrderItemEntity item : items) {
            order.addItem(item);
        }
    }

}
