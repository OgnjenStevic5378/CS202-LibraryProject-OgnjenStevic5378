package DataClass;

import java.util.Date;

/**
 * CREATE TABLE client (
 * id_client INT NOT NULL PRIMARY KEY,
 * name varchar(30) NOT NULL,
 * last_name varchar(30) NOT NULL,
 * birth_date date
 * );
 */

public class Client {
    private int idClient;
    private String name;
    private String lastName;
    private Date birthDate;

    public Client() {
    }

    public Client(int idClient, String name, String lastName, Date birthDate) {
        this.idClient = idClient;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getIdClient() {
        return idClient;
    }

    public Client setIdClient(int idClient) {
        this.idClient = idClient;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Client setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
