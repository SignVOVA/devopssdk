package editDefaultChecklist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.util.ArrayList;

import database.DBHelper;
import editTrip.ViewTripActivity;
import editTrip.ViewTripDetailsActivity;
import lloydst.aston.triporganiser.MainActivity;
import lloydst.aston.triporganiser.R;

public class EditDefaultTripChecklist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList defaultChecklist;
    private ListAdapter todoListAdapter;
    ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_default_trip_checklist);

        DBHelper mydb = DBHelper.getInstance(this);
        defaultChecklist = mydb.getTasks();
        setupList();

        ListView listview = (ListView) findViewById(R.id.listChecklistItems);
        listview.setOnItemClickListener(this);

    }


    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        Intent intent = new Intent(this, EditDefaultTripChecklist.class);
        DBHelper mydb = DBHelper.getInstance(this);
        System.out.println(listView.getItemAtPosition(position).toString());
        mydb.deleteTask(listView.getItemAtPosition(position).toString());
        startActivity(intent);

    }

    private void setupList() {

        ArrayAdapter<String> itemsAdapter;

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, defaultChecklist);

        listView = (ListView) findViewById(R.id.listChecklistItems);
        listView.setAdapter(itemsAdapter);

    }

    public void addTaskToChecklist(View view){

        final EditText editDefaultChecklist = (EditText) findViewById(R.id.editChecklistItem);


        DBHelper mydb;
        mydb = DBHelper.getInstance(this);
        mydb.insertTask(editDefaultChecklist.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}


