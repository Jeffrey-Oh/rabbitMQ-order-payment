package com.jeffrey.orderapi.adapter.outbound.mapper;

import com.jeffrey.domain.Menu;
import com.jeffrey.orderapi.entity.MenuEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {CommonValueMapper.class}
)
public interface MenuMapper {

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    Menu toDomain(MenuEntity menuEntity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    MenuEntity toEntity(Menu menu);

}
