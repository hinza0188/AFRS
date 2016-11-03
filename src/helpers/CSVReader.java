package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads a CSV file from disk line by line.
 */
public class CSVReader implements Iterable<String[]>
{
    private String filePath;
    private BufferedReader br;

    /**
     * Initialize with given file path.
     * @param filePath
     */
    public CSVReader(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Open the file.
     * @throws FileNotFoundException
     */
    public void open() throws FileNotFoundException
    {
        this.br = new BufferedReader(new FileReader(this.filePath));
    }

    /**
     * Close the file.
     */
    public void close()
    {
        try
        {
            this.br.close();
        } catch (Exception ex)
        {
        }
    }

    public CSVIterator getIterator()
    {
        return new CSVIterator(this);
    }

    /**
     * Read the next line in the file and split by comma.
     * @return
     */
    public String[] readLine()
    {
        try
        {
            String line = this.br.readLine();
            if (line != null)
            {
                // use comma as separator
                return line.split(",");
            }
        } catch (Exception ex)
        {
        }

        // close and return
        this.close();
        return null;
    }
}