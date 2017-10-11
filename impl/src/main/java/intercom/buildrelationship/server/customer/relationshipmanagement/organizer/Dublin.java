package intercom.buildrelationship.server.customer.relationshipmanagement.organizer;

import intercom.buildrelationship.object.criteria.offices.Offices;

import java.awt.*;

/**
 * Class which represents properties/attributes of Dublin office.
 * @author Ritesh Dalvi.
 */
class Dublin implements Offices {

    private Point coordinates;

    /**
     * {@inheritDoc}
     */
    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getCoordinates() {
        return coordinates;
    }
}
