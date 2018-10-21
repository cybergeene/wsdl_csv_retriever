package org.mednov.wsdl_csv.repository;

import org.mednov.wsdl_csv.entity.Csv;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CsvRepository extends CrudRepository<Csv, Integer> {
    Optional<List<Csv>> findAllById(Integer id);
}
