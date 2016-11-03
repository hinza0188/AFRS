package helpers;

import java.io.FileNotFoundException;

/**
 * Created by hetelek on 11/3/16.
 */
public class CSVIterator implements Iterator<String[]>
{
    private CSVReader reader;
    private String[] currentLine;
    public CSVIterator(CSVReader reader)
    {
        this.reader = reader;
    }

    /**
     * Move to the first iterable item.
     */
    public void first()
    {
        try
        {
            this.reader.close();
            this.reader.open();

            this.currentLine = this.reader.readLine();
        }
        catch (Exception ex) { }
    }

    /**
     * Move to the next item.
     */
    public void next()
    {
        if (this.currentItem() != null)
            this.currentLine = this.reader.readLine();
    }

    /**
     * Get the current item.
     * @return
     */
    public String[] currentItem()
    {
        return this.currentLine;
    }
}
