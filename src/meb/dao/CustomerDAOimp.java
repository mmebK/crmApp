package meb.dao;

import com.meb.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOimp implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        //get the current hibernate session

        Session session = sessionFactory.getCurrentSession();

        //create a query

        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
        //execute the query and get result list

        List<Customer> customers = query.getResultList();


        //return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //nw retieve/read from database using the primary key
        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Customer where id=:theId");
        query.setParameter("theId",id);

        //we should always add this line of code to execute the query
        query.executeUpdate();
    }
}
