
package com.dev.utils;

import com.dev.objects.Offer;
import com.dev.objects.Product;
import com.dev.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist (SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public User getUserByUsername (String username) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username= :username")
                .setParameter("username",username)
                .uniqueResult();
        session.close();
        return found;
    }

    public void saveUser (User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public User getUserByUsernameAndToken (String username , String token) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username " +
                "AND token = :token")
                .setParameter("username", username)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public List<User> getAllUsers () {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        return allUsers;
    }

    public List<Offer> getAllOffers () {
        Session session = sessionFactory.openSession();
        List<Offer> allOffers = session.createQuery("FROM Offer ").list();
        session.close();
        return allOffers;
    }

    public List<Product> getAllProducts () {
        Session session = sessionFactory.openSession();
        List<Product> allProducts = session.createQuery("FROM Product ").list();
        session.close();
        return allProducts;
    }

    public List<Offer> getAllClosedProducts () {
        Session session = sessionFactory.openSession();
        List<Offer> allClosedProductsOffers = session.createQuery("FROM Offer WHERE product.isOpen = false group by product" +
                " order by MAX (offerAmount) DESC ").list();
        session.close();
        return allClosedProductsOffers;
    }

    public List<Product> getAllOpenProducts () {
        Session session = sessionFactory.openSession();
        List<Product> allOpenProducts = session.createQuery("FROM Product WHERE isOpen = true ").list();
        session.close();
        return allOpenProducts;
    }

    public User getUserByToken (String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User WHERE token = :token")
                .setParameter("token",token)
                    .uniqueResult();
        session.close();
        return user;
    }

    public User getUserById (int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User WHERE id = :id")
                .setParameter("id",id)
                .uniqueResult();
        session.close();
        return user;
    }

    public void saveProduct (Product product) {
        Session session = sessionFactory.openSession();
        session.save(product);
        session.close();
    }





}
