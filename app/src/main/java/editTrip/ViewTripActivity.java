package editTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.ParseException;
import android.database.Cursor;
import android.content.ContentResolver;


import java.util.ArrayList;

import database.DBHelper;
import lloydst.aston.triporganiser.R;

public class ViewTripActivity extends AppCompatActivity implements OnItemClickListener {

    ArrayList futureTripItems;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBHelper mydb = DBHelper.getInstance(this);

        try {
            futureTripItems = mydb.getUpcomingTripDestinations();
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

        setupList();

        ListView listview = (ListView) findViewById(R.id.listPlannedTripItems);
        listview.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        Intent intent = new Intent(this, ViewTripDetailsActivity.class);
        // Get the cursor, positioned to the corresponding row in the result set
        intent.putExtra("tripDestination", listView.getItemAtPosition(position).toString());
        startActivity(intent);

    }

    private void setupList() {

        ArrayAdapter<String> itemsAdapter;

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, futureTripItems);

        listView = (ListView) findViewById(R.id.listPlannedTripItems);
        listView.setAdapter(itemsAdapter);

    }

}
