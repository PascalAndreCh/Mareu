package com.projet4.maru.di;

import com.projet4.maru.service.FakeDummyReunionApiService;
import com.projet4.maru.service.FakeReunionApiService;

public class DI {


    public static FakeReunionApiService service = new FakeDummyReunionApiService();

    public static FakeReunionApiService getFakeReunionApiService() { return service; }

    public static FakeReunionApiService getNewInstanceApiService() {
        return new FakeDummyReunionApiService();
    }

}
