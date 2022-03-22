package com.projet4.maru.di;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;
import com.projet4.maru.service.DummyMaReuApiService;
import com.projet4.maru.service.MaReuApiService;

import java.util.List;

public class DI {


    public static MaReuApiService service = new DummyMaReuApiService();

    public static MaReuApiService getStartListApiService() { return service; }


}
