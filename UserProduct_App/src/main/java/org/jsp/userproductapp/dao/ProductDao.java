package org.jsp.userproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public Product saveProduct(Product p, int id) {
		User u = manager.find(User.class, id);
		if (u != null) {
			u.getProduct().add(p);
			p.setUser(u);
			manager.persist(p);
			transaction.begin();
			transaction.commit();
			return p;
		} else {
			return null;
		}

	}

	public Product updateProduct(Product p, int id) {
		User u = manager.find(User.class, id);
		if (u != null) {
			u.getProduct().add(p);
			p.setUser(u);
			manager.merge(p);
			transaction.begin();
			transaction.commit();
			return p;
		} else {
			return null;
		}
	}

	public boolean deleteproduct(int user_id,int id) {
		User u=manager.find(User.class, user_id);
		if(u!=null) {
			Product p=manager.find(Product.class, id);
			manager.remove(p);
			transaction.begin();
			transaction.commit();
			return true;
		}else {
			return false;
		}
	}
	public List<Product> findProductByUser(int user_id) {
		String qry = "select u.product from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, user_id);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Product> findProductByBrand(String brand) {
		String qry = "select p from Product p where p.brand=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, brand);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
