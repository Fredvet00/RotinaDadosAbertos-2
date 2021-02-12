package dadosAbertos.dto;

public class EndpointDTO {
    private Long id;
    private String endpoint;


    public EndpointDTO() {
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "EndpointDTO{" +
                "id=" + id +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
