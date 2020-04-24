import service.DeveloperService;
import service.ProjectService;

public class Main {
    public static void main(String[] args) {
        final DeveloperService developerService = new DeveloperService();
        final ProjectService projectService = new ProjectService();
        System.out.println();
        //1
        projectService.printSumSalaryForProject("Minecraft");
        System.out.println();
        //2
        developerService.printDevelopersForProject("WorldOfWarcraft");
        System.out.println();
        //3
        developerService.printDevelopersForSkill("Java");
        System.out.println();
        //4
        developerService.printDevelopersForGrade("Middle");
        System.out.println();
        //5
        projectService.printAllProjectsInfo();
        System.out.println();
        //6
        //final CustomerService customerService = new CustomerService();
        //customerService.addCustomer("OfficeWorkers",22,60);
        developerService.addDeveloper("BellaGates", 37, "female", 1, 3200);
        //projectService.addProject("Windows","Vista:revenge",30400,now());
    }
}
