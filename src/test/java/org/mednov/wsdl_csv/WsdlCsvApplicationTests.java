package org.mednov.wsdl_csv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mednov.wsdl_csv.entity.Catalog;
import org.mednov.wsdl_csv.entity.Csv;
import org.mednov.wsdl_csv.entity.Requests;
import org.mednov.wsdl_csv.repository.CatalogRepository;
import org.mednov.wsdl_csv.repository.CsvRepository;
import org.mednov.wsdl_csv.repository.RequestsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsdlCsvApplicationTests {

    Logger logger = LoggerFactory.getLogger(WsdlCsvApplicationTests.class);

    @Autowired
    RequestsRepository requestsRepository;

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    CsvRepository csvRepository;


    @Test
    public void contextLoads() {

    }

    @Test
    public void readFromCatalog(){

    }

    @Test
    public void readFromRequests(){
        List<Requests> requestsList = requestsRepository.findAllByNumber(1);
//        System.out.println(requestsList.get(0).getId());
        for (Requests request :
                requestsList) {
            Catalog catalog = request.getCatalog();
            System.out.println("****" + request.getNumber()+ " "+catalog.getCode());
        }
    }

    @Test
    public void writeNewRequest(){
        Optional<Catalog> catalog = catalogRepository.findById(1);
        Requests request = new Requests();
        Random random = new Random();
        request.setNumber(random.nextInt(20));
        request.setFileNames("1.csv, 5.csv, 3.csv");
        request.setCatalog(catalog.orElse(null));
        requestsRepository.save(request);

    }

    @Test
    public void writeCsvFileNames() {

        String filename = "1.csv";
        Csv csv = new Csv();
        csv.setId(20);
        csv.setFileName(filename);
        csvRepository.save(csv);
        Csv csv1 = new Csv();
        csv1.setId(30);
        csv1.setFileName(filename);
        csvRepository.save(csv1);
        Csv csv2 = new Csv();
        csv2.setId(20);
        csv2.setFileName(filename);
        csvRepository.save(csv2);
        csv.setId(30);
        csv.setFileName("2.csv");
        csvRepository.save(csv);
    }

    @Test
    public void findByNamberTest(){
        List<Csv> csvs = csvRepository.findAllById(30).orElse(null);
        List<Csv> csvs1 = csvRepository.findAllById(20).orElse(null);
    }

    @Test
    public void loggerTest() {
        logger.info("*************** a message");
    }

}
