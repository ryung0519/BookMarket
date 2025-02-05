package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;

@Repository
public class BookRepositoryImpl implements BookRepository {
	private List<Book> listOfBooks = new ArrayList<Book>();

	public BookRepositoryImpl() {
		Book book1 = new Book("ISBN1234", "C# ������", 30000);
		book1.setAuthor("�ڿ���");
		book1.setDescription(
				"C# �������� ���� ù ���α׷��� ���� C#�� �����ϴ� ���ڸ� ������� �Ѵ�. Ư�� ���� ���α׷��Ӹ� ���� C# �Թ�����, C#�� ����Ͽ� ����(����Ƽ), ��, �����, IoT ���� ������ �� �ʿ��� C# ���� ������ ������ �⺻�⸦ źź�ϰ� ������ ���� �����̴�.");
		book1.setPublisher("���");
		book1.setCategory("IT������");
		book1.setUnitPrice(30000);
		book1.setReleaseDate("2020/05/29");

		Book book2 = new Book("ISBN1235", "Node.js ������", 36000);
		book2.setAuthor("������");
		book2.setDescription(
				"�� å�� ����Ʈ���� ����, �����ͺ��̽�, �������� �ƿ츣�� �������� ������ �ٷ��. �������� ���� �������� �������� �⺻ ������ Ȯ���� �����ϰ�, ����� ��ɰ� ���°踦 ����� ���鼭 ������ �����ϴ� ������ ������. ������ �ڵ�� �ֽ� ������ ����߰� �ǹ��� �����ϰų� ���� ������ �� �ִ�.");
		book2.setPublisher("���");
		book2.setCategory("IT������");
		book2.setUnitPrice(36000);
		book2.setReleaseDate("2020/07/25");

		Book book3 = new Book("ISBN1236", "��� XD CC 2020", 25000);
		book3.setAuthor("�����");
		book3.setDescription(
				"��� XD ���α׷��� ���� UI/UX �������� ������ �ϴ� ���� �����̳��� �����̿� �°� �⺻���� ������ Ȱ���� ������ �����ΰ� ��&�� ������ ������, UI ������, �� �����ο� �ִϸ��̼ǰ� ���ͷ����� ������ ������Ÿ������ �н��մϴ�.");
		book3.setPublisher("���");
		book3.setCategory("ITȰ�뼭");
		book3.setUnitPrice(25000);
		book3.setReleaseDate("2019/05/29");

		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
	}

	@Override
	public List<Book> getAllBooks() {
		return listOfBooks;
	}

	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = new ArrayList<Book>();
		for (int i = 0; i < listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if (category.equalsIgnoreCase(book.getCategory()))
				booksByCategory.add(book);
		}
		return booksByCategory;
	}

	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();

		Set<String> booksByFilter = filter.keySet();

		if (booksByFilter.contains("publisher")) {
			for (int j = 0; j < filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);

				for (int i = 0; i < listOfBooks.size(); i++) {
					Book book = listOfBooks.get(i);
					if (publisherName.equalsIgnoreCase(book.getPublisher()))
						booksByPublisher.add(book);
				}
			}
		}

		if (booksByFilter.contains("category")) {
			for (int i = 0; i < filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				List<Book> list = getBookListByCategory(category);
				booksByCategory.addAll(list);
			}
		}
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	public Book getBookById(String bookId) {
		Book bookInfo = null;
		for (int i = 0; i < listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
				bookInfo = book;
				break;
			}
		}
		if (bookInfo == null)
			throw new BookIdException(bookId);
		return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
    	listOfBooks.add(book);
    }
}
