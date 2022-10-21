package com.tigran.server.dao;
import com.tigran.server.dao.Interfaces.AdvertisementDAO;
import com.tigran.server.models.Advertisement;
import com.tigran.server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;
import java.util.List;

@Service
public class AdvertisementDAOImpl implements AdvertisementDAO {
    @Override
    public void create(Advertisement advertisement){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(advertisement);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error createAdvertisement ", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public List<Advertisement> read(){
        List<Advertisement> advertisement = null;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Advertisement> criteria = builder.createQuery(Advertisement.class);
            criteria.from(Advertisement.class);
            advertisement = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAdvertisement", JOptionPane.OK_OPTION);
        }
        return advertisement;
    }
    @Override
    public void update(String id, Advertisement advertisement){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(advertisement);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllAdvertisement", JOptionPane.OK_OPTION);
        }
    }
    @Override
    public void delete(Advertisement advertisement){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(advertisement);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error readAllAdvertisement", JOptionPane.OK_OPTION);
        }
    }
}
