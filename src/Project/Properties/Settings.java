package Project.Properties;

import java.io.*;
import java.util.Properties;

public class Settings
{
    private String settingsPath = "resources/Settings.properties";
    private Properties prop;
    public void createPropertiesFile()
    {
        try(OutputStream output = new FileOutputStream(settingsPath))
        {
            prop = new Properties();
            prop.setProperty("trip.uniqueID","0");
            prop.store(output, null);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public String readFromProperties(String token)
    {
        String output;
        try(InputStream in = new FileInputStream(settingsPath))
        {
            prop = new Properties();
            prop.load(in);
            output = prop.getProperty(token);
            return output;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return "Error in readFromProperties, unclassified token!";
    }
    public void writeToSettings( String token, String input )
    {
        try (OutputStream out = new FileOutputStream(settingsPath))
        {
            prop.setProperty(token, input);
            prop.store(out, null);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void increaseUniqueID()
    {
        String lastUniqueID = readFromProperties("trip.uniqueID");
        int temp = Integer.valueOf(lastUniqueID);
        writeToSettings("trip.uniqueID", Integer.toString(temp+1));
    }
}
