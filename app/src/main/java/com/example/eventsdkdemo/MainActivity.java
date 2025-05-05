package com.example.eventsdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventsdkdemo.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import net.mediascope.mediatagsdk.Mediatag;
import net.mediascope.mediatagsdk.MediatagEvent;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private int selectedContactTypeIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //активация сервиса
        String[] contactTypes = {getString(R.string.undefined), getString(R.string.live_broadcast),
                getString(R.string.vod), getString(R.string.catch_up),
                getString(R.string.text_article), getString(R.string.soc_network),
                getString(R.string.live_audio), getString(R.string.audio)};

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, contactTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.contactType.setAdapter(adapter);
        binding.contactType.setText(adapter.getItem(selectedContactTypeIndex), false);
        binding.btnStart.setChecked(true);

        binding.contactType.setOnItemClickListener((adapterView, view1, i, l) -> selectedContactTypeIndex = i);

        binding.btnSendEvent.setOnClickListener(view1 -> {
             View parentLayout = findViewById(android.R.id.content);

            int selectedViewTypeIndex = 0;  //STOP,
            if (binding.btnStart.isChecked())
                selectedViewTypeIndex = 1;  //START,
            else if (binding.btnPause.isChecked())
                selectedViewTypeIndex = 3;  //PAUSE;
            else if (binding.btnHeartbeat.isChecked())
                selectedViewTypeIndex = 2;   //HEARTBEAT,

            //инициализация события с одним обязательным параметром
            MediatagEvent event = new MediatagEvent(MediatagEvent.ContactTypes.values()[selectedContactTypeIndex]);

            if (binding.tvVer.getEditableText().length() > 0)
                event.setVer(Integer.parseInt(binding.tvVer.getEditableText().toString()));

            if (binding.tvMedia.getEditableText().length() > 0)
                event.setMedia(binding.tvMedia.getEditableText().toString());

            if (binding.tvFts.getEditableText().length() > 0)
                event.setFts(Long.parseLong(binding.tvFts.getEditableText().toString()));

            if (binding.tvIdc.getEditableText().length() > 0)
                event.setIdc(Integer.parseInt(binding.tvIdc.getEditableText().toString()));

            if (binding.tvUrlc.getEditableText().length() > 0)
                event.setUrlc(binding.tvUrlc.getEditableText().toString());

            if (binding.tvIdlc.getEditableText().length() > 0)
                event.setIdlc(binding.tvIdlc.getEditableText().toString());

            Map<String, String> map = new HashMap<>();
            for(int i = 0; i < 35; i++){
                map.put("key_"+ i,"value_" + i);
            }

            event.setView(MediatagEvent.ViewTypes.values()[selectedViewTypeIndex])
                 .setExtMapAttrs(map);
            //добавляем событие в очередь для отправки

            Mediatag.instance().addEvent(event);

            Snackbar.make(parentLayout, R.string.event_send, Snackbar.LENGTH_LONG)
                    .setAnchorView(findViewById(R.id.btn_send_event))
                    .show();
        });
    }

    @Override
    protected void onDestroy() {
        Mediatag.release();
        super.onDestroy();
    }
}