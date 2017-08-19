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

public class AddPlacesOfInterestToTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places_of_interest_to_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addTripProceedToFinalSummary(View view){

        final EditText editTripPOIs = (EditText) findViewById(R.id.editTripPOIs);

        Intent intent = new Intent(this, AddTripFinalSummary.class);
        intent.putExtra("destination", getIntent().getExtras().getString("destination"));
        intent.putExtra("startDate", getIntent().getExtras().getString("startDate"));
        intent.putExtra("endDate", getIntent().getExtras().getString("endDate"));
        intent.putExtra("notes", getIntent().getExtras().getString("notes"));
        intent.putExtra("checklist", getIntent().getExtras().getString("checklist"));
        intent.putExtra("contacts", getIntent().getExtras().getString("contacts"));
        intent.putExtra("events", getIntent().getExtras().getString("events"));
        intent.putExtra("pois", editTripPOIs.getText().toString());
        startActivity(intent);

    }

}
