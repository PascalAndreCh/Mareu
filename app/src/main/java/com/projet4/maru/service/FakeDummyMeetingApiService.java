package com.projet4.maru.service;

import com.projet4.maru.model.Meeting;

import java.util.List;

public class FakeDummyMeetingApiService implements FakeMeetingApiService {

    private List<Meeting> mMeetings = FakeDummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) { mMeetings.remove(meeting); }

    /**
     *
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

}
