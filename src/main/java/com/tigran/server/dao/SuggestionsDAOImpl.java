package com.tigran.server.dao;
import com.tigran.server.dao.Interfaces.SuggestionsDAO;
import com.tigran.server.models.Suggestions;
import com.tigran.server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.util.List;

@Service
public class SuggestionsDAOImpl implements SuggestionsDAO {
    @Override
    public void create(Suggestions suggestions){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(suggestions);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error createSuggestions ", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public List<Suggestions> read(){
        List<Suggestions> suggestions = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Suggestions> criteria = builder.createQuery(Suggestions.class);
            criteria.from(Suggestions.class);
            suggestions = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readSuggestions", JOptionPane.OK_OPTION);
        }
        return suggestions;
    }
    @Override
    public void update(String id, Suggestions suggestions){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(suggestions);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllSuggestions", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public void delete(Suggestions suggestions){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(suggestions);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllSuggestions", JOptionPane.OK_OPTION);
        }
    }
}
