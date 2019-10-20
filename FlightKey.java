package funkpaws.hadoop.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightKey implements WritableComparable<FlightKey> {
    public int getAirportId() {
        return airportId;
    }

    private int airportId;

    public int getType() {
        return type;
    }

    private int type;

    public FlightKey() {

    }

    public FlightKey(int airportId, int type){
        this.airportId = airportId;
        this.type = type;
    }

    @Override
    public int compareTo(FlightKey obj){
        if (airportId != obj.airportId){
            return airportId - obj.airportId;
        } else {
            return  type - obj.type;
        }
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof FlightKey)){
            return  false;
        }
        FlightKey other = (FlightKey)obj;
        return (airportId == other.airportId && type == other.type);
    }
    @Override public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeInt(airportId);
        dataOutput.writeInt(type);
    }

    @Override
    public  void readFields(DataInput dataInput) throws IOException{
        airportId = dataInput.readInt();
        type = dataInput.readInt();
    }

    @Override
    public String toString() {
        return airportId + "," + type;
    }

    @Override public int hashCode() {
        return toString().hashCode();
    }
}
