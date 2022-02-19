package HomeWorkSeven;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();


    public void runApplication () throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Здравствуйте. Выберите, пожалуйста, операцию: 1 - текущая погода, 2 - погода на пять дней" +
                    ", 3 - завершить работу");

            String result = scanner.nextLine();


            try {
                validateUserInput(result);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            checkIsExit(result);

            if (result.equals("1") || result.equals("2")) {
                System.out.println("Введите название города на английском языке");
                String city = scanner.nextLine();
                setGlobalCity(city);
            }

            try {
                notifyController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkIsExit(String result) throws IOException {
        if (result.equals("3")) {
            controller.getExit();
        }

    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    private void validateUserInput (String userInput) throws IOException {
        if (userInput == null || userInput.length() !=1) {
            throw new IOException("Некорректный выбор операции, количество выбранных опреаций должно быть равно 1: " + userInput);
        }
        int answer;
        try {
            answer = Integer.parseInt(userInput);
            if (answer >= 4) {
                throw new IOException("Некорретный выбор операции:  введенное число должно быть меньше 4.");
            }
        } catch (NumberFormatException e) {
            throw new IOException("Некорректный выбор типа опреации: введен не номер");
        }

    }

    private void notifyController (String input) throws IOException {
        controller.onUserInput(input);
    }
}
