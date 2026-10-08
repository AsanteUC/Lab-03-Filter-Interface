/**
 * A Filter that accepts short words: any String whose length is less than 5
 * characters. Anything that isn't a String is rejected outright.
 *
 * @author Your Name
 * @version 1.0
 */
public class ShortWordFilter implements Filter
{
    /** A word must be strictly shorter than this to be accepted. */
    public static final int MAX_SHORT_LENGTH = 5;

    /**
     * Accepts x if it is a String shorter than MAX_SHORT_LENGTH characters.
     *
     * @param x the object to test
     * @return true if x is a String of length less than 5
     */
    @Override
    public boolean accept(Object x)
    {
        if (!(x instanceof String))
        {
            return false;
        }
        String s = (String) x;
        return s.length() < MAX_SHORT_LENGTH;
    }
}