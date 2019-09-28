package com.bazinga.base.repository;

import com.bazinga.base.model.entirty.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends BaseEntity, ID extends Serializable> {

    List<T> findAll();
}
