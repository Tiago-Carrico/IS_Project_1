package is1;

import java.io.File;

import is1.models.People;
import jakarta.xml.bind.JAXBException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //Hotkey to run is ctrl+alt+k

        String file = "testData5000000.txt";

        try {
            PeopleProto.testOne(file);
            PeopleXML.testOne(file);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
