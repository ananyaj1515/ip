public class Event extends Task{

    public String start;
    public String end;

    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String storeFormat() {
        return "E | " + this.getStoreStatus() + " | " + this.getDesc() + " | " + start + "-" + end;
    }
}
