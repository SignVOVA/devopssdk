package editTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import lloydst.aston.triporganiser.R;

public class EditTripEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText et = (EditText)findViewById(R.id.editTripEvents);
        et.setText(getIntent().getExtras().getString("events"));

    }

    public void editTripProceedToPlacesOfInterest(View view){

        final EditText editTripEvents = (EditText) findViewById(R.id.editTripEvents);


        Intent intent = new Intent(this, EditTripPlacesOfInterest.class);
        intent.putExtra("destination", getIntent().getExtras().getString("destination"));
        intent.putExtra("startDate", getIntent().getExtras().getString("startDate"));
        intent.putExtra("endDate", getIntent().getExtras().getString("endDate"));
        intent.putExtra("notes", getIntent().getExtras().getString("notes"));
        intent.putExtra("checklist", getIntent().getExtras().getString("checklist"));
        intent.putExtra("contacts", getIntent().getExtras().getString("contacts"));
        intent.putExtra("events", editTripEvents.getText().toString());
        intent.putExtra("pois", getIntent().getExtras().getString("pois"));

        startActivity(intent);

    }

}
