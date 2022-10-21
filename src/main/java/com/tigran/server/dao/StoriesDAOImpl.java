package com.tigran.server.dao;
import com.tigran.server.dao.Interfaces.StoriesDAO;
import com.tigran.server.models.Stories;
import com.tigran.server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.util.List;
@Service
public class StoriesDAOImpl implements StoriesDAO {
    @Override
    public void create(Stories stories){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(stories);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error createStories ", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public List<Stories> read(){
        List<Stories> stories = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Stories> criteria = builder.createQuery(Stories.class);
            criteria.from(Stories.class);
            stories = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readStories", JOptionPane.OK_OPTION);
        }
        return stories;
    }
    @Override
    public void update(String id, Stories stories){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(stories);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllStories", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public void delete(Stories stories){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(stories);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllStories", JOptionPane.OK_OPTION);
        }
    }
}
