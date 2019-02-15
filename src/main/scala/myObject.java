import java.io.Serializable;

//Just a simple object to hold a value so I can compare them later
//I make it serializable so I can send it through the internet
public class myObject  implements Serializable {
    int value;

    myObject(int value){
        this.value = value;
    }

    int getValue(){
        return value;
    }
}
