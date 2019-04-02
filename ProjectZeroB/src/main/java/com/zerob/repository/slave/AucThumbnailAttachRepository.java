package com.zerob.repository.slave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.zerob.entity.slave.AucThumbnailAttach;

//@Repository
@RepositoryRestResource
public interface AucThumbnailAttachRepository extends JpaRepository<AucThumbnailAttach, Long> {
    
}
