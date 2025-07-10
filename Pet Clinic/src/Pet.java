import java.sql.Date;
import java.time.LocalDate;

public class Pet {
    private int id;
    private String name;
    private String type;
    private int age;
    private String owner;
    private String ownerPhone;

    private Date visitDate;

    public Pet() {}

    public Pet(int id, String name, String type, int age, String owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.owner = owner;
    }
    public Pet(String name, String type, int age, String owner, String ownerPhone) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.owner = owner;
        this.ownerPhone = ownerPhone;

    }
    public Pet(int id, String name, String type, int age, String owner, String ownerPhone) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.owner = owner;
        this.ownerPhone = ownerPhone;

    }
    public Pet(int id, String name, String type, int age, String owner, String ownerPhone, Date date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.owner = owner;
        this.ownerPhone = ownerPhone;
        this.visitDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Date getDate() {
        return visitDate;
    }

    public void setDate(Date date) {
        this.visitDate = date;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Type: %s | Age: %d | Owner: %s | Owner Phone : %s | Visited Date : %s",
                 id, name, type, age, owner, ownerPhone, visitDate);
    }
}
