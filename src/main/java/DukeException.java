public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "∑(O_O;) Oh no!! " + getMessage();
    }
}
