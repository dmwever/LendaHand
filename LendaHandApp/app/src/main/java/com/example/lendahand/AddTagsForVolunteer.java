package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class AddTagsForVolunteer extends AppCompatActivity {

    ChipGroup tagCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags_for_volunteer);

        tagCollection = (ChipGroup)findViewById(R.id.tags_group);

        LayoutInflater inflater = LayoutInflater.from(AddTagsForVolunteer.this);

        String[] tags = new String[]{"Enviromnet", "Hunger Relief", "Health", "Senior", "People with Disabilities", "Animals", "Education", "Construction", "Community", "Animal", "Hunger Relief"};

        for (String tagString : tags) {
            Chip tag = (Chip)inflater.inflate(R.layout.tag_item, null, false);
            tag.setText(tagString);
            tagCollection.addView(tag);
        }
    }
}
