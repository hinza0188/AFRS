package helpers;

/**
 * Created by hetelek on 11/3/16.
 */
public interface Iterable<T>
{
    /**
     * Create an iterator for this iterable.
     * @return
     */
    Iterator<T> getIterator();
}
