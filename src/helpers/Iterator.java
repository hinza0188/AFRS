package helpers;

/**
 * Created by hetelek on 11/3/16.
 */
public interface Iterator<T>
{
    void first();
    void next();
    T currentItem();
}
