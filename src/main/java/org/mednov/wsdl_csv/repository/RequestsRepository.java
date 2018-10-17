package org.mednov.wsdl_csv.repository;

import org.mednov.wsdl_csv.entity.Requests;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestsRepository extends CrudRepository<Requests, Integer> {
    List<Requests> findAllByNumber(Integer number);
}
