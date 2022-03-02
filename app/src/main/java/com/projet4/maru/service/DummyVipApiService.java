package com.projet4.maru.service;

import com.projet4.maru.model.Vip;

import java.util.List;

public class DummyVipApiService implements VipApiService {

    private List<Vip> vips = DummyVipGenerator.generateVips();

    @Override
    public List<Vip> getVips() {
        return vips;
    }

}

