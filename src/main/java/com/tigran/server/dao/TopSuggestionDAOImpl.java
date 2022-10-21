package com.tigran.server.dao;
import com.tigran.server.dao.Interfaces.TopSuggestionDAO;
import com.tigran.server.models.TopSuggestion;
import com.tigran.server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.util.List;

@Service
public class TopSuggestionDAOImpl implements TopSuggestionDAO {
    @Override
    public void create(TopSuggestion topSuggestion){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(topSuggestion);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error createTopSuggestion ", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public List<TopSuggestion> read(){
        List<TopSuggestion> topSuggestion = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TopSuggestion> criteria = builder.createQuery(TopSuggestion.class);
            criteria.from(TopSuggestion.class);
            topSuggestion = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readTopSuggestion", JOptionPane.OK_OPTION);
        }
        return topSuggestion;
    }
    @Override
    public void update(String id, TopSuggestion topSuggestion){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(topSuggestion);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllTopSuggestion", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public void delete(TopSuggestion topSuggestion){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(topSuggestion);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllTopSuggestion", JOptionPane.OK_OPTION);
        }
    }
}
