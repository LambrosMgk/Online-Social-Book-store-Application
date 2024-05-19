package myy803.SocialBookStore.formsData;

import java.util.List;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;

public class RecommendationsFormData {
	private List<String> favoriteCategories;
	private List<String> favoriteAuthors;
	private List<BookFormData> recommendedBooks;
	
	
	public RecommendationsFormData() {}
	
	public RecommendationsFormData(List<String> favoriteCategories) {
		this.favoriteCategories = favoriteCategories;
	}

	public List<String> getFavoriteCategories() {return favoriteCategories;}
	public void setFavoriteCategories(List<String> favoriteCategories) {this.favoriteCategories = favoriteCategories;}

	public List<String> getFavoriteAuthors() {return favoriteAuthors;}
	public void setFavoriteAuthors(List<String> favoriteAuthors) {this.favoriteAuthors = favoriteAuthors;}

	public List<BookFormData> getRecommendedBooks() {return recommendedBooks;}
	public void setRecommendedBooks(List<BookFormData> recommendedBooks) {this.recommendedBooks = recommendedBooks;}
}
