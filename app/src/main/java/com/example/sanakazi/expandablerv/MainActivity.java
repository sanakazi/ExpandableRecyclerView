package com.example.sanakazi.expandablerv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String REGISTER_URL = "http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorListCategoryGrouped?userID=20194";
    private static final String TAG=MainActivity.class.getSimpleName();
    MyAdapter mAdapter;
    private ArrayList<DataModel.ParentModel> parent_arrayList;
    private ArrayList<DataModel.ParentModel.ChildModel> child_arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
      //  chList = new ArrayList<DataModel.ChildModel>();
       // pList = new ArrayList<DataModel.ParentModel>();

        volleyService();
    }

    private void volleyService()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w(TAG,response.toString());
                        Gson gson= new Gson();
                        DataModel jsonResponse = gson.fromJson(response, DataModel.class);
                        if (jsonResponse.getStatus() == 1) {
                            parent_arrayList = jsonResponse.getMessage();
                            Log.w(TAG, "Values for Parent are " );

                            for (int i = 0; i < parent_arrayList.size(); i++) {
                                Log.w(TAG, parent_arrayList.get(i).getFullName());
                                if(parent_arrayList.get(i).getVendorList()!=null)
                                {

                                    int childArraySize=parent_arrayList.get(i).getVendorList().size();
                                    Log.w(TAG, "Values for Child are " );
                                    child_arrayList = parent_arrayList.get(i).getVendorList();
                                    for(int j=0;j<childArraySize;j++)
                                    {
                                        Log.w(TAG, child_arrayList.get(j).getFullName());
                                    }
                                }
                            }
                        }



                        mAdapter = new MyAdapter(MainActivity.this, parent_arrayList);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }





}
