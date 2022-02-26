package midterm_act3;

abstract class Professor {
    protected String name;
    protected int salary;
}

class Dean extends Professor {
    protected String department;
    protected String rank;

    public Dean(String name, int salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.rank = "Dean";
    }

    /* Docu: display_status()
     *  "Displays Rank, Name, Department and salary"
     *  Takes 0 argument
     *  Author: Arjhay Frias 02/26/2022 */
    public void display_status() {
        System.out.println("Status");
        System.out.println("Rank: " + this.rank);
        System.out.println("Name: " + this.name);
        System.out.println("Department: " + this.department);
        System.out.println("Salary: " + this.salary);
        System.out.println("--------------------------------------------------");
    }
}

class Executive extends Dean {
    public Executive(String name, int salary, String department) {
        super(name, salary, department);
        this.rank = "Executive";
    }
}

public class School {
    public static void main(String[] args) {
        // Create object dean1 and executive1
        Dean dean1 = new Dean("John Cena", 32000, "English");
        dean1.display_status();

        Executive executive1 = new Executive("Ronald McDonald", 42000, "Computer Science");
        executive1.display_status();
    }
}