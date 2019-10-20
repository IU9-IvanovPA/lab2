package funkpaws.hadoop.lab2;

import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightEntry implements Writable {
    private int airportId;
    private int delay;
    private boolean canceled;
    public final static int CANCELED = 19;
    public final static int DEST_AIRPORT_ID = 14;
    public final static int ARR_DELAY_NEW = 18;

    public boolean isCanceled() {
        return canceled;
    }

    public int getDelay() {
        return delay;
    }

    public int getAirportId() {
        return airportId;
    }
    public FlightEntry() {
    }

    public FlightEntry(String string) {
        String[] params = string.split(",");
        airportId = Integer.parseInt(params[DEST_AIRPORT_ID]);
        canceled = (((int) Double.parseDouble(params[CANCELED])) == 1);

        if (!canceled && !params[ARR_DELAY_NEW].equals("")) {
            delay = (int) Double.parseDouble(params[ARR_DELAY_NEW]);
        } else {
            delay = 0;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(airportId);
        dataOutput.writeInt(delay);
        dataOutput.writeBoolean(canceled);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        airportId = dataInput.readInt();
        delay = dataInput.readInt();
        canceled = dataInput.readBoolean();
    }

    @Override
    public String toString() {
        return airportId + "," + delay + "," + canceled;
    }
}
