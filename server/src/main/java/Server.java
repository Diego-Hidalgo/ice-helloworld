
public class Server
{
    public static void main(String[] args)
    {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "server.cfg", extraArgs))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("SimpleAdapter");
            com.zeroc.Ice.Object object1 = new PrinterI();
            com.zeroc.Ice.Object object2 = new ResponderI();
            adapter.add(object1, com.zeroc.Ice.Util.stringToIdentity("SimplePrinter"));
            adapter.add(object2, com.zeroc.Ice.Util.stringToIdentity("SimpleResponder"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}