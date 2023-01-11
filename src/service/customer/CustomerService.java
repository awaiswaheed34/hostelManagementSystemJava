package service.customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.customer.Customer;

public class CustomerService 
{
    private static List<Customer> customers = new ArrayList<Customer>();
    private static CustomerService instance = null;
    private CustomerService() {}
    public static CustomerService getInstance() 
    {
        if (instance == null) 
        {
            instance = new CustomerService();
        }
        return instance;
    }
    public void addcustomer(String email, String firstName, String lastName) 
    {
        Customer customer = new Customer(firstName, lastName, email);
        boolean flag = true;
        for (Customer c : customers) 
        {
            if (c.getEmail().equals(email)) 
            {
                System.out.println("Email already exist!");
                flag = false;
                break;
            }
        }
        if (flag) 
        {
            customers.add(customer);
        }
    }
    public Customer getCustomer(String customerEmail) 
    {
        for (Customer c : customers) 
        {
            if (c.getEmail().equals(customerEmail)) 
            {
                return c;
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers() 
    {
        return customers;
    }
}