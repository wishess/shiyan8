package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    List list1=new ArrayList();
    List list2=new ArrayList();
    List list3=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"library",null,2);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData =(Button) findViewById(R.id.add_data1);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //第一条数据
                values.put("category_name","经济类");
                values.put("category_code","1");
                db.insert("Category",null,values);
                values.clear();
                //第二条数据
                values.put("category_name","历史类");
                values.put("category_code","2");
                db.insert("Category",null,values);
            }
        });

        Button addData2 =(Button) findViewById(R.id.add_data2);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                values.put("category_id",1);
                db.insert("Book",null,values);
                values.clear();
                //第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                values.put("category_id",2);
                db.insert("Book",null,values);
            }
        });
        Button queryButton=(Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor!=null&&cursor.getCount()>=1){
                    while(cursor.moveToNext()){
                        list1.add(cursor.getString(cursor.getColumnIndex("name")));
                        list2.add(cursor.getString(cursor.getColumnIndex("category_id")));
                        list3.add(cursor.getString(cursor.getColumnIndex("price")));
                    }
                }
                cursor.close();
                db.close();
            }
        });

    }
}