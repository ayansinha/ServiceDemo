package org.service.demo

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.widget.RemoteViews
import org.service.demo.util.LocationPrefs
import org.service.demo.view.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class NHSLocationWidget : AppWidgetProvider() {


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {


        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    /*override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        *//*val title = intent?.getStringExtra("POSTAL_CODE")
        val desc = intent?.getStringExtra("DESCRIPTION")
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val res = AppWidgetManager.getInstance(context)
        val appWidgetIds = res.getAppWidgetIds(ComponentName(context, NHSLocationWidget::class.java))*//*


    }*/

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val locationPref = LocationPrefs(context)
    val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            PendingIntent.getActivity(context, 0, intent, 0)
        }

    //val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views: RemoteViews = RemoteViews(context.packageName, R.layout.n_h_s_location_widget).apply {
        setOnClickPendingIntent(R.id.appwidget_image_location, pendingIntent)
    }

    views.setTextViewText(R.id.appwidget_text_title, locationPref.getPostalCode())
    views.setTextViewText(R.id.appwidget_text_desc, locationPref.getDescription())

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}