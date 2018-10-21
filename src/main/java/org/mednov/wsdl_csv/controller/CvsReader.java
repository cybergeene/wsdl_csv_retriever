package org.mednov.wsdl_csv.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface CvsReader {
    public CompletableFuture<Boolean> load();
    public String getStatus();
}
