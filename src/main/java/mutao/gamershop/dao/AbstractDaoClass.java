package mutao.gamershop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import mutao.gamershop.model.AbstractEntityClass;

@javax.faces.bean.RequestScoped
public abstract class AbstractDaoClass<E extends AbstractEntityClass> {

	protected final EntityManager em;

	public AbstractDaoClass(EntityManager em) {
		this.em = em;
	}

	public E insert(E entity) {
		this.em.persist(entity);
		em.refresh(entity);
		return entity;
	}

	public E get(Class<E> entityClass, Long id) {
		return (E) this.em.find(entityClass, id);
	}

	public E update(E entity) {
		return this.em.merge(entity);
	}

	public boolean delete(E entity) {
		entity = em.merge(entity);
		this.em.remove(entity);
		return true;
	}

	public List<E> listAll(Class<E> entityClass) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);

		Root<E> root = query.from(entityClass);
		Order[] orderBy = { criteriaBuilder.asc(root.get("id")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<E> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
	
	public List<E> mostVoted(Class<E> entityClass) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);

		Root<E> root = query.from(entityClass);
		Order[] orderBy = { criteriaBuilder.desc(root.get("votes")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<E> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
	
	public void resetTable(Class<?> ... classes) {

		for (Class<?> entityClass : classes) {

			String COLUMN_NAME = getColumnName(entityClass);

			Query query = em.createNativeQuery("DELETE FROM " + COLUMN_NAME + " WHERE id > 0;");
			query.executeUpdate();

			/*
			 * Resetar o increment no H2
			 */
			query = em.createNativeQuery("ALTER TABLE " + COLUMN_NAME + " ALTER COLUMN id RESTART WITH 1;");
			query.executeUpdate();

		}
		em.clear();
	}

	protected String getColumnName(Class<?> entityClass) {
		return ((Table) entityClass.getAnnotation(Table.class)).name();
	}


	
}
