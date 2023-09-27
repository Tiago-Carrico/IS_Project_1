package is1.models;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class People {

    @XmlAttribute
    int id;

    @XmlElement(namespace = "Org Name")
    String orgName;

    @XmlElement(name = "peopleList")
    private ArrayList<Person> peopleList = null;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setOrgName(String orgName){
        this.orgName = orgName;
    }

    public String getOrgname(){
        return orgName;
    }

    public void setPeopleList(ArrayList<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public void addPeopleList(Person person){
        this.peopleList.add(person);
    }

    public ArrayList<Person> getPeopleList() {
        return this.peopleList;
    }

    public People(){
        peopleList = new ArrayList<Person>();
    }

    public People(int id, String orgName){
        this();
        this.id = id;
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "Person{" + 
        "id= " + id
        + "\nOrg Name= " + orgName
        + "\nEmployees= " + peopleList
        + "}";
    }
}
