package com.example.homework2.network;

import com.example.homework2.datamodel.City;

import java.util.HashMap;

public interface  CityResultsObserver {
    public void receiveCitiesList(HashMap<String, City> citiesCollection);
    public void onFailure(Throwable cause);
}
