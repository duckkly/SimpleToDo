package sg.edu.rp.c346.id22011765.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etinput;
    Button btnadd;
    Button btndelete;
    Button btnclear;
    ListView todolist;

    ArrayList<String> todoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        etinput = findViewById(R.id.etinput);
        btnadd = findViewById(R.id.btnadd);
        btndelete = findViewById(R.id.btndelete);
        btnclear = findViewById(R.id.btnclear);
        todolist = findViewById(R.id.todolist);

        todoal = new ArrayList<>();

        ArrayAdapter todoaa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, todoal);
        todolist.setAdapter(todoaa);

        btnadd.setOnClickListener((v -> {
            String input = etinput.getText().toString();
            todoal.add(input);
            todoaa.notifyDataSetChanged();
            etinput.setText(null);
        }));

        btnclear.setOnClickListener((v -> {
            todoal.clear();
            todoaa.notifyDataSetChanged();
        }));

        //enhancement 1

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etinput.setHint("Type in a new task here");
                        etinput.setText("");
                        btndelete.setEnabled(false);
                        btnadd.setEnabled(true);
                        break;
                    case 1:
                        etinput.setHint("Type in the index of the task to be removed");
                        etinput.setText("");
                        btnadd.setEnabled(false);
                        btndelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //enhancement 2

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = etinput.getText().toString();
                int indexint = Integer.parseInt(index);
                if (todoal.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                } else if (indexint < 0 || indexint >= todoal.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                } else {
                    todoal.remove(indexint);
                    todoaa.notifyDataSetChanged();
                }
            }
        });
    }
}
