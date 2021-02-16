package dadosAbertos.DAO;

import dadosAbertos.entidades.Endpoint;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
@Component
public class EndpointDAO {
     EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("dev-PU");
     EntityManager entityManager = entityManagerFactory.createEntityManager();

     public List<Endpoint>listarEndpoints(){
         List<Endpoint> endpoints = null;
         try {
             this.entityManager.getTransaction().begin();
             endpoints = entityManager.createNativeQuery("SELECT  * FROM endpoint").getResultList();
             entityManager.clear();
         }catch (Exception e) {
         System.err.println(e);
            entityManager.close();
         }finally {
             entityManager.close();
             return endpoints;
         }




     }


}
