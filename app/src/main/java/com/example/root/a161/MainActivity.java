package com.example.root.a161;

import java.io.IOException;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;

        import android.app.Activity;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Environment;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    TextView content;
    EditText text;
    Button ok, delete;
    static String FILENAME = "test.txt";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up UI components
        setIds();

        //creation of File
        file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        try {
            if (file.createNewFile()) {
                Toast.makeText(getApplicationContext(), "File Created",
                        Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //update data to File
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value =  text.getText().toString();
                text.setText("");

                ReadFromFile rf = new ReadFromFile(file);
                rf.execute(value);

            }
        });

        //deletion of  file
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                file.delete();
            }
        });

    }

    public void setIds() {
        content = (TextView) findViewById(R.id.textView);
        text = (EditText) findViewById(R.id.editText);
        ok = (Button) findViewById(R.id.ok);
        delete = (Button) findViewById(R.id.delete);
    }

    private class ReadFromFile extends AsyncTask<String, Integer, String> {




       //  static String FILENAME = "test.txt";
        File f;

        public ReadFromFile(File f) {
            super();
            this.f = f;
            // TODO Auto-generated constructor stub
        }

        //update data to file
        @Override
        protected String doInBackground(String... str) {



            String enter = "\n";

            FileWriter writer=null ;
            try {
                writer = new FileWriter(f,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                writer = new FileWriter(f, true);
                writer.append(str[0].toString());
                writer.append(enter);
                writer.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {


            }


            return null;

        }


        //read data from file
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String name = "";
            StringBuilder sb = new StringBuilder();
            FileReader fr = null;

            try {
                fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                while ((name = br.readLine()) != null) {
                    sb.append(name);
                    sb.append("\n");

                }
                br.close();
                fr.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            content.setText(sb.toString());
        }

    }
}

