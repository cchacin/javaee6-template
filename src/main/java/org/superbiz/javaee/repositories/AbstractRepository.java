package org.superbiz.javaee.repositories;

import org.superbiz.javaee.entities.Model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepository<E extends Model, ID extends Serializable> {

	@Inject
	private EntityManager em;

	protected Class<E> entityClass;

	public AbstractRepository() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	public E create(E e) {
		em.persist(e);
		return e;
	}

	public E update(E e) {
		return em.merge(e);
	}

	public void delete(ID id) {
		em.remove(em.find(entityClass, id));
	}

	public E find(ID id) {
		return em.find(entityClass, id);
	}

	public List<E> find(String query, int min, int max) {
		return queryRange(em.createQuery(query, entityClass), min, max)
				.getResultList();
	}

	public List<E> namedFind(String query, int min, int max) {
		return queryRange(em.createNamedQuery(query, entityClass), min, max)
				.getResultList();
	}

	private static Query queryRange(Query query, int min, int max) {
		if (max >= 0) {
			query.setMaxResults(max);
		}
		if (min >= 0) {
			query.setFirstResult(min);
		}
		return query;
	}
}
