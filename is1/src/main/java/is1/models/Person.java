package is1.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement 
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    int id;

    @XmlElement(namespace = "name")
    String name;

    @XmlElement(namespace = "email")
    String email;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(){

    }

    public Person(int id, String name, String email){
        this();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nPerson{" + 
        "id= " + id
        + ", name= " + name
        + ", email= " + email
        + "}";
    }

    

}
