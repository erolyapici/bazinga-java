package com.bazinga.base.repository;

import com.bazinga.base.model.entirty.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoFactory<T extends BaseEntity, ID extends Serializable> implements GenericDao<T, ID> {
    private Class<T> persistentClass;
    private SessionFactory sessionFactory;

    public GenericDaoFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private CriteriaQuery<T> createCriteria() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(persistentClass);
        return query;
    }

    public List<T> findAll() {
        CriteriaQuery<T> c = createCriteria();
        Root<T> from = c.from(persistentClass);
        return getSession().createQuery(c.select(from)).getResultList();
    }

}
