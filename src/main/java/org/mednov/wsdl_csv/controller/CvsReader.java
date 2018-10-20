package org.mednov.wsdl_csv.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface CvsReader {
    public CompletableFuture<Boolean> load();
    public boolean isLoaded();
    public boolean isStarted();
    public void setStarted(boolean started);
}
