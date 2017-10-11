package intercom.buildrelationship.server.assistant.calculation;

/**
 * Calculates the distance between two point on Geo sphere.
 * @author Ritesh Dalvi.
 */
public class GeoCalculation {

    /**
     * Calculates the distance between two points on a sphere.
     * @param officeLatitude The latitude of the intercom office (may be negative).
     * @param officeLongitude The longitude of the intercom office (may be negative).
     * @param customerLatitude The customer latitude information (may be negative).
     * @param customerLongitude The customer longitude information (may be negative).
     * @return distance between two points.
     */
    public static int calculateDistance(final double officeLatitude, final double officeLongitude, final double customerLatitude, final double customerLongitude) {

        double officeLatitudeInRadians = Math.toRadians(officeLatitude);
        double officeLongitudeInRandians = Math.toRadians(officeLongitude);
        double customerLatitudeInRadians = Math.toRadians(customerLatitude);
        double customerLongitutudeInRadians = Math.toRadians(customerLongitude);

        double longitudeDifferenceInRadians = Math.abs(officeLongitudeInRandians-customerLongitutudeInRadians);
        double latitudeDifference = Math.abs(officeLatitudeInRadians - customerLatitudeInRadians);

        double sinOfOfficeLatitude = Math.sin(officeLatitudeInRadians);
        double sinOfCustomerLatitude = Math.sin(customerLatitudeInRadians);
        double cosOfOfficeLatitude = Math.cos(officeLatitudeInRadians);
        double cosOfCustomerLatitude = Math.cos(customerLatitudeInRadians);
        double cosOfDifferenceInLongitude = Math.cos(longitudeDifferenceInRadians);

        double intermidiateDistance = sinOfOfficeLatitude * sinOfCustomerLatitude + cosOfOfficeLatitude*cosOfCustomerLatitude*cosOfDifferenceInLongitude;

        return new Double(Math.toDegrees(Math.acos(intermidiateDistance))*69.09*1.6093).intValue();
    }
}
