package com.bazinga.base.repository;

import com.bazinga.base.model.entirty.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class GenericDaoFactory<T extends BaseEntity, ID extends Serializable> implements GenericDao<T, ID> {
    private Class<T> persistentClass;
    private SessionFactory sessionFactory;

    public GenericDaoFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public T getSingleResult(String propertyName, Object value) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        query.select(root);
        query.where(cb.equal(root.get(propertyName), value));
        Query<T> q = getSession().createQuery(query);
        return q.getSingleResult();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }


    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public CriteriaQuery<T> createCriteria() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(persistentClass);
        return query;
    }

    public Optional<List<T>> findAll() {
        CriteriaQuery<T> c = createCriteria();
        Root<T> from = c.from(persistentClass);
        return Optional.ofNullable(getSession().createQuery(c.select(from)).getResultList());
    }

}
