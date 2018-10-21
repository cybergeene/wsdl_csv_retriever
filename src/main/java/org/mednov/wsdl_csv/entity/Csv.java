package org.mednov.wsdl_csv.entity;

import javax.persistence.*;

@Table(name = "CSV")
@IdClass(CsvCompositeKey.class)
@Entity
public class Csv {
    @Id
    @Column(name = "ID")
    private int id;

    @Id
    @Column(name = "FILE_NAME")
    private String fileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
