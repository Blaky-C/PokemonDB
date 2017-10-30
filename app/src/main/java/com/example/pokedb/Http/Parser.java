package com.example.pokedb.Http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Jack on 2017/10/29.
 */

public class Parser {

    public interface HTMLParserListener{
        void onFinish(Document response);
        void onError(Exception e);
    }

    public static void parseHTML(final String a, final HTMLParserListener p){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = null;
                try {
                    document = Jsoup.connect(a).get();
                    p.onFinish(document);
                }catch (Exception e){
                    p.onError(e);
                }
            }
        }).start();

    }

}
