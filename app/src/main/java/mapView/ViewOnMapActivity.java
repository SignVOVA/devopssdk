package mapView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import lloydst.aston.triporganiser.R;


public class ViewOnMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_on_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }



    public void viewPreviousTripsOnMap(View view){
        Intent intent = new Intent(this, ViewPastTripOnMapsActivity.class);
        // Get the cursor, positioned to the corresponding row in the result set
        startActivity(intent);
    }

    public void viewFutureTripsOnMap(View view){
        Intent intent = new Intent(this, ViewFutureTripOnMapActivity.class);
        // Get the cursor, positioned to the corresponding row in the result set
        startActivity(intent);
    }

}
