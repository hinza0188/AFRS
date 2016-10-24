package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader
{
    private String filePath;
    private BufferedReader br;

    public CSVReader(String filePath)
    {
        this.filePath = filePath;
    }

    public void open() throws FileNotFoundException
    {
        this.br = new BufferedReader(new FileReader(this.filePath));
    }

    public void close()
    {
        try
        {
            this.br.close();
        } catch (IOException ex)
        {
        }
    }

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