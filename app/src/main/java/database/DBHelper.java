package database;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public final class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TRIP_TABLE_NAME = "trips";
    public static final String TRIP_COLUMN_ID = "id";
    public static final String TRIPS_COLUMN_DESTINATION = "destination";
    public static final String TRIPS_COLUMN_CHECKLIST = "checklist";
    public static final String TRIPS_COLUMN_START_DATE = "startDate";
    public static final String TRIPS_COLUMN_END_DATE = "endDate";
    public static final String TRIPS_COLUMN_NOTES = "notes";
    public static final String TRIPS_COLUMN_CONTACTS = "contacts";
    public static final String TRIPS_COLUMN_EVENTS = "events";
    public static final String TRIPS_COLUMN_POIS = "pois";

    private HashMap hp;
    private SQLiteDatabase db = null;
    private static DBHelper dbInstance;

    private DBHelper(final Context context, final String name,
                     final int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public static DBHelper getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DBHelper(context.getApplicationContext(),
                    DATABASE_NAME, DATABASE_VERSION);
        }

        return dbInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trips(id integer primary key,destination text,startDate text,endDate text,notes text,checklist text,contacts text,events text,pois text)");
        db.execSQL("create table checklist(id integer primary key,task text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing
    }

    @Override
    public synchronized void close() {
        if (dbInstance != null)
            db.close();
    }

    public boolean insertTask(String task) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        db.insert("checklist", null, contentValues);
        db.close();
        return true;
    }

    public void deleteTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("checklist", "task = ? ", new String[] { task });
    }

    public ArrayList<String> getTasks() {

        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from checklist", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("task")));
            res.moveToNext();
        }

        return array_list;
    }

    public boolean insertTrip(String destination, String startDate, String endDate, String notes, String checklist, String contacts, String events, String pois) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("destination", destination);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        contentValues.put("notes", notes);
        contentValues.put("checklist", checklist);
        contentValues.put("contacts", contacts);
        contentValues.put("events", events);
        contentValues.put("pois", pois);
        db.insert("trips", null, contentValues);
        db.close();
        return true;
    }


    public int numberOfRows(){
        db = getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TRIP_TABLE_NAME);
        return numRows;
    }

    public boolean updateTrip (Integer id, String destination, String startDate, String endDate, String notes, String checklist, String contacts,String events, String pois) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("destination", destination);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        contentValues.put("notes", notes);
        contentValues.put("checklist", checklist);
        contentValues.put("contacts", contacts);
        contentValues.put("events", events);
        contentValues.put("pois", pois);
        db.update("trips", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteTrip (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("trips",
                "destination = ? ",
                new String[] { id });
    }

    public ArrayList<String> getTripDetailInformation(String id) {

        ArrayList<String> array_list = new ArrayList<String>();

        System.out.println(id);
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor res =  db.rawQuery("select * from trips where " + TRIPS_COLUMN_DESTINATION + "=", );
        Cursor res =  db.rawQuery("select * from trips", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            if (res.getString(res.getColumnIndex(TRIPS_COLUMN_DESTINATION)).equals(id)) {
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_DESTINATION)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_START_DATE)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_END_DATE)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_NOTES)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_CHECKLIST)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_CONTACTS)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_EVENTS)));
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_POIS)));
            }
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getUpcomingTripDestinations() throws ParseException {

        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from trips", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String date = null;
            date = res.getString(res.getColumnIndex(TRIPS_COLUMN_START_DATE));
            String todayAsString = new SimpleDateFormat("ddMMyy").format(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
            Date date1 = sdf.parse(date);
            Date date2 = sdf.parse(todayAsString);

            if (date1.compareTo(date2) > 0) {
                array_list.add(res.getString(res.getColumnIndex(TRIPS_COLUMN_DESTINATION)));
            }

            res.moveToNext();
        }
        return array_list;
    }
}