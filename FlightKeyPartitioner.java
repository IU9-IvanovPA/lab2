package funkpaws.hadoop.lab2;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class FlightKeyPartitioner extends Partitioner<FlightKey, Text> {
    @Override
    public int getPartition(FlightKey flightKey, Text text, int i) {
        return flightKey.getAirportId() % i;
    }
}
