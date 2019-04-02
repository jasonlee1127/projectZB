package com.zerob.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.zerob.entity.primary.AucAttach;

//@Repository
@RepositoryRestResource
public interface AucAttachRepository extends JpaRepository<AucAttach, Long> {

//    @RestResource(path = "name")
//    public List<AucAttach> findByVrecordid(@Param("name") String v_recordid);
    
}
