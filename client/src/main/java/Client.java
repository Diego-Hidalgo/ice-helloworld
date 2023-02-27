public class Client
{

    private static java.io.BufferedWriter bw;
    private static java.io.BufferedReader br;

    public static void main(String[] args) throws java.io.IOException
    {
        bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        java.util.List<String> extraArgs = new java.util.ArrayList<>();
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "client.cfg", extraArgs))
        {
            Demo.ResponsePrx twoway = Demo.ResponsePrx.checkedCast(
                    communicator.propertyToProxy("Responder.Proxy")).ice_twoway().ice_secure(false);
            Demo.ResponsePrx responder = twoway.ice_twoway();
            if(responder == null)
            {
                throw new Error("Invalid proxy");
            }
            try {
                String info = System.getProperty("user.name") + ":" + java.net.InetAddress.getLocalHost().getHostName();
                bw.write(info + "-input: ");
                bw.flush();
                String msg = br.readLine();
                while(!msg.equalsIgnoreCase("exit")) {
                    bw.write("SERVER-OUTPUT: " + responder.giveResponse(info, msg) + "\n");
                    bw.write(info + "-INPUT: ");
                    bw.flush();
                    msg = br.readLine();
                }
            } catch (java.net.UnknownHostException e) {
                bw.write("Unable to get hostname");
                bw.flush();
            }
            bw.close();
            br.close();
        }
    }

}
