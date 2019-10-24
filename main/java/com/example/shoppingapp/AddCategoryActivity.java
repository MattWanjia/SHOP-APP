package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddCategoryActivity extends AppCompatActivity {

    private ImageView tshirts, short_sleeve, long_sleeve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        tshirts = findViewById(R.id.category_t_shirt);
        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tshirts){
                    Intent intent = new Intent(getApplicationContext(), AdminAddProductActivity.class);
                    intent.putExtra("category_name", "tshirts");
                    startActivity(intent);
                }
            }
        });

        short_sleeve = findViewById(R.id.category_short_sleeve);
        short_sleeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == short_sleeve){
                    Intent intent = new Intent(getApplicationContext(), AdminAddProductActivity.class);
                    intent.putExtra("category_name", "ShortSleeve");
                    startActivity(intent);
                }
            }
        });

        long_sleeve = findViewById(R.id.category_long_sleeve);
        long_sleeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == long_sleeve){
                    Intent intent = new Intent(getApplicationContext(), AdminAddProductActivity.class);
                    intent.putExtra("category_name", "LongSleeve");
                    startActivity(intent);
                }
            }
        });
    }
}
