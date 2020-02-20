package com.library1.example.perkins.toolbar2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Lets remove the title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //populate spinner
        setupSimpleSpinner();

        setupCheckBox();
        setupEditText();
    }

    EditText myText;
    private void setupEditText() {
        myText = (EditText)findViewById(R.id.textView);
        myText.setText("New Text");
    }

    CheckBox myCheckbox;
    private void setupCheckBox() {
        myCheckbox = (CheckBox)findViewById(R.id.checkBox);
        myCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String myString = (isChecked)?"Checked":"UN Checked";
                Toast.makeText(MainActivity.this, myString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    Spinner spinner;
    private void setupSimpleSpinner() {
        //create a data adapter to fill above spinner with choices
        //R.array.numbers is arraylist in strings.xml
        //R.layout.spinner_item_simple is just a textview
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,R.layout.spinner_item_simple);

        //get a reference to the spinner
        spinner = (Spinner)findViewById(R.id.spinner);

        //bind the spinner to the datasource managed by adapter
        spinner.setAdapter(adapter);
        //respond when spinner clicked
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public static final int SELECTED_ITEM = 0;

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long rowid) {
                if (arg0.getChildAt(SELECTED_ITEM) != null) {
                    ((TextView) arg0.getChildAt(SELECTED_ITEM)).setTextColor(Color.WHITE);
                    Toast.makeText(MainActivity.this, (String) arg0.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.settings:
                Toast.makeText(this, "settings goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reset:
                doReset();
                return true;
            case R.id.share:
                Toast.makeText(this, "share goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                doHelp();
                return true;
        }

        //all else fails let super handle it
        return super.onOptionsItemSelected(item);
    }

    private void doHelp() {
        // Create out AlterDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This is where the help screen goes");
        //create an anonymous class that is listening for button click
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            /**
             * This method will be invoked when a button in the dialog is clicked.
             * Note the @override
             * Note also that I have to scope the context in the toast below, thats because anonymous classes have a
             * reference to the class they were declared in accessed via Outerclassname.this
             *
             * @param dialog The dialog that received the click.
             * @param which  The button that was clicked (e.g.
             *               {@link DialogInterface#BUTTON1}) or the position
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "clicked OK in Help", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * findViewById(R.id.rel_lay2) is the viewgroup that will host the snackbar
     * If you click the Action button the onclick listener is called and the toast pops.
     */
    private void doReset() {
        Snackbar.make(findViewById(R.id.rel_lay2), "I'm a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Snackbar Action", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
