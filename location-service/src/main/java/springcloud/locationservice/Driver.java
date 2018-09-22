package springcloud.locationservice;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Driver {
    private long id;
    private String firstName;
    private String lastName;

    public Driver() {

    }

    public Driver(String firstname, String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
    }


    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    @JsonProperty
    public String getFirstname() {
        return this.firstName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    @JsonProperty
    public String getLastName() {
        return this.lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }


}
