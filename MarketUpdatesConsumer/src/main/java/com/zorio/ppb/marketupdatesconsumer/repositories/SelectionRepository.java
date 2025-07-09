package com.zorio.ppb.marketupdatesconsumer.repositories;

import com.zorio.ppb.marketupdatesconsumer.entities.SelectionEntity;
import com.zorio.ppb.marketupdatesconsumer.entities.primary_keys.SelectionPK;
import org.springframework.data.repository.CrudRepository;

public interface SelectionRepository extends CrudRepository<SelectionEntity, SelectionPK> {

}
