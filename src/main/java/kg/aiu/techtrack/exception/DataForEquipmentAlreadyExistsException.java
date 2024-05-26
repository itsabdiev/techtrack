package kg.aiu.techtrack.exception;

public class DataForEquipmentAlreadyExistsException extends RuntimeException {

    public DataForEquipmentAlreadyExistsException() {
        super("Data for equipment already exists");
    }
}
