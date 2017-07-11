package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by Peter on 06/07/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    //private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
    }


        // Get the object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location_text_view);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset_text_view);
        locationOffsetView.setText(locationOffset);

        // Find the TextView in the list_item.xml layout with the place name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        //Format magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Get the place name and set this text on the name TextView
        magnitudeTextView.setText(formattedMagnitude);

        //Set proper background color on magnitude circle
        //Fetch background from TextView which is GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        //Get appropriate background color based on current earthquake
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        //Set color on magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //Create new date object for time in milliseconds
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        //Find the ImageView in the list_view.xml layout with the ID
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        //Format date string
        String formattedDate = formatDate(dateObject);
        //Get the image resource ID and set the image
        dateTextView.setText(formattedDate);

        //Find TextView with ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        //Format time string
        String formattedTime = formatTime(dateObject);
        //Display time of current earthquake in that view
        timeTextView.setText(formattedTime);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;}



    //Return formatted date string
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    //Retrun formatted date string
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    //Return formatted magnitude
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
