package Project.Singleton;

import Project.File.FileFactory.ReaderFactory;
import Project.File.FileType.FileType;
import Project.Person.Agent;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class AgentSingleton
{
    private static Object syncLock = new Object();
    private static volatile AgentSingleton Murdock;
    private static List<Agent> AgentList = new ArrayList<>();

    private AgentSingleton()
    {
        AgentList = ReaderFactory.readFile(FileType.JSON).readAgentFromFile();
    }
    public static List getAgentList()
    {
        if (Murdock == null)
        {
            synchronized (syncLock)
            {
                if (Murdock == null)
                {
                    Murdock = new AgentSingleton();
                }
            }
        }
        return AgentList;
    }
}
