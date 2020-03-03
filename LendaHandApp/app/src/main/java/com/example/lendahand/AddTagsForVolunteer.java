package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class AddTagsForVolunteer extends AppCompatActivity {

    ChipGroup tagCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags_for_volunteer);



        tagCollection = (ChipGroup)findViewById(R.id.tags_group);

        LayoutInflater inflater = LayoutInflater.from(AddTagsForVolunteer.this);

        String[] tags = new String[]{"Environment", "Hunger Relief", "Health", "Senior", "People with Disabilities", "Animals", "Education", "Construction", "Community", "Animal", "Hunger Relief"};
        final ArrayList<String> userSelectedTags = new ArrayList<String>();

        for (String tagString : tags) {
            Chip tag = (Chip)inflater.inflate(R.layout.tag_item, null, false);
            tag.setText(tagString);
            tagCollection.addView(tag);
        }

        MaterialButton startServingButton = findViewById(R.id.start_serving_button);

        startServingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Code from: https://stackoverflow.com/questions/55905793/get-selected-chips-from-a-chipgroup
                String msg = "Chips checked are: ";
                int chipsCount = tagCollection.getChildCount();
                if (chipsCount == 0) {
                    msg += "None!!";
                } else {
                    int i = 0;
                    while (i < chipsCount) {
                        Chip chip = (Chip) tagCollection.getChildAt(i);
                        if (chip.isChecked() ) {
                            String nameOfTag = chip.getText().toString();
                            msg += nameOfTag + " " ;
                            userSelectedTags.add(nameOfTag);
                        }
                        i++;
                    };
                }

                Log.d("AddTags", msg);
                Intent home = new Intent(v.getContext(), MainActivity.class);
                startActivity(home);

            }
        });


    }
}
