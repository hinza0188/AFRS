package helpers;

/**
 * Interface of iterator class
 */
interface Iterable<T>
{
    /**
     * Create an iterator for this iterable.
     * @return Iterator object
     */
    Iterator<T> getIterator();
}
