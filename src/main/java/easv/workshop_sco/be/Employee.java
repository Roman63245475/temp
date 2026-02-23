package easv.workshop_sco.be;

public class Employee {

    private int id;
    private String name;
    private float personalBonus;
    private boolean on_leave;
    private String phoneNumber;
    private float salary;
    private String salaryGroup;

    public Employee(int id, String name, float personalBonus, boolean on_leave, String phoneNumber, float salary, String salary_group) {
        this.id = id;
        this.name = name;
        this.personalBonus = personalBonus;
        this.salary = salary;
        this.on_leave = on_leave;
        this.phoneNumber = phoneNumber;
        this.salaryGroup = salary_group;
    }

    public String getSalaryGroup() {
        return salaryGroup;
    }

    public float getPersonalBonus() {
        return personalBonus;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public boolean getOnLeave() {
        return on_leave;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
