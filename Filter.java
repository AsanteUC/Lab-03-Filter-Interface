/**
 * Callback interface used to test whether an arbitrary Object should be
 * accepted by whatever process is filtering a collection of them.
 * Implementations decide what "accepted" means for the type they check.
 *
 * @author Your Name
 * @version 1.0
 */
public interface Filter
{

    boolean accept(Object x);
}
