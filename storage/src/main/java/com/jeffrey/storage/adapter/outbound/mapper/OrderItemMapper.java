package com.jeffrey.storage.adapter.outbound.mapper;

import com.jeffrey.domain.OrderItem;
import com.jeffrey.storage.entity.OrderItemEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {CommonValueMapper.class, MenuMapper.class}
)
public interface OrderItemMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    OrderItem toDomain(OrderItemEntity orderItemEntity);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    OrderItemEntity toEntity(OrderItem orderItem);

}
