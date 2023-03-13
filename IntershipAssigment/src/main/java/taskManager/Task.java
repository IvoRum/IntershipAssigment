package taskManager;



import java.util.Date;

 class Task {
    private String title;
    private String description;
    private Employee assignee;
    private Date due;

    protected Task(){}

    /**
     * Crate a Task just with a title
     * @param title The Task Title(if null title will be 'Untitled')
     * @param description The Task description
     */
    public Task(String title, String description) {
        if(title==null)
            title="Untitled";
        this.title = title;
        if(description==null)
            description="";
        this.description = description;
    }

    /**
     * Create a Task
     * @param title The Task Title(if null title will be 'Untitled')
     * @param description The Task description
     * @param due The due date of the Task()
     */
    public Task(String title, String description, Date due) {
        if(title==null)
            description="Untitled";
        this.title = title;
        if(description==null)
            description="";
        this.description=description;
        if(due==null)
            due = new Date(System.currentTimeMillis());
        this.due = due;
    }

    /**
     * Create a Task
     * @param title The Task Title(if null title will be 'Untitled')
     * @param description The Task description
     * @param assignee the assignee to the Task(if null the assignee is going to be a black instance)
     */
    public Task(String title, String description, Employee assignee) {
        if(title==null)
            description="Untitled";
        this.title = title;
        if(description==null)
            description="";
        this.description=description;
        if(assignee==null)
            assignee=new Employee();
        this.assignee = assignee;
    }

    /**
     * Create a Task
     * @param title The Task Title(if null title will be 'Untitled')
     * @param description The Task description
     * @param assignee the assignee to the Task(if null the assignee is going to be a black instance)
     * @param due The due date of the Task()
     */
    public Task(String title, String description, Employee assignee, Date due) {
        if(title==null)
            description="Untitled";
        this.title = title;
        if(description==null)
            description="";
        this.description=description;
        if(assignee==null)
            assignee=new Employee();
        this.assignee = assignee;
        if(due==null)
            due = new Date(System.currentTimeMillis());
        this.due = due;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title==null)
            throw new IllegalArgumentException("The Title of the Task must not be empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description==null)
            throw new IllegalArgumentException("The Description of the Task must not be empty");
        this.description = description;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        if(assignee==null)
            throw new IllegalArgumentException("The assignee is not valid");
        this.assignee = assignee;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        Date today=new Date(System.currentTimeMillis());
        if(due==null||due.compareTo(today)>0)
            throw new IllegalArgumentException("The Due Date of the Task is not valid");
        this.due = due;
    }

     @Override
     public String toString() {
         return "Task{" +
                 "title='" + title + '\'' +
                 ", description='" + description + '\'' +
                 ", assignee=" + assignee +
                 ", due=" + due +
                 '}';
     }
 }
