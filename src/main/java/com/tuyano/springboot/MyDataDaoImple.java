package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImple implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public MyDataDaoImple() {
			super();
	}

	public MyDataDaoImple(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@Override
	public List<MyData> getAll() {
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public MyData findById(long id) {
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByName(String name) {
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}

	@Override
	public List<MyData> find(String fstr) {
		List<MyData> list = null;
		String qstr = "from MyData where id = :fstr";
		Query query = entityManager.createQuery(qstr)
				.setParameter("fstr",  Long.parseLong(fstr));
		list = query.getResultList();
		return list;
	}
}
