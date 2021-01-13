package Exercises.ex7.software1.predicate;

public class ByAuthor implements Predicate<Book> {

    private char letter;

    public ByAuthor(char letter) { // Q2
        this.letter = letter;

    }

    @Override
    public boolean test(Book book) { // Q2
        char bookAuthorLetter = Character.toLowerCase(book.getAuthor().charAt(0));
        return bookAuthorLetter == letter;
    }
}