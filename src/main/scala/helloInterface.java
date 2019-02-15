import java.rmi.Remote;
import java.rmi.RemoteException;

public interface helloInterface extends Remote {
    public String sendObject(myObject anObject, myObject anObject2) throws RemoteException;
}
