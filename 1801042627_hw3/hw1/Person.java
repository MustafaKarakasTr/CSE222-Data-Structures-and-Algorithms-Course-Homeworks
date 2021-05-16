package mustafa.karakas.hw1;
/**
 * It represents a Person 
 */
public abstract class Person {
    protected String name;
    protected String surname;
    /*protected String e_mail;
    protected String password;*/
    /**
     * Constructs a Person 
     * @param _name name of person
     * @param _surname  surname of person
     */
    public Person(String _name,String _surname){
    
        name = _name;
        surname = _surname;
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        // field comparison
        return (person.name.equals(name) && person.surname.equals(surname) 
                //&& person.e_mail.equals(e_mail) && person.password.equals(password));
        );
    }
    public String toString(){
        return name+" "+surname;
    }
}
