package lloydst.aston.triporganiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import createTrip.AddTripActivity;
import database.DBHelper;
import editDefaultChecklist.EditDefaultTripChecklist;
import editTrip.ViewTripActivity;
import mapView.ViewFutureTripOnMapActivity;
import mapView.ViewOnMapActivity;
import mapView.ViewPastTripOnMapsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBHelper mydb = DBHelper.getInstance(this);

    }

    public void addTripButton(View view){

        Intent intent = new Intent(this, AddTripActivity.class);
        startActivity(intent);

    }

    public void viewTripButton(View view){

        Intent intent = new Intent(this, ViewTripActivity.class);
        startActivity(intent);

    }

    public void viewOnPastTripsOnMapButton(View view){

        Intent intent = new Intent(this, ViewPastTripOnMapsActivity.class);
        startActivity(intent);

    }

    public void viewOnFutureTripsOnMapButton(View view){

        Intent intent = new Intent(this, ViewFutureTripOnMapActivity.class);
        startActivity(intent);

    }

    public void editDefaultChecklistButton(View view){

        Intent intent = new Intent(this, EditDefaultTripChecklist.class);
        startActivity(intent);

    }

}
