package vn.edu.stu.book_library.model;

public class Book {

        public String bookId;
        public String name;
        public String type;
        public String author;
        public String amount;
        public byte[] image;

    public Book(String bookId, String name, String type, String author, String amount, byte[] image) {
        this.bookId = bookId;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.author = author;
        this.image = image;
    }
}



