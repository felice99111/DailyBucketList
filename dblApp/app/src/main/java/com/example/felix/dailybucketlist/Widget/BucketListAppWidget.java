package com.example.felix.dailybucketlist.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.felix.dailybucketlist.R;
import com.example.felix.dailybucketlist.Widget.WidgetRemoteViewsService;

//Implementation der App Widget Funktionalität
// Das Widget zeigt alle Aufgaben der aktuellen Woche an
public class BucketListAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Initialisiere RemoteView für das Widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
        Intent intent = new Intent(context, WidgetRemoteViewsService.class);

        // Setze Adapter für das Widget
        views.setRemoteAdapter(R.id.widgetListView, intent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Update Funktion für alle Widget
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

