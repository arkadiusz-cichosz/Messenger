package pl.pwpw.messenger.DAO_Impl;

import java.util.ArrayList;
import java.util.List;

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

import pl.pwpw.messenger.DAO.MessageDAO;
import pl.pwpw.messenger.Models.Message;
import pl.pwpw.messenger.Models.User;

@Repository
public class MessageDaoImpl implements MessageDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void addMessage(Message newMessage) {
		entityManager.persist(newMessage);
	}

	@Override
	public Message getMessageById(long Id) {
		return entityManager.find(Message.class, Id);
	}

	@Override
	public Iterable<Message> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Message> cq = cb.createQuery(Message.class);
	    Root<Message> messageRoot = cq.from(Message.class);
	    cq.select(messageRoot);
		return entityManager.createQuery(cq).getResultList();
	}

}
