package com.projet4.maru.events;

import com.projet4.maru.model.Reunion;

public class DeleteReunionEvent {

    public Reunion reunion;

    public DeleteReunionEvent(Reunion reunion) { this.reunion = reunion; }

}
