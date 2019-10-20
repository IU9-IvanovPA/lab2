package funkpaws.hadoop.lab2;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class FlightKeyReducer extends Reducer<FlightKey , Text , Text , Text> {
    @Override
    protected void reduce(FlightKey key , Iterable <Text> values, Context context) throws IOException{
        Text airportName = new Text();
        int count = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(Text entry : values){
            if(count == -1){
                airportName = new Text (entry);
                count++;
            } else {
                int delay = Integer.parseInt(entry.toString());
                sum += delay;

                if(delay > max) {
                    max = delay;
                }

                if(delay < min) {
                    min = delay;
                }
                count++;
            }
        }
        if(count > 0) {
            try {
                context.write(airportName, new Text((sum / (double)count) + "\t" + min + "\t" + max));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
