package editTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import database.DBHelper;
import lloydst.aston.triporganiser.R;

public class EditTripSummary extends AppCompatActivity {

    private String destination;
    private String startDate;
    private String endDate;
    private String notes;
    private String checklist;
    private String contacts;
    private String events;
    private String pois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tripDestination = (TextView)findViewById(R.id.tripDestination);
        TextView tripStartDate = (TextView)findViewById(R.id.tripStartDate);
        TextView tripEndDate = (TextView)findViewById(R.id.tripEndDate);
        TextView tripNotes = (TextView)findViewById(R.id.tripNotes);
        TextView tripChecklist = (TextView)findViewById(R.id.tripChecklist);
        TextView tripContacts = (TextView)findViewById(R.id.tripContacts);
        TextView tripEvents = (TextView)findViewById(R.id.tripEvents);
        TextView tripPois = (TextView)findViewById(R.id.tripPOIs);

        destination = getIntent().getExtras().getString("destination");
        startDate = getIntent().getExtras().getString("startDate");
        endDate = getIntent().getExtras().getString("endDate");
        notes =  getIntent().getExtras().getString("notes");
        checklist = getIntent().getExtras().getString("checklist");
        contacts = getIntent().getExtras().getString("contacts");
        events = getIntent().getExtras().getString("events");
        pois = getIntent().getExtras().getString("pois");


        tripDestination.setText(destination);
        tripStartDate.setText(startDate);
        tripEndDate.setText(endDate);
        tripNotes.setText(notes);
        tripChecklist.setText(checklist);
        tripContacts.setText(contacts);
        tripEvents.setText(events);
        tripPois.setText(pois);

    }

    public void returnToListOfTrips(View view){

        DBHelper mydb;
        mydb = DBHelper.getInstance(this);
        mydb.deleteTrip(destination);
        mydb.insertTrip(destination, startDate, endDate, notes, checklist, contacts, events, pois);
        Intent intent = new Intent(this, ViewTripActivity.class);
        startActivity(intent);

    }

}
