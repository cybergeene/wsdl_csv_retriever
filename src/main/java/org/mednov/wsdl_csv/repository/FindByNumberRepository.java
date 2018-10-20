package org.mednov.wsdl_csv.repository;

import org.mednov.wsdl_csv.controller.CvsReader;
import org.mednov.wsdl_csv.web_service.FilesFound;
import org.mednov.wsdl_csv.web_service.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class FindByNumberRepository {
    boolean runned;
    CompletableFuture<Boolean> completableFuture;

    @Autowired
    CvsReader cvsReader;

    @PostConstruct
    public void initData() {

    }

    public SearchResult findByNumber(String name) {
        SearchResult searchResult = new SearchResult();

        if(completableFuture==null) {
            completableFuture = cvsReader.load();
            searchResult.setCode("loading started");
            searchResult.setError("Please wait");
        }else{
            if(completableFuture.isDone()){
                searchResult.setCode("done");
                searchResult.setError("");
            }else if(completableFuture.isCompletedExceptionally()){
                searchResult.setCode("error");
                searchResult.setError("load from csv failed");
            }else{
                searchResult.setCode("loading...");
                searchResult.setError("Data is preparing. Try again later");
            }
        }

        return searchResult;
    }
}
