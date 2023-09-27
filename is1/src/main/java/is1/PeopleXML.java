package is1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

import is1.models.People;
import is1.models.Person;

public class PeopleXML {

    //It removes the file extension (e.g.: .txt, .xml, etc.)
    public static String removeSufix(String filename){
        int pos = filename.lastIndexOf('.');
      if(pos > -1)
         return filename.substring(0, pos);
      else
         return filename;
    }

    public static void testAll(){
        JAXBContext jaxbContext = null;
        try {

            File[] files = new File("is1\\DataGenerator\\testDataFolder").listFiles();


            // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
            // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{People.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileWriter myWriter;
            //Yeah it's dirty af but it cleans the results file every time we run another round of tests
            try {
                myWriter = new FileWriter("is1\\src\\results\\resultsXML.txt");               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //Tests done in a row should not interfere with each other
            //TODO test results seem to be incorrect due to perhaps saving data in RAM? tests that should be longer are actually shorter than their smaller counterparts
            for(File filename : files){

                String newFilename = removeSufix(filename.getName());

                //TODO insert here the function to convert the file to the People object for marshalling
                //Just for clarifying, if we wanted to have an XML file with several People, we would need another class with a People arrayList
                People currP = new People(1, "testOrg");

                readFile.readPeopleFile(filename.getAbsolutePath(), currP);

                long start = System.currentTimeMillis();

                //TODO insert here the marshalling
                //Works and marshalls all test files and writes the corresponding XML file right
                jaxbMarshaller.marshal(currP, new File("is1\\src\\outputXML\\" + newFilename + "XML.xml"));

                long finish = System.currentTimeMillis();
                long timeElapsedMarshalling = finish - start;

                start = System.currentTimeMillis();

                //TODO insert here unmarshalling
                //Unmarshalls well
                File unmarFile = new File("is1\\src\\outputXML\\" + newFilename + "XML.xml");
                People o = (People) jaxbUnmarshaller.unmarshal(unmarFile);
                
                finish = System.currentTimeMillis();
                long timeElapsedUnMarshalling = finish - start;

                //Write results to file (filename, marshall time, unmarshall time)
                File results = new File("is1\\src\\results\\resultsXML.txt");
                try {
                    results.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                try {
                    myWriter = new FileWriter("is1\\src\\results\\resultsXML.txt", true);
                    //Format is <test file case> <marshalling time> <unmarshalling time>
                    myWriter.write(newFilename + " " + Long.toString(timeElapsedMarshalling) + " " + Long.toString(timeElapsedUnMarshalling) + "\n");
                    myWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    
    }

    public static void testOne(String filename){
        String path = "is1\\DataGenerator\\testDataFolder";
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{People.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileWriter myWriter;
            String newFilename = removeSufix(filename);

            try {
                myWriter = new FileWriter("is1\\src\\results\\" + newFilename + "XML.txt");               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            People currP = new People(1, "testOrg");
            readFile.readPeopleFile(path + "\\" + filename, currP);

            long start = System.currentTimeMillis();

            //TODO insert here the marshalling
            //Works and marshalls all test files and writes the corresponding XML file right
            jaxbMarshaller.marshal(currP, new File("is1\\src\\outputXML\\" + newFilename + "XML.xml"));

            long finish = System.currentTimeMillis();
            long timeElapsedMarshalling = finish - start;

            File unmarFile = new File("is1\\src\\outputXML\\" + newFilename + "XML.xml");
            People o = (People) jaxbUnmarshaller.unmarshal(unmarFile);
            
            finish = System.currentTimeMillis();
            long timeElapsedUnMarshalling = finish - start;

            //Write results to file (filename, marshall time, unmarshall time)
            File results = new File("is1\\src\\results\\" + newFilename + ".txt");
            try {
                results.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            try {
                myWriter = new FileWriter("is1\\src\\results\\" + newFilename + "XML.txt", true);
                //Format is <test file case> <marshalling time> <unmarshalling time>
                myWriter.write(newFilename + " " + Long.toString(timeElapsedMarshalling) + " " + Long.toString(timeElapsedUnMarshalling) + "\n");
                myWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //To test all files in the test case folder, there is cross contamination as the first file marshalled probably saves stuff in the RAM and is slower than it should/the others are faster than they should
        testAll();
        //to test just one so it shows values without errors
        testOne("testData100000.txt");
    }
}


            //Example of Person object creation
            /*
            Person o = new Person();
            o.setId(1);
            o.setName("Banana");
            o.setEmail("hehe@gmail.com");
            o.setBirthDate(ZonedDateTime.parse("2016-10-05T08:20:10+05:30[Asia/Kolkata]"));
            */

            //only here to test people class marshalling, comment or remove later
            //Confirmed the People object is created well
            /*
            People testP = new People(1, "testOrg");
            testP.addPeopleList(new Person(0, "Tiago", "mail@gmail.com"));
            testP.addPeopleList(new Person(1, "Marco", "marco@hotmail.com"));
            testP.addPeopleList(new Person(2, "Tatiana", "tatiana@yahoo.com"));
            System.out.println(testP);
            */
        
            //Works and People object is created sucessfully
            /*
            //TODO only here to test reading the file into the People Object
            People testP = new People(1, "testOrg");
            //TODO has most of the dir but put way to change the text file name while on a loop later
            readFile.readPeopleFile("is1\\DataGenerator\\testDataFolder\\testData50.txt", testP);
            System.out.println(testP);
            */
            
            //Keeps all file names in the testDataFolder in a File array 
            /* 
            File[] files = new File("C:\\Users\\ferre\\Documents\\Uni\\IS\\Assignment1\\is1\\DataGenerator\\testDataFolder").listFiles();
            for(File filename: files){
                System.out.println(filename.getName());
            }
            */