package is1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import is1.protomodel.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import is1.models.People;
import is1.models.Person;
import is1.protomodel.*;

public class PeopleProto {
        //It removes the file extension (e.g.: .txt, .xml, etc.)
    public static String removeSufix(String filename){
        int pos = filename.lastIndexOf('.');
      if(pos > -1)
         return filename.substring(0, pos);
      else
         return filename;
    }

    public static void testAll(){

        File[] files = new File("is1\\DataGenerator\\testDataFolder").listFiles();


        //TODO add proto buffers encoding here


        FileWriter myWriter;
        //Yeah it's dirty af but it cleans the results file every time we run another round of tests
        try {
            myWriter = new FileWriter("is1\\src\\results\\resultsPROTO.txt");               
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Tests done in a row should not interfere with each other
        //TODO test results seem to be incorrect due to perhaps saving data in RAM? tests that should be longer are actually shorter than their smaller counterparts
        for(File filename : files){

            String newFilename = removeSufix(filename.getName());

            //TODO insert here the function to convert the file to the People object for marshalling
            //Just for clarifying, if we wanted to have an XML/Proto file with several People, we would need another class with a People arrayList
            People currP = new People(1, "testOrg");

            readFile.readPeopleFile(filename.getAbsolutePath(), currP);

            ArrayList<Person> personListTemp = new ArrayList<Person>();
            personListTemp = currP.getPeopleList();

            ArrayList<is1.protomodel.Person> personListProtoTemp = new ArrayList<is1.protomodel.Person>();

            String protoFilePath = "is1\\src\\outputProto\\" + newFilename + "PROTO.proto";

            long start = System.currentTimeMillis();

            //Proto Marshall files
            //Using examples we gotta convert the Person objects to proto and then do the People proto object

            for(Person p: personListTemp){
                is1.protomodel.Person personTemp = is1.protomodel.Person.newBuilder().setId(p.getId()).setName(p.getName()).setEmail(p.getEmail()).build();
                personListProtoTemp.add(personTemp);
            }

            //Proto serialization
            is1.protomodel.People peopleProto = is1.protomodel.People.newBuilder().setId(1).setOrgName("testOrg").addAllPeopleList(personListProtoTemp).build();

            //Proto saving to file
            try {
                FileOutputStream fos;
                fos = new FileOutputStream(protoFilePath);
                peopleProto.writeTo(fos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            long timeElapsedMarshalling = finish - start;

            start = System.currentTimeMillis();

            //TODO insert here unmarshalling
            //File unmarFile = new File("is1\\src\\outputXML\\" + newFilename + "XML.xml");
            
            try {
                is1.protomodel.People protoDeserial = is1.protomodel.People.newBuilder().mergeFrom(Files.newInputStream(Paths.get(protoFilePath))).build();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            finish = System.currentTimeMillis();
            long timeElapsedUnMarshalling = finish - start;

            

            //Write results to file (filename, marshall time, unmarshall time)
            File results = new File("is1\\src\\results\\resultsPROTO.txt");
            try {
                results.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            try {
                myWriter = new FileWriter("is1\\src\\results\\resultsPROTO.txt", true);
                //Format is <test file case> <marshalling time> <unmarshalling time>
                myWriter.write(newFilename + " " + Long.toString(timeElapsedMarshalling) + " " + Long.toString(timeElapsedUnMarshalling) + "\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    
    }

    public static void testOne(String filename) throws JAXBException{
        String path = "is1\\DataGenerator\\testDataFolder";
        JAXBContext jaxbContext = null;
        FileWriter myWriter;
        String newFilename = removeSufix(filename);
        String protoFilePath = "is1\\src\\outputProto\\" + newFilename + "PROTO.proto";

        try {
            myWriter = new FileWriter("is1\\src\\results\\" + newFilename + "PROTO.txt");               
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        People currP = new People(1, "testOrg");
        readFile.readPeopleFile(path + "\\" + filename, currP);

        //TODO insert here the marshalling
        //Works and marshalls all test files and writes the corresponding XML file right

        ArrayList<Person> personListTemp = new ArrayList<Person>();
        personListTemp = currP.getPeopleList();
        ArrayList<is1.protomodel.Person> personListProtoTemp = new ArrayList<is1.protomodel.Person>();

        long start = System.currentTimeMillis();

        for(Person p: personListTemp){
            is1.protomodel.Person personTemp = is1.protomodel.Person.newBuilder().setId(p.getId()).setName(p.getName()).setEmail(p.getEmail()).build();
            personListProtoTemp.add(personTemp);
        }

        is1.protomodel.People peopleProto = is1.protomodel.People.newBuilder().setId(1).setOrgName("testOrg").addAllPeopleList(personListProtoTemp).build();

        try {
            FileOutputStream fos;
            fos = new FileOutputStream(protoFilePath);
            peopleProto.writeTo(fos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        long finish = System.currentTimeMillis();
        long timeElapsedMarshalling = finish - start;

        start = System.currentTimeMillis();
        

        try {
            is1.protomodel.People protoDeserial = is1.protomodel.People.newBuilder().mergeFrom(Files.newInputStream(Paths.get(protoFilePath))).build();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finish = System.currentTimeMillis();
        long timeElapsedUnMarshalling = finish - start;

        //Write results to file (filename, marshall time, unmarshall time)
        File results = new File("is1\\src\\results\\" + newFilename + "PROTO.txt");
        try {
            results.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            myWriter = new FileWriter("is1\\src\\results\\" + newFilename + "PROTO.txt", true);
            //Format is <test file case> <marshalling time> <unmarshalling time>
            myWriter.write(newFilename + " " + Long.toString(timeElapsedMarshalling) + " " + Long.toString(timeElapsedUnMarshalling) + "\n");
            myWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //To test all files in the test case folder, there is cross contamination as the first file marshalled probably saves stuff in the RAM and is slower than it should/the others are faster than they should
        testAll();
        //to test just one so it shows values without errors
        try {
            testOne("testData100000.txt");
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
