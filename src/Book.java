public class Book {
    private final String title;
    private final String[] authors;
    private final String description;
    private final int publicationYear;
    private boolean isAbsent;

    public Book(String title, String[] authors, String description, int year) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.publicationYear = year;
        this.isAbsent = false;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAbsent() {
        return isAbsent;
    }

    public void setAbsent(boolean absent) {
        isAbsent = absent;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            info.append(authors[i]);
            if (i < authors.length - 1) {
                info.append(", ");
            }
        }
        info.append(':');
        info.append(" \"").append(title).append("\" ");
        info.append('(').append(publicationYear).append(')');
        return info.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Book book = (Book) object;
        if (getPublicationYear() != book.getPublicationYear()) {
            return false;
        }
        if (getAuthors().length != book.getAuthors().length) {
            return false;
        }
        else {
            for (int i = 0; i < getAuthors().length; i++) {
                if (!getAuthors()[i].equals(book.getAuthors()[i])) {
                    return false;
                }
            }
        }
        return getTitle() != null ? getTitle().equals(book.getTitle()) : book.getTitle() == null;
    }
}
