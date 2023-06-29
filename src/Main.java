import Views.LoginView;

public class Main {
    public static void main(String[] args) {
        new LoginView();


//        LoginView login = new LoginView();
//        login.setVisible(true);
//        Listagem
//        PersonDAO personDAO = new PersonDAO();
//        for (Person person : personDAO.findAll()) {
//            System.out.println(person.getName());
//        }

//        Mostrar
//        PersonDAO personDAO = new PersonDAO();
//        Person person = personDAO.findOneById(30);
//        System.out.println(person.getName());

//        Store
//        pessoa
//        Person person = new Person();
//        person.setName("Rafael");
//        person.setEmail("rafael@test.com.br");
//        person.setPhone("5440028922");
//        person.setDriver(true);
//
//        PersonDAO personDAO = new PersonDAO();
//        personDAO.insert(person);

//        veículo
//        Vehicle vehicle = new Vehicle();
//        vehicle.setPersonId(1);
//        vehicle.setColor("Preto");
//        vehicle.setBrand("Fiat");
//        vehicle.setModel("Uno");
//        vehicle.setPlate("ABC-1234");
//        vehicle.setSeats(20);
//
//        VehicleDAO vehicleDAO = new VehicleDAO();
//        vehicleDAO.insert(vehicle);

//        endereço
//        Address address = new Address();
//        address.setNumber("123");
//        address.setComplement("Casa");
//        address.setStreet("Rua Teste");
//        address.setNeighborhood("Centro");
//        address.setCity("Veranópolis");
//        address.setState("Rio Grande do Sul");
//        address.setCountry("Brasil");
//        address.setPostalCode("95330000");
//
//        AddressDAO addressDAO = new AddressDAO();
//        addressDAO.insert(address);

//        carona
//        Ride ride = new Ride();
//        ride.setDescription("IFRS");
//        ride.setDate(new java.util.Date());
//        ride.setVehicleId(1);
//        ride.setPersonId(1);
//        ride.setAddressId(1);
//        ride.setRideType(1);
//
//        RideDAO rideDAO = new RideDAO();
//        rideDAO.insert(ride);

//        Update
//        RideDAO rideDAO = new RideDAO();
//        Ride ride = rideDAO.findOneById(2);
//        ride.setDescription("IFRS Veranópolis");
//        rideDAO.update(ride);

//        Destroy
//        PersonDAO personDAO = new PersonDAO();
//        personDAO.delete(30);
    }
}