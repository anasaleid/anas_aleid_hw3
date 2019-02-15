import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class server  extends UnicastRemoteObject implements helloInterface {
    public server() throws RemoteException {

    }

    @Override
    public String sendObject(myObject a, myObject b) throws RemoteException {

        Config configFactory = ConfigFactory.load("config.conf");
        //Once the client connects and sends the objects using the sendObject method, this is were they will be
        // received by the server.
        //I then change each value once attempting to make the values for each object not equal
        b.value = configFactory.getInt("jdbc.aSecondValue");
        a.value = configFactory.getInt("jdbc.bSecondValue");;
        //This conditional will check whether or not they values are still going to be equal.
        //They will be equal since both objects still point to the same instance even after being sent from
        // one JVM to another
        if (a.value == b.value) {
            return "This is the server: Both objects point to the same instance. Java RMI framework holds the referential integrity property";
        } else {
            return "This is the server: The objects do NOT point to the same instance";
        }

    }

    public static void main(String[] args) throws RemoteException {

        Config configFactory = ConfigFactory.load("config.conf");
        //Starting up the server on port 5678. This is just a random porn. If there
        // are issues with this port, you can just change the value. Once started, the server will stall until
        //a client has established a connecting on the same port.
        Registry regi = LocateRegistry.createRegistry(configFactory.getInt("jdbc.port"));
        regi.rebind("hello", new server());
    }
}


