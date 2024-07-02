package com.github.catvod.debug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.catvod.R;
import com.github.catvod.crawler.Spider;
import com.github.catvod.spider.CaoLiu;
import com.github.catvod.spider.Cg51;
import com.github.catvod.spider.Douban;
import com.github.catvod.spider.Dygangs;
import com.github.catvod.spider.IQIYI;
import com.github.catvod.spider.Ikanbot;
import com.github.catvod.spider.Init;
import com.github.catvod.spider.J91;
import com.github.catvod.spider.Jable;
import com.github.catvod.spider.JavDb;
import com.github.catvod.spider.JustLive;
import com.github.catvod.spider.MGTV;
import com.github.catvod.spider.MiMei;
import com.github.catvod.spider.QxiTv;
import com.github.catvod.spider.RouVideo;
import com.github.catvod.spider.W55Movie;
import com.github.catvod.spider.Wogg;
import com.github.catvod.spider.XVideos;
import com.github.catvod.spider.Zhaozy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    private ExecutorService executor;
    private Spider spider;
    private TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button homeContent = findViewById(R.id.homeContent);
        Button homeVideoContent = findViewById(R.id.homeVideoContent);
        Button categoryContent = findViewById(R.id.categoryContent);
        Button detailContent = findViewById(R.id.detailContent);
        Button playerContent = findViewById(R.id.playerContent);
        Button searchContent = findViewById(R.id.searchContent);
        homeContent.setOnClickListener(view -> executor.execute(this::homeContent));
        homeVideoContent.setOnClickListener(view -> executor.execute(this::homeVideoContent));
        categoryContent.setOnClickListener(view -> executor.execute(this::categoryContent));
        detailContent.setOnClickListener(view -> executor.execute(this::detailContent));
        playerContent.setOnClickListener(view -> executor.execute(this::playerContent));
        searchContent.setOnClickListener(view -> executor.execute(this::searchContent));
        Logger.addLogAdapter(new AndroidLogAdapter());
        executor = Executors.newCachedThreadPool();
        executor.execute(this::initSpider);
        textView = findViewById(R.id.textView);


        Dygangs spider1 = new Dygangs();
//        try {
//            String str = spider1.homeContent(true);
//            textView.setText(str);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Logger.e("s","s");


        List<String> ids = new ArrayList<>();
        ids.add("/dsj/20240618/54844.htm");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


//                    String html = OkHttp.string("https://www.dygangs.net", spider1.getHeader());
//                    Document doc = Jsoup.parse(html);
//                    Elements elements = doc.getElementsByTag("table").get(2).select("tr").select("a");
//                    LinkedHashMap<String, List<Filter>> filters = new LinkedHashMap<>();
//                    for (int i = 0; i < elements.size() - 1; i++) {
//                        if (i < 2 || i == elements.size() - 1) continue;
//                        Element e = elements.get(i);
//                        String typeId = e.attr("href");
//                        Logger.e("AAAAAAAAAAAAtypeId ", typeId);
//                        String typeName = e.text();
//                        Logger.e("BBBBBBBBBBBBtypeName ", typeName);

//                    String str = spider1.categoryContent("/bd/","1",true,new HashMap<>());
//                    String home = spider1.homeContent(true);
//                    https://www.dygangs.net/dsj/20240618/54844.htm
//                    String detailContent = spider1.detailContent(ids);
//                    String searchContent = spider1.searchContent("嫌疑人",true);


//                    String en = new String("搜索".getBytes(StandardCharsets.UTF_8), "GBK");
                    Log.e("URLEncoder搜索:", URLEncoder.encode("搜索","GBK"));
                    Log.e("URLEncoderkeyboard:", URLEncoder.encode("嫌疑人","GBK"));
                    String xb6vsearchContent = spider1.searchContent("嫌疑人", true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                textView.append(xb6vsearchContent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
//                                Logger.e("CCCCCCCCCC", typeId+"："+typeName+"\n");
                        }
                    });
//                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        






    }

    private void initSpider() {
        try {
            Init.init(getApplicationContext());
            spider = new RouVideo();
            spider.init(this, "");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void homeContent() {
        try {
            Logger.t("homeContent").d(spider.homeContent(true));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void homeVideoContent() {
        try {
            Logger.t("homeVideoContent").d(spider.homeVideoContent());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void categoryContent(){

        try {
            Logger.t("categoryContent").d(spider.categoryContent("探花", "1", true, new HashMap<>()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void detailContent() {
        try {
            Logger.t("detailContent").d(spider.detailContent(Arrays.asList("cltwqjwpk0000vnaosygqajdn")));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void playerContent() {
        try {
            Logger.t("playerContent").d(spider.playerContent("轉存原畫", "454873-5-5.html", new ArrayList<>()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void searchContent() {
        try {
            Logger.t("searchContent").d(spider.searchContent("空姐", false));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}