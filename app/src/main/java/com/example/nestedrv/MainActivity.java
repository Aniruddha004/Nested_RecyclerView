package com.example.nestedrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv=findViewById(R.id.rV_parent);
        rv.getRootView().setBackgroundColor(0xff000000);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(4);
        rv.setLayoutManager(layoutManager);

        Toast.makeText(this, "Loading Resources...", Toast.LENGTH_SHORT).show();

        List<child_model> childList=new ArrayList<>();
        childList.add(new child_model(0,"https://www.kuer.org/sites/kuer/files/styles/x_large/public/3185713-246700471.jpg" ));
        childList.add(new child_model(1,"https://st4.depositphotos.com/3367263/20877/v/600/depositphotos_208774514-stock-video-lightening-storm-clouds.mp4"));
        childList.add(new child_model(0,"https://cdn.mos.cms.futurecdn.net/c7GYXE3TEQvvFGDZDvXDTT-650-80.jpg.webp" ));
        childList.add(new child_model(1,"https://st3.depositphotos.com/32435356/34106/v/600/depositphotos_341067394-stock-video-view-lightning-strikes-night-dark.mp4"));


        List<parent_model> parentList=new ArrayList<>();
        parentList.add(new parent_model(0,null,childList));
        parentList.add(new parent_model(parent_model.TEXT_TYPE,"The Illuminati (plural of Latin illuminatus, 'enlightened') is a name given to several groups, both real and fictitious. Historically, the name usually refers to the Bavarian Illuminati, an Enlightenment-era secret society founded on 1 May 1776 in Bavaria, today part of Germany. The society's goals were to oppose superstition, obscurantism, religious influence over public life, and abuses of state power.",null));
        parentList.add(new parent_model(0,null,childList));
        parentList.add(new parent_model(parent_model.TEXT_TYPE,"The order of the day, they wrote in their general statutes, is to put an end to the machinations of the purveyors of injustice, to control them without dominating them. The Illuminati—along with Freemasonry and other secret societies—were outlawed through edict by Charles Theodore, Elector of Bavaria with the encouragement of the Catholic Church, in 1784, 1785, 1787, and 1790. In the following several years, the group was vilified by conservative and religious critics who claimed that they continued underground and were responsible for the French Revolution.",null));
        parentList.add(new parent_model(0,null,childList));
        parentList.add(new parent_model(parent_model.TEXT_TYPE,"The Illuminati did not survive their suppression in Bavaria; their further mischief and plottings in the work of Barruel and Robison must be thus considered as the invention of the writers.Conspiracy theorists and writers such as Mark Dice have argued that the Illuminati have survived to this day. Many conspiracy theories propose that world events are being controlled and manipulated by a secret society calling itself the Illuminati. Conspiracy theorists have claimed that many notable people were or are members of the Illuminati. Presidents of the United States are a common target for such claims.",null));
        parentList.add(new parent_model(0,null,childList));


        parent_adapter parent_adapter=new parent_adapter(parentList,getApplicationContext());
        rv.setAdapter(parent_adapter);
        parent_adapter.notifyDataSetChanged();
    }
}
