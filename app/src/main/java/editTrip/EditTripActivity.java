package editTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import createTrip.AddChecklistToTrip;
import lloydst.aston.triporganiser.R;

public class EditTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText et = (EditText)findViewById(R.id.editTripDestination);
        et.setText(getIntent().getExtras().getString("destination"));
        EditText et1 = (EditText)findViewById(R.id.editTripStartDate);
        et1.setText(getIntent().getExtras().getString("startDate"));
        EditText et2 = (EditText)findViewById(R.id.editTripEndDate);
        et2.setText(getIntent().getExtras().getString("endDate"));
        EditText et3 = (EditText)findViewById(R.id.editTripNotes);
        et3.setText(getIntent().getExtras().getString("notes"));

    }

    public void editTripProceedToCheckList(View view){

        final EditText editTripDestination = (EditText) findViewById(R.id.editTripDestination);
        final EditText editTripStartDate = (EditText) findViewById(R.id.editTripStartDate);
        final EditText editTripEndDate = (EditText) findViewById(R.id.editTripEndDate);
        final EditText editTripNotes = (EditText) findViewById(R.id.editTripNotes);


        Intent intent = new Intent(this, EditTripChecklist.class);
        intent.putExtra("destination", editTripDestination.getText().toString());
        intent.putExtra("startDate", editTripStartDate.getText().toString());
        intent.putExtra("endDate", editTripEndDate.getText().toString());
        intent.putExtra("notes", editTripNotes.getText().toString());
        intent.putExtra("checklist", getIntent().getExtras().getString("checklist"));
        intent.putExtra("contacts", getIntent().getExtras().getString("contacts"));
        intent.putExtra("events", getIntent().getExtras().getString("events"));
        intent.putExtra("pois", getIntent().getExtras().getString("pois"));

        startActivity(intent);

    }

}
