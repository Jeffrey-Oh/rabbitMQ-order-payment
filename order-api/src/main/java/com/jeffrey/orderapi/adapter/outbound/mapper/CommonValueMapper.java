package com.jeffrey.orderapi.adapter.outbound.mapper;

import com.jeffrey.domain.vo.CreatedAt;
import com.jeffrey.domain.vo.Email;
import com.jeffrey.domain.vo.UpdatedAt;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CommonValueMapper {

    default CreatedAt mapCreatedAt(LocalDateTime value) {
        return new CreatedAt(value);
    }

    default LocalDateTime mapCreatedAt(CreatedAt value) {
        return value.getValue();
    }

    default UpdatedAt mapUpdatedAt(LocalDateTime value) {
        return new UpdatedAt(value);
    }

    default LocalDateTime mapUpdatedAt(UpdatedAt value) {
        return value.getValue();
    }

    default Email mapEmail(String value) {
        return new Email(value);
    }

    default String mapEmail(Email value) {
        return value.getValue();
    }

}
