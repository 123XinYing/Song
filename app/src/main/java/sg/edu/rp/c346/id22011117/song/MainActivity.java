package sg.edu.rp.c346.id22011117.song;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    TextView tvTitle, tvSingers, tvYear, tvStars, tvResults;
    EditText etTitle, etSingers, etYear;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        tvTitle = findViewById(R.id.textViewSongTitle);
        tvSingers = findViewById(R.id.textViewSingers);
        tvYear = findViewById(R.id.textViewYear);
        tvStars = findViewById(R.id.textViewStars);
        tvResults = findViewById(R.id.textViewResults);
        etTitle = findViewById(R.id.editTextSongTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        lv = findViewById(R.id.listView);


    btnInsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DBHelper db = new DBHelper(MainActivity.this);

            String msg1 = etTitle.getText().toString();
            String msg2 = etSingers.getText().toString();
            String msg3 = etYear.getText().toString();

            db.insertTask(msg1, msg2, msg3);
        }
    });
    btnShowList.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){

        DBHelper db = new DBHelper(MainActivity.this);


        ArrayList<String> data = db.getTaskContent();

        ArrayList<List> alTasks = db.getTasks();

        db.close();

        String txt = "";
        for (int i = 0; i < data.size(); i++) {
            Log.d("Database Content", i + ". " + data.get(i));
            txt += i + ". " + data.get(i) + "\n";
        }
        tvResults.setText(txt);

        ArrayAdapter<List> aaTasks = new ArrayAdapter<List>(MainActivity.this, android.R.layout.simple_list_item_1, alTasks);
        lv.setAdapter(aaTasks);
    }
    });
}
}

