package com.example.gslplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Menus extends AppCompatActivity {
    String[] items;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        listView = findViewById(R.id.lista);

        File musicFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download");
        ArrayList<File> audioList = findingSong(musicFolder);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        items = new String[audioList.size()];
        for (int i = 0; i<audioList.size(); i++){
            items[i] = audioList.get(i).getName().toString().replace(".mp3","");
        }

        customAdapter adapter = new customAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String songName = (String) listView.getItemAtPosition(i);
                startActivity(new Intent(getApplicationContext(), Reproductor.class)
                        .putExtra( "songs", audioList)
                        .putExtra( "songname", songName)
                        .putExtra( "pos", i));
            }
        });

    }

    public ArrayList<File> findingSong(File file){
        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for (File singlefile : files){
            if (singlefile.isDirectory() && !singlefile.isHidden()){
                arrayList.addAll(findingSong(singlefile));

            }else {
                if (singlefile.getName().endsWith(".mp3")){
                    arrayList.add(singlefile);
                }
            }
        }
        return  arrayList;
    }

    class customAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView = getLayoutInflater().inflate(R.layout.activity_menus, null);
            TextView textsong = myView.findViewById(R.id.textExample);
            textsong.setSelected(true);
            textsong.setText(items[i]);
            return myView;
        }

    }
}