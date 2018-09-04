package com.example.felix.dailybucketlist.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.felix.dailybucketlist.R;

// Implementation der App Widget Funktionalität.
// Das Widget zeigt alle Aufgaben der aktuellen Woche an.
public class BucketListAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Initialisiert RemoteView für das Widget.
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
        Intent intent = new Intent(context, WidgetRemoteViewsService.class);

        // Setzt Adapter für das Widget.
        views.setRemoteAdapter(R.id.widgetListView, intent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Update Funktion
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

