package is1;

import java.io.File;

import is1.models.People;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //TODO remove later, for reading file testing purposes
        People testP = new People(1, "testOrg");

        //readFile.readPeopleFile("C:\\Users\\ferre\\Documents\\Uni\\IS\\Assignment1\\is1\\DataGenerator\\testDataFolder\\testData50.txt", testP);

        File[] files = new File("C:\\Users\\ferre\\Documents\\Uni\\IS\\Assignment1\\is1\\DataGenerator\\testDataFolder").listFiles();
        for(File filename: files){
            System.out.println(filename.getName());
        }
    }
}
