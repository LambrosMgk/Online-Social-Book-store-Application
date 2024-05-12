package myy803.BookStore.FormsData;


import myy803.BookStore.entity.User;
import myy803.BookStore.entity.UserProfile;

public class UserProfileFormData {
	
	private User user;
	private UserProfile userProfile;
	
	private int user_id;
	private String username;
	private String fullname;
	private String address;
	private int age;
	private int phonenum;
	
	public UserProfileFormData() 
	{
		if (this.user.getUserid() != this.userProfile.getId_user()) 
		{
			System.out.println("WARNING, the user class that you initialize is not the same with the user profile");
		}
		
		this.user_id = this.user.getUserid();
		this.username = this.user.getUsername();
		this.fullname = this.userProfile.getFullname();
		this.address = this.userProfile.getAddress();
		this.age = this.userProfile.getAge();
		this.phonenum = this.userProfile.getPhonenumber();
		
	}
	
	
	public int getUser_id() {return user_id;}

	public String getUsername() {return username;}

	public String getFullname() {return fullname;}

	public String getAddress() {return address;}

	public int getAge() {return age;}

	public int getPhonenum() {return phonenum;}
	
	
	@Override
	public String toString() {
		return "UserProfileFormData [user_id=" + this.user_id + ", username=" + this.username + ", fullname=" + this.fullname
				+ ", address=" + this.address + ", age=" + this.age + ", phonenum=" + this.phonenum + "]";
	}
}
	
	
	
	