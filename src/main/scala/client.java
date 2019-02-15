import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//RUN THE SERVER FIRST. YOU'LL GET AN ERROR OTHERWISE.
public class client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        //Once I start the server, I run this class next, which is the client.
        //The client will search for the port number and name specified by my server. In this case, the port number is
        //5678 and the name "hello"
        helloInterface service = (helloInterface) Naming.lookup("rmi://localhost:5678/hello");

        //I am creating two objects here and making the second point to the first.
        myObject a = new myObject(1);
        myObject b = a;

        //Now the client send both objects to the server method sendObject which returns a string with the answer
        //to the question does the Java RMI hold the referential integrity property.
        String answer = service.sendObject(a, b);
        System.out.println(answer);
    }
}
