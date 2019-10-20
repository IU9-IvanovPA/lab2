package funkpaws.hadoop.lab2;

import org.apache.hadoop.io.Writable;
import org.mortbay.util.IO;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportEntry implements Writable{

    public int getAirportID() {
        return airportID;
    }

    private int airportID;

    public String getAirportName() {
        return airportName;
    }

    private  String airportName;
    public final static int CODE = 0;
    public final static int DESC = 1;

    public AirportEntry(){

    }
    public AirportEntry(String string){
        string = cutQuotes(string);
        String[]params = string.split("\",\"");
        String code = params[CODE];
        String description = params[DESC];
        airportID = Integer.parseInt(code);
        airportName = description;
    }

    public String cutQuotes(String string){
        return string.substring(1,string.length() - 1);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(airportID);
        dataOutput.writeUTF(airportName);
    }

    @Override
    public void readFields (DataInput dataInput) throws IOException{
        airportID = dataInput.readInt();
        airportName = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return airportID + "," + airportName;
    }
}
