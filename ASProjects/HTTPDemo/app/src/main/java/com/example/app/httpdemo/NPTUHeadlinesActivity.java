package com.example.app.httpdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NPTUHeadlinesActivity extends AppCompatActivity {

    LinearLayout ll;
    ListView lv;
    ArrayList<Headline> headlines = null;
    ArrayList<MyListItem> itemList = new ArrayList<MyListItem>();
    MyListAdapter madt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        lv = new ListView(this);
        ll.addView(lv, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(ll);

        headlines = new ArrayList<Headline>();

        try {
            URL url = new URL("http://www.nptu.edu.tw/files/502-1000-1000-1.php?Lang=zh-tw");
            new AccessInternet().execute(url);
        } catch (Exception e) {
            Log.v("HTTPDemo", e.toString());
        }
    }

    private class Headline {
        String date;
        String title;
        String poster;
        String url;

        Headline(String d, String t, String p, String r) {
            date = d;
            title = t;
            poster = p;
            url = r;
        }
    }


    private class AccessInternet extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            BufferedReader reader = null;
            StringBuilder stringBuilder;

            try {
                HttpURLConnection connection = (HttpURLConnection) urls[0].openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15 * 1000);
                connection.connect();

                reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                return e.toString();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioe) {
                        return ioe.toString();
                    }
                }
            }
        }

        protected void onPostExecute(String result) {
            String date, title, poster, url;
            String webcontent = result;

            int start = webcontent.indexOf("<tbody>");
            start = webcontent.indexOf("<tbody>", start+1 );
            int end = webcontent.indexOf("</tbody>", start);

            webcontent = webcontent.substring(start, end);
            webcontent.trim();

            String[] posts = webcontent.split("</tr>");
            String curPost;

            for (int i = 0; i < posts.length-1; i++) {
                curPost = posts[i];

                int loc1 = curPost.indexOf("nowrap");
                int loc2 = curPost.indexOf("title=");
                int loc3 = curPost.indexOf("href");
                int loc4 = curPost.indexOf("\">", loc3);
                int loc5 = curPost.lastIndexOf("nowrap");
                int loc6 = curPost.lastIndexOf("td>");

                if(loc1<0) {
                    date="";
                    title="網站更新中";
                    url="";
                    poster="";
                }
                else {
                    date = curPost.substring(loc1 + 16, loc1 + 32);
                    date = date.trim();

                    title = curPost.substring(loc2 + 7, loc3 - 3);
                    title = title.trim();

                    title = title.length() > 35 ?
                            (title.substring(0, 32)).concat("...") : title;

                    if (loc4 > loc5 ) {
                        loc4 = curPost.indexOf("target");
                        url = new String("https://drive.google.com/viewerng/viewer?"+
                                        "embedded=true&url=http://www.nptu.edu.tw");
                        url = url.concat(curPost.substring(loc3 + 6, loc4 - 2));
                    } else {
                        url = curPost.substring(loc3 + 6, loc4);
                    }
                    url = url.trim();
                    url = url.replace("files/13", "files/16");
                    url = url.replace("-1.php", ".php");

                    poster = curPost.substring(loc5 + 8, loc6 - 2);
                    poster = poster.trim();
                }
                headlines.add(new Headline(date, title, poster, url));
            }

            for (int i = 0; i < headlines.size(); i++) {
                MyListItem item = new MyListItem();
                Headline hl = headlines.get(i);
                item.setDate(hl.date);
                item.setTitle(hl.title);
                item.setUnit(hl.poster);
                item.setUrl(hl.url);
                item.setImg(android.R.drawable.btn_star_big_on);
                itemList.add(item);
            }

            madt = new MyListAdapter(NPTUHeadlinesActivity.this, itemList);
            lv.setAdapter(madt);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> parent, View view, int position, long id) {
                    Intent it = new Intent(NPTUHeadlinesActivity.this,
                            DetailedHeadlinesActivity.class);
                    Headline hl = headlines.get(position);
                    it.putExtra("date", hl.date);
                    it.putExtra("title", hl.title);
                    it.putExtra("poster", hl.poster);
                    it.putExtra("url", hl.url);
                    startActivity(it);
                }
            });
        }

    }

    public class MyListItem {
        private int img;
        private String date;
        private String title;
        private String unit;
        private String url;

        public void setImg(int img) {
            this.img = img;
        }

        public int getImg() {
            return img;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}

