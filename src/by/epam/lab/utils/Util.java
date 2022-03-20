package by.epam.lab.utils;

import by.epam.lab.beans.Result;

import java.util.List;

public class Util {
    public static void printResultList(List<Result> resultList) {
        for (Result result : resultList) {
            System.out.println(result);
        }
    }
}
