package createTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import lloydst.aston.triporganiser.R;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract;


public class AddEventsToTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events_to_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addCalendarEvent(View view){
        final EditText editTripEvents = (EditText) findViewById(R.id.editTripEvents);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(Events.TITLE, editTripEvents.getText().toString());
        startActivity(intent);

    }



    public void addTripProceedToPlacesOfInterest(View view){

        final EditText editTripEvents = (EditText) findViewById(R.id.editTripEvents);

        Intent intent = new Intent(this, AddPlacesOfInterestToTrip.class);
        intent.putExtra("destination", getIntent().getExtras().getString("destination"));
        intent.putExtra("startDate", getIntent().getExtras().getString("startDate"));
        intent.putExtra("endDate", getIntent().getExtras().getString("endDate"));
        intent.putExtra("notes", getIntent().getExtras().getString("notes"));
        intent.putExtra("checklist", getIntent().getExtras().getString("checklist"));
        intent.putExtra("contacts", getIntent().getExtras().getString("contacts"));
        intent.putExtra("events", editTripEvents.getText().toString());
        startActivity(intent);

    }

}
