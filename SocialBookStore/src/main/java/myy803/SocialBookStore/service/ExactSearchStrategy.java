package myy803.SocialBookStore.service;

import java.util.List;

import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.formsData.SearchFormData;

public class ExactSearchStrategy extends TemplateSearchStrategy {

	@Override
	protected List<Book> makeinitialListOfBooks(SearchFormData searchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

}
