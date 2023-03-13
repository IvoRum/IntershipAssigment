package taskManager;

import java.util.Date;

/** Represents an employee.
 * @auther Ivaylo Rumenov
 */
 class Employee {
    private String name;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private double salary;

    public Employee(){}

    /**
     * Create an employee with the specified name.
     * @param name The employee`s full name
     * @param email The employee`s email
     * @param phoneNumber The employee`s phone number, stander phone numbers from 4 to 12 digits
     * @param dateOfBirth Date of birth of the employee(yyyy-MM-dd)
     * @param salary The employee`s salary
     */
     Employee(String name, String email, String phoneNumber, Date dateOfBirth, double salary) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    /**
     * Gets the employee`s full name
     * @return A String containing the employee`s
     *      full name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null)
            throw new IllegalArgumentException("Name of Employee can not be NULL");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email==null)
            throw new IllegalArgumentException("Email of Employee can not be NULL");
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()<4 ||phoneNumber.length()>12||phoneNumber==null)
            throw new IllegalArgumentException("Phone number of Employee is not correct");
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        Date today=new Date(System.currentTimeMillis());
        if(dateOfBirth==null||dateOfBirth.compareTo(today)>0)
            throw new IllegalArgumentException("Date of Birt is not correct");
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary<0)
            throw new IllegalArgumentException("Employee salary can not be a negative number");
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                '}';
    }
}
