package helpers;

/**
 * Interface of the iterator object
 */
interface Iterator<T>
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
     * @return : current item that the iterator is pointing at
     */
    T currentItem();
}
