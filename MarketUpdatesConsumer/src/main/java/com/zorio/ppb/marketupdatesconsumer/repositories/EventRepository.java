package com.zorio.ppb.marketupdatesconsumer.repositories;

import com.zorio.ppb.marketupdatesconsumer.entities.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, String> {
}
