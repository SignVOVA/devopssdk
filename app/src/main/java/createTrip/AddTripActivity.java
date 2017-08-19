package createTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import database.DBHelper;
import lloydst.aston.triporganiser.R;

public class AddTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addTripProceedToCheckList(View view){


        final EditText editTripDestination = (EditText) findViewById(R.id.editTripDestination);
        final EditText editTripStartDate = (EditText) findViewById(R.id.editTripStartDate);
        final EditText editTripEndDate = (EditText) findViewById(R.id.editTripEndDate);
        final EditText editTripNotes = (EditText) findViewById(R.id.editTripNotes);

        // DBHelper mydb;
        // mydb = DBHelper.getInstance(this);
        // mydb.insertTrip(editTripDestination.getText().toString(), editTripStartDate.getText().toString(),editTripEndDate.getText().toString(),editTripNotes.getText().toString(),null,null,null);

        Intent intent = new Intent(this, AddChecklistToTrip.class);
        intent.putExtra("destination", editTripDestination.getText().toString());
        intent.putExtra("startDate", editTripStartDate.getText().toString());
        intent.putExtra("endDate", editTripEndDate.getText().toString());
        intent.putExtra("notes", editTripNotes.getText().toString());


        startActivity(intent);

    }

}
