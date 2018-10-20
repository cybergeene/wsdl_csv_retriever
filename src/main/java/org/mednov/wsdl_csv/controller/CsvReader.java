package org.mednov.wsdl_csv.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Controller
public class CsvReader implements CvsReader {
    boolean loaded = false;
    boolean started = false;
    int counter = 0;

    @Async
    public CompletableFuture<Boolean> load() {

//        Future<Boolean> fut = new AsyncResult<>(false);
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        try {
            Thread.sleep(10000);
            future.complete(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
            future.completeExceptionally(e);
        }

        return future;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
