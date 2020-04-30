import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    boolean programRunning = true;
    String readMessage = "";

    Scanner scanner = new Scanner(System.in);


    public Main() {
        while(programRunning) {
            System.out.println("Please write your message: ");
            readMessage = scanner.nextLine();
            handleMessage(readMessage);
        }
    }

    private void handleMessage(String message) {
        switch (message) {
            case "exit":
                programRunning = false;
                System.out.println("System will shutdown.");
                break;
            case "continue":
                System.out.println("Program continues.");
                break;
            default:
                System.out.println("That input was not valid. Please try again.");
        }
    }
}
