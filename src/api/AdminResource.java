// AdminResource.java
package api;
import service.customer.CustomerService;
import service.reservation.ReservationService;
import model.customer.Customer;
import model.room.IRoom;
import java.util.Collection;
import java.util.List;
public class AdminResource 
{
	public static AdminResource adminResource;
	public static CustomerService customerService;
	public static ReservationService reservationService;	
	public AdminResource() 
	{
		customerService = CustomerService.getInstance();
		reservationService = ReservationService.getInstance();
	}
	public CustomerService getCustomerService() 
	{
		return customerService;
	}
	public ReservationService getReservationService() 
	{
		return reservationService;
	}
	public Customer getCustomer(String email) 
	{
		return customerService.getCustomer(email);
	}
	public boolean addRoom(List<IRoom> rooms) 
	{
		for(IRoom room:rooms) {
			return reservationService.addRoom(room);
		}
		return false;
	}

	public Collection<IRoom> getAllRooms() 
	{
		return ReservationService.rooms;
	}
	public Collection<Customer> getAllCustomers() 
	{
		return customerService.getAllCustomers();
	}
	public void desiplayAllReservations() 
	{
		reservationService.printAllReservation();
	}
    public static AdminResource getSingleton() {
		if(adminResource==null)
			adminResource = new AdminResource();
        return adminResource;
    }
}
