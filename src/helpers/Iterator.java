package helpers;

/**
 * Created by hetelek on 11/3/16.
 */
public interface Iterator<T>
{
    /**
     * Move to the first iterable item.
     */
    void first();

    /**
     * Move to the next item.
     */
    void next();

    /**
     * Get the current item.
     * @return
     */
    T currentItem();
}
