package org.mednov.wsdl_csv.repository;

import org.mednov.wsdl_csv.web_service.FilesFound;
import org.mednov.wsdl_csv.web_service.SearchResult;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FindByNumberRepository {

    @PostConstruct
    public void initData() {

    }

    public SearchResult findByNumber(String name) {
        SearchResult searchResult = new SearchResult();
        if(name.equals("1")) {
            List<String> files = Arrays.asList("2.csv", "5.csv", "7.csv");
            searchResult.setCode("00.Result.OK");
            FilesFound filesFound = new FilesFound();
            filesFound.setFileFound(files);
            searchResult.setFileNames(filesFound);
            searchResult.setError("");
        }else{
            searchResult.setCode("01.Result.NotFound");
            searchResult.setError("");
        }

        Assert.notNull(name, "The number must not be null");
        return searchResult;
    }
}
