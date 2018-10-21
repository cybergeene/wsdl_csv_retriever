package org.mednov.wsdl_csv.controller;

import org.mednov.wsdl_csv.controller.CvsReader;
import org.mednov.wsdl_csv.entity.Catalog;
import org.mednov.wsdl_csv.entity.Csv;
import org.mednov.wsdl_csv.entity.Requests;
import org.mednov.wsdl_csv.repository.CatalogRepository;
import org.mednov.wsdl_csv.repository.CsvRepository;
import org.mednov.wsdl_csv.repository.RequestsRepository;
import org.mednov.wsdl_csv.web_service.FilesFound;
import org.mednov.wsdl_csv.web_service.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
public class FindByNumberController implements FindByNumber{
    CompletableFuture<Boolean> completableFuture;

    @Autowired
    CvsReader cvsReader;

    @Autowired
    CsvRepository csvRepository;

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    RequestsRepository requestsRepository;

    @PostConstruct
    public void initData() {

    }

    public SearchResult findByNumber(String name) {
        SearchResult searchResult = new SearchResult();

        Catalog catalogOk = catalogRepository.findById(1).orElse(null);
        Catalog catalogNotFound = catalogRepository.findById(2).orElse(null);
        Catalog catalogError = catalogRepository.findById(3).orElse(null);
        String error = "";

        try {
            int number = Integer.parseInt(name);

        }
        catch(NumberFormatException e){
            error = "Number to find should be integer value";
            searchResult.setCode(catalogError.getCode());
            searchResult.setError(error);
            saveToDB(0, catalogError, "", error);
            return searchResult;
        }
        int number = Integer.parseInt(name);

        if(completableFuture==null) {
            completableFuture = cvsReader.load();
            error = "Parsing from csv started, please wait";
            searchResult.setCode(catalogError.getCode());
            searchResult.setError(error);
            saveToDB(number, catalogError, "", error);
        }else{
            if(completableFuture.isDone()){
                List<Csv> csvs = csvRepository.findAllById(number).orElse(null);

                if(csvs == null){
                    searchResult.setCode(catalogNotFound.getCode());
                    searchResult.setError("");
                    saveToDB(number, catalogNotFound, "", "");
                }else{
                    String fileNames = csvs.stream()
                            .map(Csv::getFileName)
                            .reduce((s1, s2) -> s1 + ", " + s2).orElse(null);
                    List<String> filenNamesList = csvs.stream()
                            .map(Csv::getFileName)
                            .collect(Collectors.toList());
                    searchResult.setCode(catalogOk.getCode());
                    FilesFound filesFound = new FilesFound();
                    filesFound.setFileFound(filenNamesList);
                    searchResult.setFileNames(filesFound);
                    searchResult.setError("");
                    saveToDB(number, catalogOk, fileNames, "");
                }
            }else if(completableFuture.isCompletedExceptionally()){
                error = "Load from csv failed. Error sended to system administrator";
                searchResult.setCode(catalogError.getCode());
                searchResult.setError("Load from csv failed.");
                saveToDB(0, catalogError, "", error);
            }else{
                error = cvsReader.getStatus();
                searchResult.setCode(catalogError.getCode());
                searchResult.setError(error);
                saveToDB(0, catalogError, "", error);
            }
        }

        return searchResult;
    }

    @Override
    public void saveToDB(int number, Catalog catalog, String fileNames, String error) {
        Requests request = new Requests();
        request.setNumber(number);
        request.setCatalog(catalog);
        request.setError(error);
        request.setFileNames(fileNames);
        requestsRepository.save(request);
    }
}
