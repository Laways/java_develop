package com.huang.demo1.service;

public interface SearchItemService {
    Object dataImport();

    Object queryItem(String param, Integer page, Integer rows);
}
