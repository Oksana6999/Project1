package WeatherApp;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();


    public void runApplication () {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название города");
            String city = scanner.nextLine();

            setGlobalcity(city);

            System.out.println("Выберите операцию: 1 - текущая погода, 2 - погода на пять дней" +
                    "выход (exit) - завершить работу");

            String result = scanner.nextLine();

            checkIsExit(result);

            try {
                validateUserInput(result);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            try {
                notifyController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkIsExit(String result) {
        if (result.toLowerCase().equals("выход") || result.toLowerCase().equals("exit")) {
            System.out.println("Завершение работы");
        }

    }

    private void setGlobalcity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    private void validateUserInput (String userInput) throws IOException {
        if (userInput == null || userInput.length() !=1) {
            throw new IOException("Некорректный выбор операции" + userInput);
        }
        int answer = 0;
        try {
            answer = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IOException("Введен не номер");
        }

    }

    private void notifyController (String input) throws IOException {
        controller.onUserInput(input);
    }
}
