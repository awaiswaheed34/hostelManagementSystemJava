package model.room;
public class FreeRoom extends Room
{
	public FreeRoom(String roomNumber, RoomType enumeration) 
	{
		super(roomNumber, 0.0, enumeration);
	}
	public String toString() 
	{
		return super.toString();
	}
}