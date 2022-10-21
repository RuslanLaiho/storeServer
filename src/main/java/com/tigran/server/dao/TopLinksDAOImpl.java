package com.tigran.server.dao;

import com.tigran.server.dao.Interfaces.TopLinksDAO;
import com.tigran.server.models.TopLinks;
import com.tigran.server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.util.List;

@Service
public class TopLinksDAOImpl implements TopLinksDAO {
    @Override
    public void create(TopLinks topLinks){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(topLinks);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error createTopLinks ", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public TopLinks read(String id){
        TopLinks topLinks = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            topLinks = (TopLinks) session.load(TopLinks.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readTopLinks", JOptionPane.OK_OPTION);
        }
        return topLinks;
    }

    @Override
    public List<TopLinks> readAll(){
        List<TopLinks> topLinks = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TopLinks> criteria = builder.createQuery(TopLinks.class);
            criteria.from(TopLinks.class);
            topLinks = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllTopLinks", JOptionPane.OK_OPTION);
        }
        return topLinks;
    }

    @Override
    public void update(String id, TopLinks topLinks){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(topLinks);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllTopLinks", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void delete(TopLinks topLinks){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(topLinks);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllTopLinks", JOptionPane.OK_OPTION);
        }
    }
}
