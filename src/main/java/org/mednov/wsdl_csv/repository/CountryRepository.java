package org.mednov.wsdl_csv.repository;

import org.mednov.wsdl_csv.web_service.Country;
import org.mednov.wsdl_csv.web_service.Currency;
import org.mednov.wsdl_csv.web_service.FilesFound;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {

        List<String> files = Arrays.asList("2.csv", "5.csv", "7.csv");
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46_704_314);
        FilesFound filesFound = new FilesFound();
        filesFound.setFileFound(files);
        spain.setFiles(filesFound);






        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38_186_860);

        countries.put(poland.getName(), poland);

        List<String> files1 = Arrays.asList("7.csv", "8.csv", "1.csv");
        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63_705_000);
//        uk.setFiles(files1);

        countries.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
}
