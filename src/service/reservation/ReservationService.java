//ReservationService.java
package service.reservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
public class ReservationService 
{
    public static List<IRoom> rooms = new ArrayList<IRoom>();
    static List<Reservation> reservations = new ArrayList<Reservation>();
    private static ReservationService instance = null;
    private ReservationService(){}
    public static ReservationService getInstance() 
    {
        if (instance == null) 
        {
            instance = new ReservationService();
        }
        return instance;
    }
    boolean isRoomExist(String roomId) 
    {
        for (int i = 0; i < rooms.size(); i++) 
        {
            if (rooms.get(i).getRoomNumber().equalsIgnoreCase(roomId)) 
            {
                return true;
            }
        }
        return false;
    }
    public boolean addRoom(IRoom room) 
    {
        if (isRoomExist(room.getRoomNumber())) 
        {
            return true; 
        }
        rooms.add(room);
        return false;
    }
    public IRoom getARoom(String roomId) 
    {
        for (IRoom room : rooms) 
        {
            if (room.getRoomNumber().equals(roomId)) 
            {
                return room;
            }
        }
        return null;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) 
    {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) 
    {
        ArrayList<IRoom> available = new ArrayList<IRoom>();
        ArrayList<IRoom> booked = new ArrayList<IRoom>();    
        for (Reservation reservation : reservations) 
        {
            if((checkInDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate())) || (checkOutDate.after(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())))
            {
                booked.add(reservation.getRoom());
            }
        }
        for (IRoom room : rooms) 
        {
            if(!booked.contains(room))
                available.add(room);
        }
        return available;
    }
    public Collection<Reservation> getCustomerReservation(Customer customer) 
    {
        ArrayList<Reservation> reservation = new ArrayList<Reservation>();
        for (Reservation reserve : reservations) 
        {
            if (reserve.getCustomer().equals(customer)) 
            {
                reservation.add(reserve);
            }
        }
        return reservation;
    }
    public void printAllReservation() 
    {
        for (Reservation reservation : reservations) 
        {
            System.out.println(reservation);
        }
    }
}





