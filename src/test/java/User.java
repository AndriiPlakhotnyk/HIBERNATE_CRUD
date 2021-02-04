import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "iduser")
    private int id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

