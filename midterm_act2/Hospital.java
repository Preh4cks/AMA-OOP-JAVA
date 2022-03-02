package midterm_act2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class DbModel {
    private String first_name = "0";
    private String last_name = "0";
    private String disease = "0";
    private String birth_date = "0";
    private String discharge_date = "0";

    /* Docu: get_patients(Connection conn)
    *  "Retrieve all the patient in the database"
    *  Requires 1 argument of Connection and Returns ResultSet
    *  Author: Arjhay Frias 02/26/2022 */
    public ResultSet get_patients(Connection conn) {
        try {
            Statement token = conn.createStatement();
            String query = "SELECT *, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age FROM patients";
            return token.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet get_patient(Connection conn) {
        Scanner scan = new Scanner(System.in);
        // Validate First Name
        while(true) {
            System.out.print("Enter First Name: ");
            this.first_name = scan.nextLine();
            if(!this.first_name.matches(".*[0-9].*") && this.first_name.length() > 2) {
                break;
            }
        }

        // Validate last Name
        while(true) {
            System.out.print("Enter Last Name: ");
            this.last_name = scan.nextLine();
            if(!this.last_name.matches(".*[0-9].*") && this.last_name.length() > 2) {
                break;
            }
        }

        try {
            String query = "SELECT *, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age FROM patients WHERE first_name = ? AND last_name = ?;";
            PreparedStatement token = conn.prepareStatement(query);
            token.setString(1, this.first_name);
            token.setString(2, this.last_name);
            System.out.println("");
            return token.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Docu: get_pediatric_patients(Connection conn)
     *  "Retrieve all the pediatric patient in the database"
     *  Requires 1 argument of Connection and Returns ResultSet
     *  Author: Arjhay Frias 02/26/2022 */
    public ResultSet get_pediatric_patients(Connection conn) {
        try {
            Statement token = conn.createStatement();
            String query = "SELECT *, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age FROM patients WHERE TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) < 12;";
            return token.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Docu: display_patients(ResultSet patients)
     *  "Display patients data in a readable format"
     *  Requires 1 argument of ResultSet
     *  Author: Arjhay Frias 02/26/2022 */
    public void display_patients(ResultSet patients) {
        try {
            if (patients.next() == false) {
                System.out.println("No Patient in the Database");
            } else {
                do {
                    String full_name = patients.getString("first_name") + " " + patients.getString("last_name");
                    String disease = patients.getString("disease");
                    String age = patients.getString("age");
                    String birth_date = format_date(patients.getString("birth_date"));
                    String discharge_date = format_date(patients.getString("discharge_at"));
                    String admission_date = format_date(patients.getString("created_at"));

                    System.out.println("Full Name: " + full_name);
                    System.out.println("Disease: " + disease);
                    System.out.println("Age: " + age);
                    System.out.println("Birthday: " + birth_date);
                    System.out.println("Date of Admission: " + admission_date);
                    System.out.println("Date of Discharge: " + discharge_date);
                    System.out.println("---------------------------------------------------------------");
                } while (patients.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Docu: format_date(String raw_date)
     *  "Format date into a readable format"
     *  Requires 1 argument of String and Returns String
     *  Author: Arjhay Frias 02/26/2022 */
    public static String format_date(String raw_date) {
        SimpleDateFormat old_date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat new_date_format = new SimpleDateFormat("MMMMMM dd y");
        try {
            return new_date_format.format(old_date_format.parse(raw_date));
        } catch (Exception e) {
            return raw_date;
        }
    }

    /* Docu: add_patient(Connection conn)
     *  "Get and Validate's user input"
     *  Requires 1 argument of Connection
     *  Author: Arjhay Frias 02/26/2022
     *  UPDATED: 03/03/2022 */
    public void add_patient(Connection conn) {
        Scanner scan = new Scanner(System.in);
        // Get User's first_name and last_name and validate if patient already exists in the database
        while(true) {
            // Validate First Name
            while(!this.first_name.matches("[a-zA-Z]+")) {
                System.out.print("Enter First Name: ");
                this.first_name = scan.nextLine();
                if(!this.first_name.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid First Name, It should not contain numbers");
                }
            }

            // Validate last Name
            while(!this.last_name.matches("[a-zA-Z]+")) {
                System.out.print("Enter Last Name: ");
                this.last_name = scan.nextLine();
                if(!this.last_name.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid Last Name, It should not contain numbers");
                }
            }

            // Check for Patient already exists in the database
            try {
                String query = "SELECT *, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age FROM patients WHERE first_name = ? AND last_name = ?;";
                PreparedStatement token = conn.prepareStatement(query);
                token.setString(1, this.first_name);
                token.setString(2, this.last_name);
                System.out.println("");
                ResultSet patient = token.executeQuery();
                if(patient.next() != false) {
                    // Reset if user already exists
                    this.first_name = "0";
                    this.last_name = "0";
                    System.out.println("Patient Already Exists!");
                } else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Validate Disease
        while(this.disease == "0") {
            System.out.print("Enter Disease: ");
            this.disease = scan.nextLine();
        }

        // Validate Birth Date
        while(!this.birth_date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            System.out.print("Enter Birth Day [yyyy-mm-dd]: ");
            this.birth_date = scan.nextLine();
            if(!this.birth_date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Invalid Format, please try again.");
            }
        }

        // Validate Discharge Date
        while(!this.discharge_date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            System.out.print("Enter Discharge Date [yyyy-mm-dd]: ");
            this.discharge_date = scan.nextLine();
            if(!this.discharge_date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Invalid Format, please try again.");
            }
        }
        insert_patient(conn);
    }

    /* Docu: insert_patient(Connection conn)
     *  "Inserts new patient into the Database"
     *  Requires 1 argument of Connection
     *  Author: Arjhay Frias 02/26/2022 */
    private void insert_patient(Connection conn) {
        try {
            String query = "INSERT INTO patients(first_name, last_name, disease, birth_date, discharge_at, created_at, updated_at) VALUES(?, ?, ?, ?, ?, NOW(), NOW());";
            PreparedStatement token = conn.prepareStatement(query);
            token.setString(1, this.first_name);
            token.setString(2, this.last_name);
            token.setString(3, this.disease);
            token.setString(4, this.birth_date + " 00:00:00");
            token.setString(5, this.discharge_date + " 00:00:00");
            token.execute();
            System.out.println("Congratulations! you are successfully added into our Database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Hospital {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Tennyson123");
            Scanner scan = new Scanner(System.in);
            DbModel db = new DbModel();
            String option;

            System.out.println("Welcome to Hospital Database System");
            System.out.println("1. Get All Patients");
            System.out.println("2. Get All Pediatric Patients");
            System.out.println("3. Retrieve Patient");
            System.out.println("4. Add Patient");
            System.out.println("5. Exit");
            while(true) {
                System.out.print("Select Option: ");
                option = scan.nextLine();
                System.out.println("---------------------------------------------------------------");
                switch(option) {
                    case "1":
                        System.out.println("Retrieving All Patients\n");
                        ResultSet patients = db.get_patients(conn);
                        db.display_patients(patients);
                        break;
                    case "2":
                        System.out.println("Retrieving All Pediatric Patients\n");
                        ResultSet pediatric_patients = db.get_pediatric_patients(conn);
                        db.display_patients(pediatric_patients);
                        break;
                    case "3":
                        System.out.println("Retrieving Patient\n");
                        ResultSet patient = db.get_patient(conn);
                        db.display_patients(patient);
                        break;
                    case "4":
                        System.out.println("Add a Patient");
                        db.add_patient(conn);
                        break;
                    case "5":
                        System.out.println("Thank you for coming!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Option.");
                        System.out.println("Please try again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
