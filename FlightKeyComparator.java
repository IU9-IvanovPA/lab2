package funkpaws.hadoop.lab2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class FlightKeyComparator extends WritableComparator {
    protected FlightKeyComparator(){
        super(FlightKey.class , true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        FlightKey left = (FlightKey) a;
        FlightKey right = (FlightKey) b;
        return left.getAirportId() - right.getAirportId();
    }
}
