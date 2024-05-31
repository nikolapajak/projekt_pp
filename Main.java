import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class Main extends JFrame {
    private BookManager manager;
    private JTextField title, author, year, genre, search;
    private JTextArea displayArea;

    public Main() {
        manager = new BookManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Zarządzania książkami projekt");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Panel do wprowadzania danych
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        title = new JTextField();
        author = new JTextField();
        year = new JTextField();
        genre = new JTextField();
        search = new JTextField();

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(title);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(author);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(year);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genre);
        inputPanel.add(new JLabel("Irregular search:"));
        inputPanel.add(search);

        // Przyciski do zarządzania książkami
        JButton addButton = new JButton("Add book");
        addButton.addActionListener(new AddBookAction());
        JButton removeButton = new JButton("Remove book");
        removeButton.addActionListener(new RemoveBookAction());
        JButton searchButton = new JButton("Irregular search");
        searchButton.addActionListener(new SearchBookAction());
        JButton showAllButton = new JButton("Show books");
        showAllButton.addActionListener(e -> showAllBooks());
        JButton sortTitleButton = new JButton("Sort by title");
        sortTitleButton.addActionListener(e -> sortBooks(Comparator.comparing(Book::getTitle)));
        JButton sortAuthorButton = new JButton("Sort by author");
        sortAuthorButton.addActionListener(e -> sortBooks(Comparator.comparing(Book::getAuthor)));
        JButton sortYearButton = new JButton("Sort by year");
        sortYearButton.addActionListener(e -> sortBooks(Comparator.comparingInt(Book::getYear)));
        JButton sortGenreButton = new JButton("Sort by genre");
        sortGenreButton.addActionListener(e -> sortBooks(Comparator.comparing(Book::getGenre)));

        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(searchButton);
        inputPanel.add(showAllButton);
        inputPanel.add(sortTitleButton);
        inputPanel.add(sortAuthorButton);
        inputPanel.add(sortYearButton);
        inputPanel.add(sortGenreButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void showAllBooks() {
        displayArea.setText("");
        for (Book book : manager.getBooks()) {
            displayArea.append(book.toString() + "\n");
        }
    }

    private class AddBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String titleText = title.getText();
                String authorText = author.getText();
                int yearValue = Integer.parseInt(year.getText());
                String genreText = genre.getText();
                manager.addBook(new Book(titleText, authorText, yearValue, genreText));
                displayArea.append("Added: " + titleText + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("Incorrect year\n");
            }
        }
    }

    private class RemoveBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String titleText = title.getText();
            if (manager.removeBook(titleText)) {
                displayArea.append("Usunięto: " + titleText + "\n");
            } else {
                displayArea.append("No book with title found: " + titleText + "\n");
            }
        }
    }

    private class SearchBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String regex = search.getText();
            displayArea.setText("");
            for (Book book : manager.searchBooks(regex)) {
                displayArea.append(book.toString() + "\n");
            }
        }
    }

    private void sortBooks(Comparator<Book> comparator) {
        manager.sortBooks(comparator);
        showAllBooks();
    }

    public static void main(String[] args) {
        new Main();
    }
}