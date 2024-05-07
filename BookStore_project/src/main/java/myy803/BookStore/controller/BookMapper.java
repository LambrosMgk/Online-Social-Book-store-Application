package myy803.BookStore.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myy803.BookStore.entity.Book;

@Repository
public interface BookMapper extends JpaRepository<Book , Integer> {

}
