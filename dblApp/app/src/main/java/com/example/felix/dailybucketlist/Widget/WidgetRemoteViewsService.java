package com.example.felix.dailybucketlist.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

// RemoteViewService startet RemoteViewFactory.
public class WidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewsFactory(this.getApplicationContext());
    }
}

