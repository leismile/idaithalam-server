package ch.inss.idaiserver.persistence;

import ch.inss.idaiserver.model.TestDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<TestDao, Integer> {

}
