import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String lastname = scanner.nextLine();
        System.out.println("Введите имя:");
        String firstname = scanner.nextLine();
        System.out.println("Введите отчество:");
        String patronymic = scanner.nextLine();
        String gender = getGender(patronymic);
        System.out.println("Введите дату рождения в формате дд.мм.гггг:");
        String birthdate = scanner.nextLine();
        scanner.close();

        System.out.println("Фамилия и инициалы: " + lastname + " " + firstname.charAt(0) + "." + patronymic.charAt(0) + ".");
        System.out.println("Пол: " + gender);

        int age = getAge(birthdate);
        if (age % 10 == 1 && age % 100 != 11) {
            System.out.println("Возраст: " + age + " год");
        }
        else if (age % 10 > 1 && age % 10 < 5 && (age % 100 < 10 || age % 100 >= 20)) {
            System.out.println("Возраст: " + age + " года");
        }
        else {
            System.out.println("Возраст: " + age + " лет");
        }
    }


    private static String getGender(String patronymic) {
        if (patronymic.endsWith("ич")) {
            return "Мужской";
        }
        else {
            return "Женский";
        }
    }


    private static int getAge(String birthdate) {
        LocalDate today = LocalDate.now();
        try {
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthday = LocalDate.parse(birthdate, newFormat);
            return Period.between(birthday, today).getYears();
        }
        catch (DateTimeException ex) {
            System.out.println("Введенная дата рождения некорректна!");
            System.exit(1);
            return -1;
        }
    }
}
