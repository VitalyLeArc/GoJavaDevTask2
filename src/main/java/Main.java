import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QueryToDB.printProjectsName();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите проект по которому вывести сумму зп разрабов");
        String prName=scan.nextLine();
        QueryToDB.printSumSalaryOf(prName);
    }
}
