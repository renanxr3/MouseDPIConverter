package br.com.renaninfo.mousedpiconverter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.renaninfo.mousedpiconverter.components.itemnewconfiguration.NewConfigurationItemAdapter;
import br.com.renaninfo.mousedpiconverter.components.itemnewconfiguration.NewConfigurationItemModel;

public class MainActivity extends Activity {

    private List<String> listItemsCmbDPIs;
    private static NewConfigurationItemAdapter adapter;
    private ArrayList<NewConfigurationItemModel> dataModels;

    private Spinner cmbDPI;
    private EditText edtGameSensitivity;
    private ListView lvNewConfiguration;
    private TextView lblNewConfiguration;
    private TextView lblNewDPI;
    private TextView lblNewGameSensitivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Settings
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Bind variables
        lvNewConfiguration = (ListView) findViewById(R.id.lvNewConfiguration);
        cmbDPI = (Spinner) findViewById(R.id.cmbDPI);
        edtGameSensitivity = (EditText) findViewById(R.id.edtGameSensitivity);
        lblNewConfiguration = (TextView) findViewById(R.id.lblNewConfiguration);
        lblNewDPI = (TextView) findViewById(R.id.lblNewDPI);
        lblNewGameSensitivity = (TextView) findViewById(R.id.lblNewGameSensitivity);


        // Combobox: DPIs
        Set<String> availableDpis = sharedPrefs.getStringSet("configs_dpis", new HashSet<String>());
        listItemsCmbDPIs = new ArrayList<String>();
//        listItemsCmbDPIs.add(getString(R.string.cmb_empty_item));
        for (String dpiItem: availableDpis) {
            listItemsCmbDPIs.add(dpiItem);
        }
        Spinner s = (Spinner) findViewById(R.id.cmbDPI);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listItemsCmbDPIs);
        s.setAdapter(adapter);
        s.setSelection(AdapterView.INVALID_POSITION);

        cmbDPI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(MainActivity.this, "S: " + listItemsCmbDPIs.get(position), Toast.LENGTH_SHORT).show();

                calcNewConfiguration();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(MainActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();

                calcNewConfiguration();
            }

        });
        // --


        // EditText Game Sensitivity
        edtGameSensitivity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(MainActivity.this, edtGameSensitivity.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        // --


        // List View: new configuration
        dataModels = new ArrayList<>();
//        dataModels.add(new NewConfigurationItemModel("800", "1.0"));
//        dataModels.add(new NewConfigurationItemModel("1200", "2.0"));
//        dataModels.add(new NewConfigurationItemModel("1800","10.0"));
//        dataModels.add(new NewConfigurationItemModel("2400", "20.9"));
//        dataModels.add(new NewConfigurationItemModel("3600", "3.0"));

        adapter= new NewConfigurationItemAdapter(dataModels,getApplicationContext());
        lvNewConfiguration.setAdapter(adapter);

        lvNewConfiguration.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NewConfigurationItemModel dataModel= dataModels.get(position);
//                        Snackbar.make(view, "DPI: " + dataModel.getDpi()+"Sensi: " + dataModel.getSensitivity(), Snackbar.LENGTH_LONG).setAction("No action", null).show();
                        Toast.makeText(view.getContext(), "DPI: " + dataModel.getDpi()+" Sensi: " + dataModel.getSensitivity(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // --

        /*
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    // Update UI to reflect text being shared
                }
            }
        }
        */
    }

    private void calcNewConfiguration() {
        Toast.makeText(MainActivity.this, "calcNewConfiguration", Toast.LENGTH_SHORT).show();
        if ((cmbDPI.getSelectedItemPosition()!=AdapterView.INVALID_POSITION) && (!edtGameSensitivity.getText().toString().isEmpty())) {
            lblNewConfiguration.setVisibility(View.VISIBLE);
            lblNewDPI.setVisibility(View.VISIBLE);
            lblNewGameSensitivity.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
//            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.action_about) {
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
//            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.action_share) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link));
            startActivity(Intent.createChooser(share, getString(R.string.share_title)));
        } else if (id == R.id.action_reset) {
            lblNewConfiguration.setVisibility(View.INVISIBLE);
            lblNewDPI.setVisibility(View.INVISIBLE);
            lblNewGameSensitivity.setVisibility(View.INVISIBLE);

            edtGameSensitivity.setText(R.string.empty_string);
            cmbDPI.setSelection(AdapterView.INVALID_POSITION);
        }

        return super.onOptionsItemSelected(item);
    }

}
