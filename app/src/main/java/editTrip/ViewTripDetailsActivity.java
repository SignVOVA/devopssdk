package editTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

import database.DBHelper;
import lloydst.aston.triporganiser.R;

public class ViewTripDetailsActivity extends AppCompatActivity implements OnItemClickListener {

    ArrayList tripItem;
    String tripDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tripDestination = getIntent().getExtras().getString("tripDestination");

        DBHelper mydb = DBHelper.getInstance(this);

        tripItem = mydb.getTripDetailInformation(tripDestination);

        setupList();

        ListView listview = (ListView) findViewById(R.id.listDetailedTripItem);
        listview.setOnItemClickListener(this);

    }


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        Intent intent = new Intent(this, EditTripActivity.class);
        intent.putExtra("destination", (String) tripItem.get(0));
        intent.putExtra("startDate",(String) tripItem.get(1));
        intent.putExtra("endDate", (String) tripItem.get(2));
        intent.putExtra("notes", (String) tripItem.get(3));
        intent.putExtra("checklist", (String) tripItem.get(4));
        intent.putExtra("contacts", (String) tripItem.get(5));
        intent.putExtra("events", (String) tripItem.get(6));
        intent.putExtra("pois", (String) tripItem.get(7));
        startActivity(intent);

    }

    private void setupList() {

        ArrayAdapter<String> itemsAdapter;

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tripItem);

        ListView listView = (ListView) findViewById(R.id.listDetailedTripItem);
        listView.setAdapter(itemsAdapter);

    }

    public void deleteTripAndReturnToListOfTrips(View view){

        DBHelper mydb = DBHelper.getInstance(this);
        mydb.deleteTrip(tripDestination);

        Intent intent = new Intent(this, ViewTripActivity.class);
        startActivity(intent);

    }

    public void shareTrip(View view) {
        Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) tripItem.get(1));
        i.putExtra(android.content.Intent.EXTRA_TEXT, "I am going to " + (String) tripItem.get(0) + " on " + (String) tripItem.get(1) + " till " + (String) tripItem.get(2));
        startActivity(Intent.createChooser(i,"Share via"));
    }


}
