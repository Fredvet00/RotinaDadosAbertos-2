package dadosAbertos.entidades;


import javax.persistence.*;

@Entity
@Table (name="endpoint")
public class EndPoint {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "endpoint_value")
    private String endpoint;



    public EndPoint() {

    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
