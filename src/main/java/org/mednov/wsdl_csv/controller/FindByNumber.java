package org.mednov.wsdl_csv.controller;

import org.mednov.wsdl_csv.entity.Catalog;
import org.mednov.wsdl_csv.web_service.SearchResult;

public interface FindByNumber {
    public SearchResult findByNumber(String name);
    public void saveToDB(int number, Catalog catalog, String fileNames, String error);
}
