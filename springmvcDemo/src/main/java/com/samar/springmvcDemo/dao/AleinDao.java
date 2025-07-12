package com.samar.springmvcDemo.dao;

import com.samar.springmvcDemo.model.Alein;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AleinDao {
//    @Autowired
////    private SessionFactory sessionFactory;
//    @Transactional
//    public List<Alein> getAleins(){
////        Session session=sessionFactory.getCurrentSession();
////        return session.createQuery("Select * From alein",Alein.class).list();
//        return new ArrayList<>();
//    }
//    @Transactional
//    public void addAlein(Alein a){
////        Session session=sessionFactory.getCurrentSession();
////        session.persist(a);
//    }
//    @Transactional
//    public Alein getAlein(int id){
////        Session session=sessionFactory.getCurrentSession();
////        Alein alein=session.get(Alein.class,id);
////        return  alein;
//        return null;
//    }
}
