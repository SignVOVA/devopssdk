package createTrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import java.io.File;
import java.io.IOException;

import database.DBHelper;
import lloydst.aston.triporganiser.R;

public class AddChecklistToTrip extends AppCompatActivity {

    ArrayList defaultChecklist;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_checklist_to_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBHelper mydb = DBHelper.getInstance(this);
        defaultChecklist = mydb.getTasks();
        setupList();

    }


    public void addTripProceedToContacts(View view){

        final EditText editTripChecklist = (EditText) findViewById(R.id.editTripChecklist);
        String checklist = null;

        for(int i =0;i<defaultChecklist.size();i++)
        {
            if (checklist != null) {
                checklist = checklist + ", " + defaultChecklist.get(i).toString();
            } else {
                checklist = defaultChecklist.get(i).toString();
            }

        }


        Intent intent = new Intent(this, AddContactsToTrip.class);
        intent.putExtra("destination", getIntent().getExtras().getString("destination"));
        intent.putExtra("startDate", getIntent().getExtras().getString("startDate"));
        intent.putExtra("endDate", getIntent().getExtras().getString("endDate"));
        intent.putExtra("notes", getIntent().getExtras().getString("notes"));
        intent.putExtra("checklist", checklist + editTripChecklist.getText().toString());

        startActivity(intent);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {


    }

    private void setupList() {

        ArrayAdapter<String> itemsAdapter;

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, defaultChecklist);

        listView = (ListView) findViewById(R.id.listChecklistItems);
        listView.setAdapter(itemsAdapter);

    }




}
