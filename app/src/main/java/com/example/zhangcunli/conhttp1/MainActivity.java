package com.example.zhangcunli.conhttp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private ListView listView;
    private List<Book> books;
    private final String BASEURL = "http://192.168.1.105/";
    private MainActivity content = MainActivity.this;
    private EditText editText;
    private ImageView s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuju3();
//                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
            }
        });

        s1 = (ImageView)findViewById(R.id.s1);
//        s1.setImageResource(R.drawable.s1);

//        onCreate
    }

    public void shuju3() {
        textView = (TextView) findViewById(R.id.img1);

        Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png").into(s1);
//        Picasso.with(MainActivity.this).load(R.drawable.n23).into(s1);


//        Picasso.with(MainActivity.this).load(R.drawable.n23).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                textView.setBackground(new BitmapDrawable(getResources(), bitmap));
//                textView.setText(R.string.showData);
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//                Log.e("MainActivity", "-----------onBitmapFailed--------------");
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//                Log.e("MainActivity", "-----------onPrepareLoad--------------");
//            }
//        });


//    EditText myImg = (EditText) findViewById(R.id.myimg);
//
//String imgUrl = myImg.getText().toString().trim().replace(" ","");
//    String url = "http://192.168.253.1/img/"+imgUrl;
//
//    Picasso.with(MainActivity.this).load(url).into(imageView);


    }


    //点击按钮，显示服务器上的数据
    public void shuju() {
//        listView = (ListView) findViewById(R.id.myList);

//去服务器获取数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL) //指定访问的IP地址
                .addConverterFactory(GsonConverterFactory.create())//指定以哪种格式解析接收到的数据
                .build();
        MyServerInterface serverInterface = retrofit.create(MyServerInterface.class);
        Call<List<Book>> call = serverInterface.bookDetais();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                int statusCode = response.code();
                Log.e("MainActivity", "--------------statusCode-----" + statusCode);
                books = response.body();

                listView.setAdapter(dataToPage());//把数据放到listView里面

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                t.printStackTrace();

                Log.e("MainActivity", "---------出错了-----" + t.toString());
            }
        });
    }

    //    把服务器数据放到页面
    public BaseAdapter dataToPage() {
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return books.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                RelativeLayout r1 = new RelativeLayout(content);
                RelativeLayout.LayoutParams r1Params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                r1.setLayoutParams(r1Params);

                ImageView img1 = new ImageView(content);
                img1.setId(View.generateViewId());
                img1.setScaleType(ImageView.ScaleType.FIT_XY);
                String imgUrl = BASEURL + books.get(position).getBookFace();
                Picasso.with(MainActivity.this).load(imgUrl).into(img1);
                RelativeLayout.LayoutParams img1Params = new RelativeLayout.LayoutParams(200, 200);
                img1Params.addRule(RelativeLayout.ALIGN_PARENT_START);
                img1Params.setMargins(0, 30, 30, 0);
                img1.setLayoutParams(img1Params);
                r1.addView(img1);

                TextView t1 = new TextView(content);
                t1.setText(books.get(position).getName());
                t1.setTextSize(20);
                t1.setId(View.generateViewId());
                RelativeLayout.LayoutParams t1Params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                t1Params.addRule(RelativeLayout.RIGHT_OF, img1.getId());
                t1.setLayoutParams(t1Params);
                r1.addView(t1);

                return r1;
            }
        };
        return baseAdapter;
    }


    public void shuju2() {
        editText = (EditText) findViewById(R.id.edt);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL) //指定访问的IP地址
                .addConverterFactory(GsonConverterFactory.create())//指定以哪种格式解析接收到的数据
                .build();
        MyServerInterface serverInterface = retrofit.create(MyServerInterface.class);

        String bookName = editText.getText().toString().trim().replace(" ", "");
        Call<List<Book>> call = serverInterface.bookCanshu(bookName);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                int statusCode = response.code();
                Log.e("MainActivity", "-----状态码---------statusCode-----" + statusCode);
                List<Book> books = response.body();
                String bk = "";
                for (int i = 0; i < books.size(); i++) {
                    Log.e("MainActivity", "--------------getBookId-----" + books.get(i).getBookId());
                    Log.e("MainActivity", "--------------getName-----" + books.get(i).getName());
                    Log.e("MainActivity", "--------------getBookFace-----" + books.get(i).getBookFace());
                    Log.e("MainActivity", "-------------------");
//                    bk += books.get(i).getBookId() + " *** "+books.get(i).getName()+ " *** "+books.get(i).getBookFace()+ "___" ;
                }
//                textView.setText(bk);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                t.printStackTrace();

                Log.e("MainActivity", "--------------toString-----" + t.toString());
            }
        });
    }

}
