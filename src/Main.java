// Homework seminar #5
// Create an application to imitate the work of the library.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        ArrayList<Book> takenBooks = new ArrayList<>();
        while (true) {
            System.out.print("Put your command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "/get" -> {
                    System.out.print("Put title of the book: ");
                    String title = scanner.nextLine();
                    if (title != null) {
                        int position = 1;
                        if (!library.hasBookUniqueTitle(title)) {
                            System.out.print("Put position of the book: ");
                            position = scanner.hasNextInt() ? scanner.nextInt() : 0;
                            if (position < 1) {
                                System.out.println("Wrong position");
                                break;
                            }
                        }
                        Book book = library.takeBook(title, position);
                        if (book == null) {
                            System.out.println("Book is not found");
                        }
                        else {
                            takenBooks.add(book);
                            System.out.println("You took the book: " + book.getDescription());
                        }
                    }
                    else {
                        System.out.println("Bad title");
                    }
                }
                case "/put" -> {
                    System.out.print("Put the number of taken book (check taken books list): ");
                    int position = scanner.hasNextInt() ? scanner.nextInt() : 0;
                    if (position < 1 || position > takenBooks.size()) {
                        System.out.println("Wrong position");
                        break;
                    }
                    Book book = takenBooks.remove(position - 1);
                    library.returnBook(book);
                }
                case "/list" -> library.printTakenBooks(takenBooks);
                case "/all" -> library.printAllBooks();
                case "/help" -> System.out.println(Constants.HELP);
                case "/exit" -> {
                    System.out.println(Constants.FINISH);
                    return;
                }
                default -> System.out.println(Constants.UNKNOWN_COMMAND);
            }
        }
    }
}