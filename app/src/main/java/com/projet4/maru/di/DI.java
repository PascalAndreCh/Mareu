package com.projet4.maru.di;

import com.projet4.maru.service.DummyMaReuApiService;
import com.projet4.maru.service.MaReuApiService;

public class DI {


    /**
     *
     */
    public static MaReuApiService service = new DummyMaReuApiService();

    /**
     *
     */
    public static MaReuApiService getStartListApiService() {
        return service;
    }

    /**
     *
     * @return
     */
    public static MaReuApiService getNewInstanceApiService () {
        return new DummyMaReuApiService();
    }


}
