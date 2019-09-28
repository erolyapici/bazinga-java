package com.bazinga.base.model.converter;

import com.bazinga.base.model.dto.BaseDto;
import com.bazinga.base.model.entirty.BaseEntity;

public interface GenericDtoConverter<T extends BaseDto, S extends BaseEntity> {
    T convert(S entity);
}
