package dadosAbertos.service;

import dadosAbertos.entidades.Endpoint;
import dadosAbertos.repository.EndpointRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EndpointService{

    final private EndpointRepository endpointRepository ;
    public EndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }


    public List<Endpoint> endpoints(){
        List<Endpoint> resultado = endpointRepository.findAll();
        System.out.println(resultado);
        return resultado;
    }
}
