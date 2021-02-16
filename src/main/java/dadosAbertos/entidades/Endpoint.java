package dadosAbertos.entidades;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
@Transactional
@Entity
@Table (name="endpoint")
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_sequence")
    @SequenceGenerator(name="id_sequence",sequenceName ="ID_SEQ")
    private Long id;

    @Column(name = "endpoint_value")
    private String endpoint;

    public Endpoint() {
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
}
