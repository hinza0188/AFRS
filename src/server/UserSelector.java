package server;

import java.util.Map;
import java.util.HashMap;

public class UserSelector
{
    private Map<String, RequestManager> managers = new HashMap<>();
    public RequestManager currentManager;

    public void changeUser(String userId)
    {
        // check if we already have a manager
        if (this.managers.containsKey(userId))
            this.currentManager = this.managers.get(userId);
        else
        {
            // create manager and add to hash map
            this.currentManager = new RequestManager();
            this.managers.put(userId, this.currentManager);
        }
    }

    public String[] takeCommand(String command)
    {
        // make sure we have a manager
        if (this.currentManager == null)
            return null;

        // parse command
        return this.currentManager.takeCommand(command);
    }
}
