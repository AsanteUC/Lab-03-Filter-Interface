import java.awt.Rectangle;

/**
 * A Filter that accepts "big" java.awt.Rectangle objects: any Rectangle whose
 * perimeter is strictly greater than 10. Anything that isn't a Rectangle is
 * rejected outright.
 *
 * @author Your Name
 * @version 1.0
 */
public class BigRectangleFilter implements Filter
{
    /** A rectangle's perimeter must exceed this to be accepted. */
    public static final double MIN_PERIMETER = 10.0;

    /**
     * Accepts x if it is a Rectangle whose perimeter is greater than 10.
     *
     * @param x the object to test
     * @return true if x is a Rectangle with perimeter > 10
     */
    @Override
    public boolean accept(Object x)
    {
        if (!(x instanceof Rectangle))
        {
            return false;
        }
        Rectangle r = (Rectangle) x;
        double perimeter = 2.0 * (r.getWidth() + r.getHeight());
        return perimeter > MIN_PERIMETER;
    }
}