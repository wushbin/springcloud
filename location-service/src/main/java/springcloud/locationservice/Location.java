package springcloud.locationservice;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class Location {

    private double latitude;
    private double longitube;
    private long id;
    private LocalDateTime timestamp;


    public Location() {}

    public Location(double latitude, double longitube) {
        this.latitude = latitude;
        this.longitube = longitube;
        this.timestamp = LocalDateTime.now();
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    @JsonProperty
    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitube(double longitube) {
        this.longitube = longitube;
    }
    @JsonProperty
    public double getLongitube() {
        return this.longitube;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonProperty
    public long getId() {
        return this.id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @JsonProperty
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
