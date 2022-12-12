import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Library {
    private static class GenerationContent {
        private static final int MAX_BOOKS = 100;

        private static final int MAX_AUTHORS = 5;

        private static final int MAX_WORDS_COUNT = 7;

        private static final String[] WORDS = {
                "The", "Quick", "Brown", "Fox", "Jumps", "Over", "The", "Lazy", "Cat",
                "What", "A", "Wonderful", "World", "I", "Love", "Java", "Programming",
                "I", "Am", "Student", "Of", "University", "In", "Russia",
                "Sciences", "Warsaw", "Learning", "Java", "Programming",
                "Where", "Is", "The", "Library", "Need", "A", "Book", "To", "Read",
                "How", "To", "Write", "A", "Book", "In", "Cruel", "Human", "Space",
                "Kiss", "Me", "Hard", "Before", "You", "Go", "Love",
                "StudentA", "StudentB", "StudentC", "StudentD", "StudentE"
        };

        private static final String[] AUTHORS_NAMES = {
                "AuthorA", "AuthorB", "AuthorC", "AuthorD", "AuthorE",
                "John", "Smith", "James", "Brown", "Robert", "Johnson",
                "William", "Williams", "David", "Jones", "Richard", "Davis",
                "Charles", "Miller", "Joseph", "Wilson", "Thomas", "Moore",
                "Christopher", "Taylor", "Daniel", "Anderson", "Paul", "Thomas",
                "Mark", "Jackson", "Donald", "White", "George", "Harris",
                "Kenneth", "Martin", "Steven", "Thompson", "Edward", "Garcia"
        };

        private static final String[] AUTHORS_SURNAMES = {
                "Tolkien", "Rowling", "King", "Martin", "Gaiman",
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis",
                "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas",
                "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
                "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
                "Walker", "Hall", "Allen", "Young", "Hernandez", "King"
        };

        private static int getRandomNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }

        private static String getRandomTitle() {
            int wordsCount = getRandomNumber(1, MAX_AUTHORS);
            StringBuilder title = new StringBuilder();
            for (int i = 0; i < wordsCount; i++) {
                if (i > 0) {
                    title.append(" ");
                }
                title.append(WORDS[getRandomNumber(0, WORDS.length)]);
            }
            return title.toString();
        }

        private static String[] getRandomAuthors() {
            int authorsCount = getRandomNumber(1, MAX_WORDS_COUNT);
            String[] authors = new String[authorsCount];
            for (int i = 0; i < authorsCount; i++) {
                authors[i] = AUTHORS_NAMES[getRandomNumber(0, AUTHORS_NAMES.length - 1)] + " " +
                        AUTHORS_SURNAMES[getRandomNumber(0, AUTHORS_SURNAMES.length - 1)];
            }
            Arrays.sort(authors);
            return authors;
        }

        private GenerationContent() {}
    }

    private final ArrayList<ArrayList<Book>> books;

    public Library() {
        books = new ArrayList<>();
        createLibrary();
        books.sort(Comparator.comparing(o -> o.get(0).getTitle()));
    }

    private int isTitleExist(String title) {
        for (int i = 0; i < books.size(); i++) {
            for (Book book : books.get(i)) {
                if (book.getTitle().equals(title)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void createLibrary() {
        for (int i = 0; i < GenerationContent.MAX_BOOKS; i++) {
            String title = GenerationContent.getRandomTitle();
            String[] authors = GenerationContent.getRandomAuthors();
            int year = GenerationContent.getRandomNumber(1800, 2022);
            StringBuilder description = new StringBuilder();
            description.append("Book \"").append(title).append("\" which was written by ");
            for (int j = 0; j < authors.length; j++) {
                description.append(authors[j]);
                if (j < authors.length - 1) {
                    description.append(", ");
                }
            }
            description.append(" and published in ").append(year).append(".");
            Book book = new Book(title, authors, description.toString(), year);
            int index = isTitleExist(title);
            if (index > -1) {
                books.get(index).add(book);
            } else {
                ArrayList<Book> booksList = new ArrayList<>();
                booksList.add(book);
                booksList.sort(Comparator.comparing(Book::getPublicationYear));
                books.add(booksList);
            }
        }
    }

    public boolean hasBookUniqueTitle(String title) {
        int index = isTitleExist(title != null ? title : "");
        if (index > -1) {
            return books.get(index).size() == 1;
        }
        return false;
    }

    public Book takeBook(String title, int position) {
        int index = isTitleExist(title != null ? title : "");
        if (index > -1) {
            if (position > books.get(index).size()) {
                return null;
            }
            if (books.get(index).get(position - 1).isAbsent()) {
                return null;
            }
            books.get(index).get(position - 1).setAbsent(true);
            return books.get(index).get(position - 1);
        }
        return null;
    }

    public void returnBook(Book book) {
        if (book != null) {
            for (ArrayList<Book> booksList : books) {
                for (Book bookInList : booksList) {
                    if (bookInList.equals(book)) {
                        bookInList.setAbsent(false);
                    }
                }
            }
        }
    }

    public void printTakenBooks(ArrayList<Book> takenBooks) {
        int cnt = 0;
        for (Book book : takenBooks) {
            System.out.println(++cnt + ") " + book);
        }
    }

    public void printAllBooks() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println("List #" + (i + 1));
            for (int j = 0; j < books.get(i).size(); j++) {
                System.out.println((j + 1) + ") " + books.get(i).get(j).toString());
            }
            System.out.println();
        }
    }
}
