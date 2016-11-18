package fi.metropolia.busdata.sensoridata;

import java.util.Arrays;
import java.util.List;
import android.util.Log;

/**
 * Created by jasu on 17/11/2016.
 */

public class DataContainer {

    // format <lat, lon>
    public static final Double[] UNDEFINEDCOORDINATES = {-1.0,-1.0};
    private static List<Double> gpsCoordinates = Arrays.asList(UNDEFINEDCOORDINATES);
    public static void setGPS(Double lat, Double lon) {
        Log.e("setGPS", "Values: "+lat+","+lon);
        Double[] arr = {lat, lon};
        gpsCoordinates = Arrays.asList(arr);
    }

    public static List<Double> getGPS(){
        Log.e("getGPS", "Values: "+gpsCoordinates);
        return gpsCoordinates;
    }

    public static void main(String[]args){
        setGPS(0.1,0.1);
        System.out.println(getGPS());
    }
}