import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        //1
        QueryToDB.printSumSalaryOf("Minecraft");
        System.out.println();
        //2
        QueryToDB.printDevelopersOf("WorldOfWarcraft");
        System.out.println();
        //3
        QueryToDB.printAllDevelopersOfSkill("Java");
        System.out.println();
        //4
        QueryToDB.printAllDevelopersOfGrade("Middle");
        System.out.println();
        //5
        QueryToDB.printAllProjectsInfo();
        /*
        try{
            justDoItsQuery(QueryToDB.getQuery1());
            justDoItsQuery(QueryToDB.getQuery2());
            justDoItsQuery(QueryToDB.getQuery3());
        } catch (Exception e) {
            System.out.println("wrong");
        }*/
    }
}
