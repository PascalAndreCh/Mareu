package com.projet4.maru.service;

import com.projet4.maru.model.Meeting;

import java.util.List;

public interface FakeMeetingApiService {

    List<Meeting> getMeetings();

    void createMeeting (Meeting meeting);

    void deleteMeeting (Meeting meeting);
}
