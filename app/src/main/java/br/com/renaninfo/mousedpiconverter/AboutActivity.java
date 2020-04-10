package br.com.renaninfo.mousedpiconverter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends Activity { //AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about);

        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0.0");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Description")
//                    .setImage(R.drawable.dummy_image)
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addEmail("renan.a.santos.88@gmail.com")
                .addWebsite("http://renaninfo.github.io/")
//                .addFacebook("the.medy")
//                .addTwitter("medyo80")
//                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.renaninfo")
//                .addGitHub("medyo")
//                .addInstagram("medyo80")
                .create();

        setContentView(aboutPage);
    }

}
