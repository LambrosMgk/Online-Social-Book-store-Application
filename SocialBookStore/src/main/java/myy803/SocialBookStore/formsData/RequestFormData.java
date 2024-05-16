package myy803.SocialBookStore.formsData;

public class RequestFormData {
	
	private int book_id;
	private int offersBook_user_id;
	private int wantsBook_user_id;
	private String offerDescription;
	
	
	public RequestFormData() {}
	
	public RequestFormData(int book_id)
	{
		this.book_id = book_id;
	}
}
