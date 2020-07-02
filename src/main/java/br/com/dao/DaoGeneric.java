package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	public void salvar(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entity);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	public E updateMerge(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E updateMerge = entityManager.merge(entity);
		
		entityTransaction.commit();
		entityManager.close();
		
		return updateMerge;
	}
	
	public void delete(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entity);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void deleteById(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Object id = JPAUtil.getPrimaryKeyObject(entity);
		
		entityManager.createQuery("delete from " + entity.getClass().getCanonicalName() + " where id = " + id)
		.executeUpdate();
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	public List<E> getListEntity(Class<E> entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction enTransaction = entityManager.getTransaction();
		enTransaction.begin();
		
		List<E> list = entityManager.createQuery("from " + entity.getName()).getResultList();
		
		enTransaction.commit();
		entityManager.close();
		
		return list;
	}
	
}
