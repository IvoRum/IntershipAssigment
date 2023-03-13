package taskManager;

import exceptions.*;

import java.util.Date;

public interface TaskManagerControls {
    /**
     * Create an employee with the specified name.
     * @param name The employee`s full name
     * @param email The employee`s email
     * @param phoneNumber The employee`s phone number, stander phone numbers from 4 to 12 digits
     * @param dateOfBirth Date of birth of the employee(yyyy-MM-dd)
     * @param salary The employee`s salary
     * @throws EmployeeCreationException
     */
    void createNewEmployee(String name, String email, String phoneNumber, Date dateOfBirth, double salary,String dirPath)throws EmployeeCreationException;

    /**
     * Create a Task
     * @param title The Task Title(if null title will be 'Untitled')
     * @param description The Task description
     * @param assignee the name of the assignee for the Task(if null the assignee is going to be a black instance)
     * @param due The due date of the Task()
     * @param dirPath The path to the file
     * @throws TaskCreationException
     */
    void createNewTask(String title, String description, String assignee, Date due,String dirPath)throws TaskCreationException;
    void updateEmployee(String name, String email, String phoneNumber, Date dateOfBirth, double salary,String dirPath)throws UpdateException;
    void updateTask(String title, String description, String assignee, Date due,String dirPath)throws UpdateException;
    void deleteEmployee(String employeeName)throws DeleteException;
    void deleteTask(String taskTittle)throws DeleteException;
    void getTheBestFiveEmployees()throws StatisticalException;
    void getEmployeeAllTaskStatistics()throws StatisticalException;
    void getOverallPerformance()throws StatisticalException;
}
