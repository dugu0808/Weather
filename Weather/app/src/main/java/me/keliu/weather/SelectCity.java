package me.keliu.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.keliu.app.MyApplication;
import me.keliu.bean.City;

/**
 * Created by KE on 2017/10/18.
 */

public  class SelectCity extends Activity implements View.OnClickListener{
    private ImageView mBackBtn;
    private ListView cityListLv;

    private ImageView searchBtn;
    private EditText searcnBox;

    private List<City>mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    ArrayAdapter<String> adapter;

    private String updateCityCode = "-1";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        searcnBox = (EditText)findViewById(R.id.search_box);
        searchBtn = (ImageView)findViewById(R.id.search_button);
        searchBtn.setOnClickListener(this);

        mBackBtn = (ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();
        for (int i = 0; i<mCityList.size(); i++) {
            String No_ = Integer.toString(i+1);
            String number = mCityList.get(i).getNumber();
            String provinceName = mCityList.get(i).getProvince();
            String cityName = mCityList.get(i).getCity();
            mArrayList.add(number+" "+provinceName+"-"+cityName);
        }
        cityListLv = (ListView)findViewById(R.id.selectcity_lv);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);

        //添加ListView项的点击事件的动作
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCityCode = mCityList.get(position).getNumber();
                Log.d("update city code",(updateCityCode));
            }
        };
        //为组件绑定监听
        cityListLv.setOnItemClickListener(itemClickListener);
    }




    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.search_button:
                String citycode = searcnBox.getText().toString();
                ArrayList<String> mSearchList = new ArrayList<String>();
                for(int i=0;i<mCityList.size();i++)
                {
                    String No_ = Integer.toString(i+1);
                    String number= mCityList.get(i).getNumber();
                    String provinceName = mCityList.get(i).getProvince();
                    String cityName = mCityList.get(i).getCity();
                    if(number.equals(citycode)) {
                        mSearchList.add(number + " " + provinceName + "-" + cityName);
                        //Log.d("changed adapter data","NO." + No_ + ":" + number + "-" + provinceName + "-" + cityName);
                    }
                    adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mSearchList);
                    cityListLv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                //Intent i = new Intent(this, MainActivity.class);
                //i.putExtra("cityCode",citycode);
                //setResult(RESULT_OK, i);
                //finish();
                break;
            case R.id.title_back:
                Intent intent = new Intent(this, MainActivity.class);

                //用Shareperference存储最近一次的citycode
                SharedPreferences sharedPreferences = getSharedPreferences("CityCodePreference",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cityCode",updateCityCode);
                editor.commit();

                intent.putExtra("cityCode", updateCityCode);
                Log.d("transmit data",updateCityCode);
                //startActivity(intent);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }
}
