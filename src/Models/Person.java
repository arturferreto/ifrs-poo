package Models;

public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    private boolean isDriver;
    private int favAddressId;
    private int favVehicleId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone.length() <= 20) {
            this.phone = phone;
        }
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public int getFavAddressId() {
        return favAddressId;
    }

    public void setFavAddressId(int favAddressId) {
        this.favAddressId = favAddressId;
    }

    public int getFavVehicleId() {
        return favVehicleId;
    }

    public void setFavVehicleId(int favVehicleId) {
        this.favVehicleId = favVehicleId;
    }
}
