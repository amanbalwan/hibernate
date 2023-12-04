package com.server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

    public static void main(String[] args) {
      
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

    
        Session session = sessionFactory.getCurrentSession();

        try {
       
            session.beginTransaction();

   
            Category category = new Category("Applications");
            Product product1 = new Product("Fb", 20);
            Product product2 = new Product("insta", 10);
            Product product3 = new Product("whatsapp", 15);

            category.addProduct(product1);
            category.addProduct(product2);
            category.addProduct(product3);

           
            System.out.println("Total Products: " + category.getNumberOfProducts());
            category.removeProduct(product1);
            System.out.println("Total Products after remove: " + category.getNumberOfProducts());
            product2.clearCategory();
            System.out.println("After cleaning: " + category.getNumberOfProducts());

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
