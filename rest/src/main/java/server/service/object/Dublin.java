package server.service.object;

import intercom.buildrelationship.object.criteria.offices.Offices;

import java.awt.*;

/**
 * Dublin Office Object.
 * @author Ritesh Dalvi
 **/
public class Dublin implements Offices {

    Point cordinates = null;

    @Override
    public void setCoordinates(Point coordinates) {
        this.cordinates = coordinates;
    }

    @Override
    public Point getCoordinates() {
        return cordinates;
    }
}
