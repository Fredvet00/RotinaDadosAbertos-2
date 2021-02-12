package dadosAbertos.repository;
import com.sun.istack.internal.NotNull;
import dadosAbertos.entidades.EndPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndpointRepository extends JpaRepository<EndPoint,Long>{

    List<EndPoint> findEndPointById(@NotNull Long id);





}
