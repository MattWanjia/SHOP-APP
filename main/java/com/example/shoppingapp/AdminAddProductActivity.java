package com.example.shoppingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AdminAddProductActivity extends AppCompatActivity {
    private TextView addProductTopTxt, addProductCategory;
    private String category_name, PName, PPriceString;
    private int Price_Int;

    private ImageView product_image;
    private EditText product_name, product_price;
    private Button add_product_button;

    private static final int GalleryPick = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        addProductTopTxt = findViewById(R.id.add_product_text);
        addProductCategory = findViewById(R.id.add_product_category);

        product_image = findViewById(R.id.add_product_imageView);
        product_name = findViewById(R.id.add_product_Name);
        product_price = findViewById(R.id.add_product_Price);
        add_product_button = findViewById(R.id.add_product_button);

        Intent intent = getIntent();
        category_name = intent.getStringExtra("category_name");
        addProductCategory.setText(category_name);

        //setCategoryName();

        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoosePicture();
            }
        });

        add_product_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateData();
                //Toast.makeText(getApplicationContext(), "UPLOADING PRODUCT", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChoosePicture(){
        Intent openGallery = new Intent();
        openGallery.setAction(Intent.ACTION_GET_CONTENT);
        openGallery.setType("image/*");
        startActivityForResult(openGallery, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick ){
            imageUri = data.getData();
            //product_image.setImageURI(imageUri);
            /*try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                product_image.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }*/
            Picasso.with(getApplicationContext()).load(imageUri).into(product_image);
        }
    }

    private void ValidateData(){
        PName = product_name.getText().toString();
        PPriceString = product_price.getText().toString();

        if (PName.isEmpty() || PPriceString.isEmpty()){
            Toast.makeText(getApplicationContext(), "Check product details again", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imageUri == null){
            Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            storeProductInformation();
        }
        Price_Int = Integer.parseInt(PPriceString);

    }

    private void storeProductInformation(){

    }

    private String setCategoryName(){
        Intent intent = getIntent();
        String category_name = intent.getStringExtra("category_name");
        addProductCategory.setText(category_name);

        return category_name;
    }
}
