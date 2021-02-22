package zapisi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class TaxiRideRecord {

    private static int counter = 0;
    
    private int id;
    private String medallion;
    private String hackLicense;
    private Date pickup;
    private Date dropoff;
    private int duration;
    private double distance;
    private double pickupLongitude;
    private double pickupLatitude;
    private double dropoffLongitude;
    private double dropoffLatitude;
    private String paymentType;
    private double fare;
    private double surcharge;
    private double tax;
    private double tip;
    private double tolls;
    private double total;

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TaxiRideRecord(String record) {
        id = counter++;
        String[] splitted = record.split(",");
        medallion = splitted[0];
        hackLicense = splitted[1];
        try {
            pickup = DATE_FORMATTER.parse(splitted[2]);
            dropoff = DATE_FORMATTER.parse(splitted[3]);
        } catch (ParseException ex) {
        }
        duration = Integer.parseInt(splitted[4]);
        distance = Double.parseDouble(splitted[5]);
        pickupLongitude = Double.parseDouble(splitted[6]);
        pickupLatitude = Double.parseDouble(splitted[7]);
        dropoffLongitude = Double.parseDouble(splitted[8]);
        dropoffLatitude = Double.parseDouble(splitted[9]);
        paymentType = splitted[10];
        fare = Double.parseDouble(splitted[11]);
        surcharge = Double.parseDouble(splitted[12]);
        tax = Double.parseDouble(splitted[13]);
        tip = Double.parseDouble(splitted[14]);
        tolls = Double.parseDouble(splitted[15]);
        total = Double.parseDouble(splitted[16]);
    }   

    public int getId() {
        return id;
    }

    public String getMedallion() {
        return medallion;
    }

    public String getHackLicense() {
        return hackLicense;
    }

    public Date getPickup() {
        return pickup;
    }

    public Date getDropoff() {
        return dropoff;
    }

    public int getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public double getPickupLongitude() {
        return pickupLongitude;
    }

    public double getPickupLatitude() {
        return pickupLatitude;
    }

    public double getDropoffLongitude() {
        return dropoffLongitude;
    }

    public double getDropoffLatitude() {
        return dropoffLatitude;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public double getFare() {
        return fare;
    }

    public double getSurcharge() {
        return surcharge;
    }

    public double getTax() {
        return tax;
    }

    public double getTip() {
        return tip;
    }

    public double getTolls() {
        return tolls;
    }

    public double getTotal() {
        return total;
    }
    
    

    public static boolean isParsable(String line) {
        try {
            String[] splitted = line.split(",");
            DATE_FORMATTER.parse(splitted[2]);
            DATE_FORMATTER.parse(splitted[3]);
            Integer.parseInt(splitted[4]);
            Double.parseDouble(splitted[5]);
            Double.parseDouble(splitted[6]);
            Double.parseDouble(splitted[7]);
            Double.parseDouble(splitted[8]);
            Double.parseDouble(splitted[9]);
            Double.parseDouble(splitted[11]);
            Double.parseDouble(splitted[12]);
            Double.parseDouble(splitted[13]);
            Double.parseDouble(splitted[14]);
            Double.parseDouble(splitted[15]);
            Double.parseDouble(splitted[16]);
            return true;
        } catch (ParseException | NumberFormatException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return medallion + "," + hackLicense + "," + DATE_FORMATTER.format(pickup) + "," + DATE_FORMATTER.format(dropoff) + "," + duration + "," + distance + "," + 
                pickupLongitude + "," + pickupLatitude + "," + dropoffLongitude + "," + dropoffLatitude + "," + paymentType + "," + fare + "," + 
                surcharge + "," + tax + "," + tip + "," + tolls + "," + total;
    }
    
    static void resetCounter() {
        counter = 0;
    }
}
