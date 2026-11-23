
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String name;
    private String author;
    private int year;
    private boolean available;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    public void displayInfo() {
        System.out.println("--------------------------------");
        System.out.println("Kitob nomi: " + name);
        System.out.println("Muallif: " + author);
        System.out.println("Nashr yili: " + year);
        System.out.println("Holati: " +
                (available ? "Mavjud" : "Kutubxonadan olingan"));
        System.out.println("--------------------------------");
    }
}

public class LibraryManagement {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("     KUTUBXONA BOSHQARUV TIZIMI");
            System.out.println("================================");
            System.out.println("1. Kitob qo'shish");
            System.out.println("2. Barcha kitoblarni ko'rish");
            System.out.println("3. Kitob qidirish");
            System.out.println("4. Kitobni kutubxonadan olish");
            System.out.println("5. Kitobni kutubxonaga qaytarish");
            System.out.println("6. Dasturdan chiqish");
            System.out.println("================================");

            System.out.print("Tanlovingizni kiriting: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;

                case 2:
                    showBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    borrowBook();
                    break;

                case 5:
                    returnBook();
                    break;

                case 6:
                    System.out.println(
                            "Dasturdan foydalanilganingiz uchun rahmat!"
                    );
                    break;

                default:
                    System.out.println("Noto'g'ri tanlov!");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Kitob qo'shish
    public static void addBook() {

        System.out.println("\n--- YANGI KITOB QO'SHISH ---");

        System.out.print("Kitob nomini kiriting: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Kitob nomi bo'sh bo'lishi mumkin emas!");
            return;
        }

        System.out.print("Muallif ismini kiriting: ");
        String author = scanner.nextLine().trim();

        if (author.isEmpty()) {
            System.out.println("Muallif ismi bo'sh bo'lishi mumkin emas!");
            return;
        }

        System.out.print("Nashr yilini kiriting: ");
        int year = readInt();

        if (year < 1 || year > 2026) {
            System.out.println("Nashr yili noto'g'ri!");
            return;
        }

        Book book = new Book(name, author, year);
        books.add(book);

        System.out.println("Kitob muvaffaqiyatli qo'shildi!");
    }

    // Barcha kitoblarni ko'rish
    public static void showBooks() {

        System.out.println("\n--- KUTUBXONADAGI KITOBLAR ---");

        if (books.isEmpty()) {
            System.out.println("Kutubxonada hozircha kitob yo'q.");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nKitob ID: " + (i + 1));
            books.get(i).displayInfo();
        }
    }

    // Kitob qidirish
    public static void searchBook() {

        System.out.println("\n--- KITOB QIDIRISH ---");

        System.out.print("Kitob nomi yoki muallifini kiriting: ");
        String keyword = scanner.nextLine().trim();

        boolean found = false;

        for (Book book : books) {

            if (book.getName().toLowerCase().contains(
                    keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(
                            keyword.toLowerCase())) {

                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Kitob topilmadi!");
        }
    }

    // Kitobni olish
    public static void borrowBook() {

        showBooks();

        if (books.isEmpty()) return;

        System.out.print("Olinadigan kitob ID sini kiriting: ");
        int id = readInt();

        if (id < 1 || id > books.size()) {
            System.out.println("Bunday ID mavjud emas!");
            return;
        }

        Book book = books.get(id - 1);

        if (!book.isAvailable()) {
            System.out.println("Bu kitob allaqachon olingan!");
        } else {
            book.borrowBook();
            System.out.println("Kitob muvaffaqiyatli olindi!");
        }
    }

    // Kitobni qaytarish
    public static void returnBook() {

        showBooks();

        if (books.isEmpty()) return;

        System.out.print("Qaytariladigan kitob ID sini kiriting: ");
        int id = readInt();

        if (id < 1 || id > books.size()) {
            System.out.println("Bunday ID mavjud emas!");
            return;
        }

        Book book = books.get(id - 1);

        if (book.isAvailable()) {
            System.out.println("Bu kitob allaqachon kutubxonada!");
        } else {
            book.returnBook();
            System.out.println("Kitob muvaffaqiyatli qaytarildi!");
        }
    }

    // Xavfsiz son kiritish
    public static int readInt() {

        while (!scanner.hasNextInt()) {
            System.out.print("Iltimos, butun son kiriting: ");
            scanner.nextLine();
        }

        int number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }
}
