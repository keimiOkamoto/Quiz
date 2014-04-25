package view;

import constants.ExceptionMessages;
import constants.PlayerMessages;
import controllers.QuizPlayerOrchestrator;
import models.Player;

import java.rmi.RemoteException;
import java.util.Scanner;

public class PlayerCreatorImpl implements PlayerCreator {
    @Override
    public Player createPlayer(Player player, Scanner scanner, QuizPlayerOrchestrator quizPlayerOrchestrator) {
        String message1;
        String name = null;
        String country = null;
        String age;
        int age1 = 0;
        message1 = PlayerMessages.START_MESSAGE;

        while (message1 == PlayerMessages.START_MESSAGE) {
            System.out.println(PlayerMessages.GET_NAME_MESSAGE);
            name = scanner.nextLine();
            if (name.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);

            if (!checkForNull(name)) {
                message1 = PlayerMessages.GET_COUNTRY_MESSAGE;
            } else {
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            }
            while (message1.equals(PlayerMessages.GET_COUNTRY_MESSAGE)) {
                System.out.println(message1);
                country = scanner.nextLine();
                if (country.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);

                if (!checkForNull(country)) {
                    message1 = PlayerMessages.GET_AGE_MESSAGE;
                } else {
                    System.out.println(ExceptionMessages.INVALID_USER_INPUT);
                }

                while (message1.equals(PlayerMessages.GET_AGE_MESSAGE)) {
                    System.out.println(message1);
                    age = scanner.nextLine();
                    if (age.equals(PlayerMessages.EXIT_MESSAGE)) System.exit(0);

                    age1 = 0;
                    try {
                        age1 = Integer.parseInt(age);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(ExceptionMessages.NO_NUMBER_ENTERED);
                    }
                }
            }
        }
        try {
            player = quizPlayerOrchestrator.addPlayer(name, country, age1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return player;
    }

    private static boolean checkForNull(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }
}
