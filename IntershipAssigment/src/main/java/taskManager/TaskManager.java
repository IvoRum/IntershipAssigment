package taskManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.*;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


public class TaskManager implements TaskManagerControls {
    private List<Employee> employees;
    private List<Task> tasks;

    public TaskManager(String employeesPath,String tasksPath) throws ManagerException {
        tasks=new ArrayList<>();
        employees=new ArrayList<>();
        Load(employeesPath,tasksPath);
    }

    private void Load(String employeesPath,String tasksPath) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            List<Employee> employeeRepo=mapper.readValue(new File(employeesPath),new TypeReference<List<Employee>>(){});
            employees.addAll(employeeRepo);
            List<Task> taskRepo=mapper.readValue(new File(tasksPath),new TypeReference<List<Task>>(){});
            tasks.addAll(taskRepo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployee(String dirPath) throws JsonReadWriteExceptions {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            try {
                objectMapper.writeValue(Paths.get(dirPath).toFile(),employees);
            } catch (Exception e) {
                e.printStackTrace();
                throw new JsonReadWriteExceptions("ERROR when writhing Employees");
            }
    }

    @Override
    public void createNewEmployee(String name, String email, String phoneNumber, Date dateOfBirth, double salary, String dirPath) throws EmployeeCreationException {
        try {
            employees.add(new Employee(name, email, phoneNumber, dateOfBirth, salary));
            saveEmployee(dirPath);
        }catch (Exception e){
            e.printStackTrace();
            throw new EmployeeCreationException("Employee creation problem in TaskManager.class");
        }
    }

    @Override
    public void createNewTask(String title, String description, String assignee, Date due, String dirPath) throws TaskCreationException {
        Employee employee=SurchEmployee(assignee);
        try{
            tasks.add(new Task(title,description,employee,due));
            saveTask(dirPath);
        }catch (Exception e){
            e.printStackTrace();
            throw new TaskCreationException("Task creation problem at the moment");
        }
    }

    @Override
    public void updateEmployee(String name, String email, String phoneNumber, Date dateOfBirth, double salary, String dirPath) throws UpdateException {
        Employee employee=new Employee();
        for (Employee e :
                employees) {
            if(e.getName().equals(name)){
                employee=e;
                employees.remove(e);
                updateAllTasksWhit(e,dirPath);
            }
        }
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        employees.add(employee);
        try {
            saveEmployee(dirPath);
        } catch (JsonReadWriteExceptions e) {
            e.printStackTrace();
        }

    }

    private void updateAllTasksWhit(Employee employee,String path){
        List<Task> updetedTasks=new ArrayList<>();
        List<Task> oldTasks=new ArrayList<>();
        for (Task t :
                tasks) {
            if(t.getAssignee().equals(employee)){
                oldTasks.add(t);
                t.setAssignee(employee);
                updetedTasks.add(t);
            }
        }
        tasks.removeAll(oldTasks);
        tasks.addAll(updetedTasks);
        try {
            saveTask(path);
        } catch (JsonReadWriteExceptions e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateTask(String title, String description, String assignee, Date due, String dirPath) throws UpdateException {
        Task task=new Task();
        for (Task t :
                tasks) {
            if(t.getTitle().equals(title)){
                task=t;
                tasks.remove(t);
            }
        }
        task.setDescription(description);
        task.setAssignee(SurchEmployee(assignee));
        task.setDue(due);
        tasks.add(task);
        try {
            saveTask(dirPath);
        } catch (JsonReadWriteExceptions e) {
            e.printStackTrace();
        }
    }

    private void saveTask(String dirPath) throws JsonReadWriteExceptions {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        try {
            objectMapper.writeValue(Paths.get(dirPath).toFile(),tasks);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonReadWriteExceptions("ERROR when writhing Task");
        }
    }
    private Task SurchTask(String title) {
        for (Task t :
                tasks) {
            if(t.getTitle().equals(title)){
                return t;
            }
        }
        return null;
    }
    private Employee SurchEmployee(String name) {
        for (Employee e :
                employees) {
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }


    @Override
    public void deleteEmployee(String employeeName) throws DeleteException {

    }

    @Override
    public void deleteTask(String taskTittle) throws DeleteException {

    }

    @Override
    public void getTheBestFiveEmployees() throws StatisticalException {
        Set<Integer> score=new TreeSet<>(Comparator.reverseOrder());
    }

    @Override
    public void getEmployeeAllTaskStatistics() throws StatisticalException {

    }


    @Override
    public void getOverallPerformance() throws StatisticalException {
        for (Employee e :
                employees) {
            System.out.println(e.toString());
        }
        for (Task t :
                tasks) {
            System.out.println(t.toString());
        }
    }
}
