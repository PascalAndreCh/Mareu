package com.projet4.maru.service;

import com.projet4.maru.model.Reunion;

import java.util.List;

public class FakeDummyReunionApiService  implements FakeReunionApiService {

    private List<Reunion> reunions = FakeDummyReunionGenerator.generateReunions();

    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) { reunions.remove(reunion); }

    /**
     *
     * @param reunion
     */
    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }

}
