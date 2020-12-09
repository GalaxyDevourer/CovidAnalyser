package controllers;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import models.utils.other.SaveData;

import java.io.IOException;

public interface Controller {
    void loadData (SaveData data) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;
}
