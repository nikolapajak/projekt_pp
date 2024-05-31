import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        initializeBookDatabase();
    }

    // Dodawanie ksiązki
    public void addBook(Book newBook) {
        if (!books.contains(newBook)) {
            books.add(newBook);
        } else {
            System.out.println("Such a book already exists");
        }
    }

    // Usuwanie ksiązki
    public boolean removeBook(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    // Wyszukiwanie nieregularne
    public List<Book> searchBooks(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (pattern.matcher(book.getTitle()).find() || pattern.matcher(book.getAuthor()).find()) {
                results.add(book);
            }
        }
        return results;
    }

    // Sortowanie po tytule
    public List<Book> getBooksByGenre(String genre) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    // Sortowanie po roku
    public List<Book> searchBooksByYear(int year) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    // Sortowanie według kompaktora
    public void sortBooks(Comparator<Book> comparator) {
        Collections.sort(books, comparator);
    }

    // Metoda zwracająca liste ksiązek
    public List<Book> getBooks() {
        return books;
    }

    // Wszystkie ksiązki
    public void printBooks() {
        books.forEach(System.out::println);
    }

    // Lista 30 ksiązek
    private void initializeBookDatabase() {
        books.add(new Book("Physics of the Future", "Michio Kaku", 2011, "Science"));
        books.add(new Book("The Future of Humanity", "Michio Kaku", 2018, "Science"));
        books.add(new Book("Parallel Worlds", "Michio Kaku", 2004, "Science"));
        books.add(new Book("Hyperspace", "Michio Kaku", 1994, "Science"));
        books.add(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999, "Technology"));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008, "Technology"));
        books.add(new Book("Introduction to the Theory of Computation", "Michael Sipser", 1996, "Technology"));
        books.add(new Book("Artificial Intelligence: A Modern Approach", "Stuart Russell", 2015, "Technology"));
        books.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", 2011, "Psychology"));
        books.add(new Book("Influence: The Psychology of Persuasion", "Robert B. Cialdini", 1984, "Psychology"));
        books.add(new Book("The Lean Startup", "Eric Ries", 2011, "Business"));
        books.add(new Book("Zero to One", "Peter Thiel", 2014, "Business"));
        books.add(new Book("The Hard Thing About Hard Things", "Ben Horowitz", 2014, "Business"));
        books.add(new Book("Atomic Habits", "James Clear", 2018, "Self-help"));
        books.add(new Book("The Power of Habit", "Charles Duhigg", 2012, "Self-help"));
        books.add(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, "History"));
        books.add(new Book("Homo Deus: A Brief History of Tomorrow", "Yuval Noah Harari", 2015, "History"));
        books.add(new Book("Gone Girl", "Gillian Flynn", 2012, "Thriller"));
        books.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", 2005, "Thriller"));
        books.add(new Book("The Da Vinci Code", "Dan Brown", 2003, "Thriller"));
        books.add(new Book("The Silent Patient", "Alex Michaelides", 2019, "Thriller"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "Classic"));
        books.add(new Book("1984", "George Orwell", 1949, "Classic"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813, "Classic"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "Classic"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "Classic"));
        books.add(new Book("Moby-Dick", "Herman Melville", 1851, "Classic"));
        books.add(new Book("The Selfish Gene", "Richard Dawkins", 1976, "Science"));
        books.add(new Book("A Brief History of Time", "Stephen Hawking", 1988, "Science"));
        books.add(new Book("The Gene: An Intimate History", "Siddhartha Mukherjee", 2016, "Science"));
    }
}
