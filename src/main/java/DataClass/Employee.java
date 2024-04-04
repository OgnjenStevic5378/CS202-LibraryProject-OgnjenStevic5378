package DataClass;

/**
 * CREATE TABLE employee (
 * id_employee INT NOT NULL PRIMARY KEY,
 * username varchar(15) NOT NULL,
 * password varchar(64) NOT NULL
 * );
 */


public class Employee {
    private int idEmployee;
    private String username;
    private String password;

    public Employee() {
    }

    public Employee(int idEmployee, String username, String password) {
        this.idEmployee = idEmployee;
        this.username = username;
        this.password = password;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public Employee setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Employee setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
