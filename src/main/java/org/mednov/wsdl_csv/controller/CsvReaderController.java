package org.mednov.wsdl_csv.controller;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.mednov.wsdl_csv.entity.Csv;
import org.mednov.wsdl_csv.repository.CsvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CsvReaderController implements CvsReader {

    @Autowired
    CsvRepository csvRepository;

    private String status;

    Logger logger = LoggerFactory.getLogger(CsvReaderController.class);

    @Value("${app.csvFilesPath}")
    private String csvFiles;


    @Async
    public CompletableFuture<Boolean> load() {

        CompletableFuture<Boolean> future = new CompletableFuture<>();

        List<File> files = null;
        try (Stream<Path> paths = Files.walk(Paths.get(csvFiles))) {
            files = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            future.completeExceptionally(e);
        }

        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(settings);
        int fileCnt = 0;
        for (File file :
                files) {
            fileCnt++;
            String fileName = file.getName();
            status = "Start parsing " + fileName;
            logger.info(status);
//          List<String[]> allRows = null;

//          allRows = parser.parseAll(new InputStreamReader(new FileInputStream(file)));
            parser.beginParsing(file);//parseAll(new InputStreamReader(new FileInputStream(file)));
            String[] row;
            status = "Parsing rows in file " + fileName
                    + " ( " + fileCnt + " of " + files.size() + " )";
            while ((row = parser.parseNext()) != null) {
                List<Csv> csvs = new ArrayList<>();
                for (int i = 0; i < row.length; i++) {
                    Integer number = Integer.parseInt(row[i]);
                    Csv csvEntity = new Csv();
                    csvEntity.setId(number);
                    csvEntity.setFileName(fileName);
                    csvs.add(csvEntity);
                }
                csvRepository.saveAll(csvs);
            }
        }
        status = "All Done";
        logger.info(status);
        future.complete(true);
        return future;
    }


    public String getStatus() {
        return status;
    }

}
