package com.projet4.maru.service;

import com.projet4.maru.model.Reunion;

import java.util.List;

public interface FakeReunionApiService {

    List<Reunion> getReunions();

    void createReunion (Reunion reunion);

    void deleteReunion (Reunion reunion);
}
