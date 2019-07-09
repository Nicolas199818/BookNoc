package com.example.booknoc;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static List<Book> map(List<BookDTO> weaponDTOList) {
        List<Book> bookList = new ArrayList<>();
        for (BookDTO bookDTO : weaponDTOList) {
            bookList.add(map(bookDTO));
        }
        return bookList;
    }

    private static Book map(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle());
        return book;
    }
}
