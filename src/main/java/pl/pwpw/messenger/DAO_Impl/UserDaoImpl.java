package pl.pwpw.messenger.DAO_Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.pwpw.messenger.DAO.UserDAO;
import pl.pwpw.messenger.Models.Message;
import pl.pwpw.messenger.Models.User;

@Repository
public class UserDaoImpl implements UserDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void addUser(User newUser) {
		entityManager.persist(newUser);
	}

	@Override
	public User getUserById(long Id) {
		return entityManager.find(User.class, Id);
	}

	@Override
	public Iterable<User> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    Root<User> userRoot = cq.from(User.class);
	    cq.select(userRoot);
		return entityManager.createQuery(cq).getResultList();
	}

}
