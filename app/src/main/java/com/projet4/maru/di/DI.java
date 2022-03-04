package com.projet4.maru.di;

import com.projet4.maru.service.FakeDummyMeetingApiService;
import com.projet4.maru.service.FakeMeetingApiService;

public class DI {


    public static FakeMeetingApiService service = new FakeDummyMeetingApiService();

    public static FakeMeetingApiService getFakeMeetingApiService() { return service; }

    public static FakeMeetingApiService getNewInstanceApiService() {
        return new FakeDummyMeetingApiService();
    }

}
