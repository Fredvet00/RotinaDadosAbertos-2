package dadosAbertos.web.rest;

import dadosAbertos.DAO.EndpointDAO;
import dadosAbertos.entidades.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EndPointRestController {

    @Autowired
    private EndpointDAO endpointDAO;


    @GetMapping("/endpoints")
    public ResponseEntity<List<Endpoint>> listarEndpoints() throws Exception {
        try {
            List<Endpoint> endPoint = endpointDAO.listarEndpoints();
            System.out.println("Persistindo endpoint no banco: {}" + endPoint);

            return new ResponseEntity<>(endPoint, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

  /*
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
*/
    @GetMapping("/test")
    public void teste(){
        System.out.println("Certo");
    }


}


