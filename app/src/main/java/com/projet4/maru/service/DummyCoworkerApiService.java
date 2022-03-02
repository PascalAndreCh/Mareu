package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;

import java.util.List;

public class DummyCoworkerApiService implements CoworkerApiService {

    private List<Coworker> coworkers = DummyCoworkerGenerator.generateCoworkers();

    @Override
    public List<Coworker> getCoworkers() {
        return coworkers;
    }
}
