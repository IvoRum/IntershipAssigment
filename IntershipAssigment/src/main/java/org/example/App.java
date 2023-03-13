package org.example;

import exceptions.EmployeeCreationException;
import exceptions.TaskCreationException;
import exceptions.UpdateException;
import org.jetbrains.annotations.NotNull;
import taskManager.TaskManager;

import java.util.Date;
import java.util.Scanner;

public class App
{
    private static TaskManager taskManager;
    public static void printMenu(String @NotNull [] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    static public void main(String[] args) {
        String jsonpathToEmployees="src/main/resources/employees.jason";
        String jsonpathToTasks="src/main/resources/tasks.json";
        pathInput(jsonpathToEmployees,jsonpathToTasks);
        String[] options = {
                "1- Create new Employee",
                "2- Create new Task",
                "3- Update Employee",
                "4- Update Task",
                "5- Show Five best performing employees",
                "6- Show All Statistics",
                "0- EXIT",
        };
        Scanner scanner = new Scanner(System.in);
        int option=1;
        while (option!=0){
            printMenu(options);
            try{
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        EmployeeInputScript(jsonpathToEmployees);
                        break;
                    case 2:
                        TaskInputScript(jsonpathToTasks);
                        break;
                    case 3:
                        UpdateEmployeeScript(jsonpathToEmployees);
                        break;
                    case 4:
                        UpdateTaskScript(jsonpathToTasks);
                        break;
                    case 5:
                        taskManager.getTheBestFiveEmployees();
                        break;
                    case 6:
                        taskManager.getOverallPerformance();
                        break;
                }
            }catch (Exception e){
              //  e.printStackTrace();
            }


        }
    }

    private static void UpdateTaskScript(String jsonpath) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Title of the task");
        String title=scanner.nextLine();
        System.out.println("Discription of the task");
        String discription=scanner.nextLine();
        System.out.println("Name of tje assignee for the task");
        String nameOfAssignee=scanner.nextLine();
        System.out.println("Due Date for the Task yyyyMMdd");
        String due=scanner.nextLine();
        Date date=new Date(Long.parseLong(due));
        try {
            taskManager.updateTask(title,discription,nameOfAssignee,date,jsonpath);
        } catch (UpdateException e) {
            e.printStackTrace();
        }
    }

    private static void UpdateEmployeeScript(String jsonpath) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give Full name of the Employee: ");
        String name=scanner.nextLine();
        System.out.println("Give new Email of Employee: ");
        String email=scanner.nextLine();
        System.out.println("Give new Phone Number of the Employee: ");
        String phoneNumber=scanner.nextLine();
        System.out.println("Give new Date of burth yyyyMMdd: ");
        String tempDate=scanner.nextLine();
        Date date=new Date(Long.parseLong(tempDate));
        System.out.println("Salary of the Employee: ");
        String salaryTemp=scanner.nextLine();
        double salary= Double.parseDouble(salaryTemp);
        try {
            taskManager.updateEmployee(name,email,phoneNumber,date,salary,jsonpath);
        } catch (UpdateException e) {
            e.printStackTrace();
        }
    }

    private static void TaskInputScript(String jsonpath) throws TaskCreationException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Title of the task");
        String title=scanner.nextLine();
        System.out.println("Discription of the task");
        String discription=scanner.nextLine();
        System.out.println("Name of tje assignee for the task");
        String nameOfAssignee=scanner.nextLine();
        System.out.println("Due Date for the Task yyyyMMdd");
        String due=scanner.nextLine();
        Date date=new Date(Long.parseLong(due));
        taskManager.createNewTask(title,discription,nameOfAssignee,date,jsonpath);
    }

    private static void EmployeeInputScript(String path) throws EmployeeCreationException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give Full name of new Employee: ");
        String name=scanner.nextLine();
        System.out.println("Give Email of new Employee: ");
        String email=scanner.nextLine();
        System.out.println("Give Phone Number of the Employee: ");
        String phoneNumber=scanner.nextLine();
        System.out.println("Give Date of burth yyyyMMdd: ");
        String tempDate=scanner.nextLine();
        Date date=new Date(Long.parseLong(tempDate));
        System.out.println("Salary of the Employee: ");
        String salaryTemp=scanner.nextLine();
        double salary= Double.parseDouble(salaryTemp);
        taskManager.createNewEmployee(name,email,phoneNumber,date,salary,path);
    }

    static void pathInput(String employeePath,String taskPath){
        if(employeePath==null||employeePath.length()==0||
            taskPath==null||taskPath.length()==0)
            throw new RuntimeException();
        try {
            taskManager =new TaskManager(employeePath,taskPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
