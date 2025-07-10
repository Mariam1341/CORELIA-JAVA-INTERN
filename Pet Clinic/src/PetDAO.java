import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    public void addPet(Pet pet){
        String query = "INSERT INTO pets (name, type, age,owner, ownerPhone, visitDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getAge());
            statement.setString(4, pet.getOwner());
            statement.setString(5, pet.getOwnerPhone());

            LocalDate today = LocalDate.now();
            statement.setDate(6, Date.valueOf(today));



            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error adding pet " + e.getMessage());
        }
    }

    public List<Pet> getAllPets(){
        String query = "SELECT * FROM pets";
        List<Pet> pets = new ArrayList();
        try (Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                pets.add(new Pet (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getInt("age"),
                        resultSet.getString("owner"),
                        resultSet.getString("ownerPhone"),
                        resultSet.getDate("visitDate")
                ));
            }


        }catch (SQLException e){
            System.out.println("Error fetching pets"+ e.getMessage());
        }
        return pets;
    }

    public List<Pet> searchPets(String keyword){
        String query = "SELECT * FROM pets WHERE name LIKE ? OR type LIKE ? OR owner LIKE ? ";
        List<Pet> pets = new ArrayList();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, keyword);
            statement.setString(2, keyword);
            statement.setString(3, keyword);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                pets.add(new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getInt("age"),
                        resultSet.getString("owner"),
                        resultSet.getString("ownerPhone"),
                        resultSet.getDate("visitDate")
                ));

            }

        }catch (SQLException e){
            System.out.println("Error searching pets: " + e.getMessage());
        }
        return pets;
    }

    public void updatePet(Pet pet){
        String query = "UPDATE pets SET name = ?, type = ?, age = ?, owner = ?, ownerPhone = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getAge());
            statement.setString(4, pet.getOwner());
            statement.setString(5, pet.getOwnerPhone());
            statement.setInt(6, pet.getId());

            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error updating pet: " + e.getMessage());
        }
    }

    public void deletePet(int id){
        String query = "DELETE FROM pets WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error deleting pet: " + e.getMessage());
        }
    }



}
