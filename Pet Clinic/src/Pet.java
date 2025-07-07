public class Pet {
    private int id;
    private String name;
    private String type;
    private int age;
    private Owner owner;

    public Pet() {}

    public Pet(int id, String name, String type, int age, Owner owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.owner = owner;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", owner=" + owner.getName() +
                '}';
    }
}
