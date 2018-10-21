package org.mednov.wsdl_csv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Controller
public class CsvReaderController implements CvsReader {

    Logger logger = LoggerFactory.getLogger(CsvReaderController.class);

    @Value("${app.csvFilesPath}")
    private String csvFiles;


    @Async
    public CompletableFuture<Boolean> load() {

        CompletableFuture<Boolean> future = new CompletableFuture<>();

        try {
            logger.info("the path is "+ csvFiles);
            Thread.sleep(10000);
            future.complete(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
            future.completeExceptionally(e);
        }

        return future;
    }

}
