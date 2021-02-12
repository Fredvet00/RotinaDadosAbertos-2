package dadosAbertos.web.rest;

import dadosAbertos.entidades.EndPoint;
import dadosAbertos.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
public class EndPointRestController {
    @Autowired
    private final EndpointRepository endpointRepository;

    public EndPointRestController(EndpointRepository endpointRepository) {

        this.endpointRepository = endpointRepository;
    }


    @PostMapping("/endpoint")
    public ResponseEntity<EndPoint> persistirEndpoint(@RequestBody EndPoint endpoint) throws Exception {
        try {
            System.out.println("Persistindo endpoint no banco: {}" + endpoint);
            EndPoint endpointSave = endpointRepository.save(endpoint);
            return new ResponseEntity<>(endpointSave, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/endpoints")
    public ResponseEntity<List> persistirEndpoints(@RequestBody List<EndPoint> endpointList) throws Exception {
        try {
            System.out.println("Persistindo lista de endpoints no banco: {}" + endpointList);
            List<EndPoint> endpointListSave = endpointRepository.save(endpointList);
            return new ResponseEntity<>(endpointListSave, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}


