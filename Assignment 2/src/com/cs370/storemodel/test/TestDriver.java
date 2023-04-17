package com.cs370.storemodel.test;

import com.cs370.storemodel.model.StoreModelService;


public class TestDriver {
    public static void main(String[] args) {

        StoreModelService storeModelService = StoreModelService.getInstance();
        storeModelService.processCommandFile(args[0]);
    }
}
