package com.example.demo.repositories;

import com.example.demo.models.OurRequestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurRequestRepository extends CrudRepository<OurRequestModel,Long> {
}
