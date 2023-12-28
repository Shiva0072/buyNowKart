package com.buyNowKart.dao;

import com.buyNowKart.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao{
    Session session;
    public CategoryDao(){
        try{
            session = sessionFactory.openSession();
        }catch (Exception e){
            System.out.println("error in opening session of CategoryDao : "+e.getMessage());
            e.printStackTrace();
        }
    }
    public boolean AddCategory(Category category){
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
        return true;
    }

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
//        try(Session session = sessionFactory.openSession()){
//            categories.addAll(session.createQuery("SELECT a FROM Category a", Category.class).getResultList());
//        }
        try{
            categories.addAll(session.createQuery("SELECT a FROM Category a", Category.class).getResultList());
        }
        catch(Exception e){
            System.out.println("error in opening session : "+e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }

}
