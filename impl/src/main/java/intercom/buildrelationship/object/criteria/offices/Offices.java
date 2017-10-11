package intercom.buildrelationship.object.criteria.offices;

import java.awt.*;

/**
 * Represented offices of intercom all over the world.
 * @author Ritesh Dalvi.
 */
public interface Offices {

    /**
     * Set coordinates of the office.
     * @param coordinates coordinates where the intercom office is located (rarely gets updated) (cannot be null).
     */
    public void setCoordinates(final Point coordinates);

    /**
     * @return non-null coordinates of the intercom office.
     */
    public Point getCoordinates();
}
