package is1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import is1.models.*;

public class readFile {
    public static void readPeopleFile(String path, People ppl){

        try{
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);
            String currentPerson = myReader.nextLine();
            int elementNumber = 0;

            try{
                elementNumber = Integer.parseInt(currentPerson);
            }catch (NumberFormatException ex){
                ex.printStackTrace();
            }

            for(int i = 0; i < elementNumber; i++){
                Person ps = new Person();
                currentPerson = myReader.nextLine();
                String[] currP = currentPerson.split(";", 0);
                ps.setId(Integer.parseInt(currP[0]));
                ps.setName(currP[1]);
                ps.setEmail(currP[2]);
                ppl.addPeopleList(ps);
            }

            myReader.close();
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
