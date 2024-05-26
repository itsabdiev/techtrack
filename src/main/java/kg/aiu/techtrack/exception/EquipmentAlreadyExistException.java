package kg.aiu.techtrack.exception;


public class EquipmentAlreadyExistException extends RuntimeException {

    public EquipmentAlreadyExistException() {
        super("Equipment already exists");
    }
}