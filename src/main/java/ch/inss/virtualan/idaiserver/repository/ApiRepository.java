package ch.inss.virtualan.idaiserver.repository;

import ch.inss.virtualan.idaiserver.model.ApiKeyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApiRepository extends CrudRepository<ApiKeyDAO,Long> {
    
    ApiKeyDAO findByUserid(String userid);

    @Override
    @Transactional(timeout = 10)
    List<ApiKeyDAO> findAll();

}

