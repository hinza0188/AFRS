/**
 * Created by hetelek on 10/6/16.
 */

package server;

public class RequestParser
{
    private String currentData;

    public RequestParser()
    {
        this.clearData();
    }

    public void appendData(String data)
    {
        this.currentData += data;
    }

    public Request[] parseData()
    {
        return new Request[] { };
    }

    public void clearData()
    {
        this.currentData = "";
    }
}
