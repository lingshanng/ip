public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    
    public DukeException concat(Exception e) {
        return new DukeException(this.getMessage() + " " + e.getMessage());
    }
    
    @Override
    public String toString() {
        return "∑(O_O;) Oh no!! " + getMessage();
    }
}
