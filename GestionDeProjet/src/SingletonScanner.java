import java.util.Scanner;

public final class SingletonScanner {
    private static SingletonScanner instance = null;
    private Scanner scanner;

    private SingletonScanner() {
    }

    private SingletonScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public static SingletonScanner getInstance() {
        if (instance == null) {
            instance = new SingletonScanner(new Scanner(System.in));
        }

        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
