package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewItemPage extends AppCompatActivity {


    private EditText itemName;
    private EditText itemCategory;
    private EditText itemDescription;
    private Button addItemBTN;
    private ItemClass itemClass;
    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    private FloatingActionButton fab;
    private ImageView cover;
    private Spinner Category;
    private ArrayAdapter<String> adapter;
    private String selectedCategory;
    private FloatingActionButton datepickerButton;
    //private Button selectdateBTN;
    // One Button
    private Button SelectImage;
    private Button TakePhotoBTN;
    private Uri imageUri;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int REQUEST_IMAGE_CAPTURE_PERMISSION = 100;
    private Uri filePath;
    private Button backBTN;

    // One Preview Image
    ImageView IVPreviewImage;

    // Define the pic id
    private static final int pic_id = 123;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    // Define the button and imageview type variable
    private Button camera_open_id;
    private ImageView click_image_id;
    private FirebaseStorage fireStore;
    private StorageReference storeReference;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("ItemCategories");
    // get the Firebase  storage reference



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item_page);

        // register the UI widgets with their appropriate IDs
        //BSelectImage = findViewById(R.id.BSelectImage);

        fireStore = FirebaseStorage.getInstance();
        storeReference = fireStore.getReference();

        spinner = (Spinner) findViewById(R.id.categorySpinner);

        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Categories");
        categoryList.add("Shirts");
        categoryList.add("Pants");
        categoryList.add("Hats");
        categoryList.add("Shoes");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, categoryList);

        spinner.setAdapter(categoryAdapter);


        itemName = findViewById(R.id.itemnameTB);
        itemDescription = findViewById(R.id.descriptionTB);
        addItemBTN = findViewById(R.id.addItemButton);
        datepickerButton = findViewById(R.id.datePickerBTN);
        SelectImage = findViewById(R.id.BSelectImage);
        TakePhotoBTN = findViewById(R.id.TakePhotoBTN);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        backBTN = findViewById(R.id.backButton2);
        itemClass = new ItemClass();

        addItemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = spinner.getSelectedItem().toString();

                String itemNameTODB = itemName.getText().toString();
                String descriptionTODB = itemDescription.getText().toString();
                //String dateTODB = itemName.getText().toString();


                if (!(spinner.getSelectedItem().toString() == "Categories") && !TextUtils.isEmpty(descriptionTODB) && !TextUtils.isEmpty(itemNameTODB) && !TextUtils.isEmpty(itemClass.getItemDate())) {
                    itemClass.setItemName(itemNameTODB);
                    itemClass.setItemDescription(descriptionTODB);
                    //
                    //

                    storeDrobeRef.child(selectedCategory).push().setValue(itemClass);
                    Toast.makeText(AddNewItemPage.this, "Item added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddNewItemPage.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }

                //uploadImage();

            }

        });


        datepickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create calender for todays date
                Calendar datePickerCalender = Calendar.getInstance();
                int year = datePickerCalender.get(Calendar.YEAR);
                int month = datePickerCalender.get(Calendar.MONTH);
                int day = datePickerCalender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog itemDatePicker = new DatePickerDialog(AddNewItemPage.this,
                        android.R.style.Theme_Light_Panel,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {

                                //set date once ordered
                                itemClass.setItemDate(year + "-" + month + "-" + dayofmonth);
                            }
                        }, year, month, day);
                itemDatePicker.show();

            }
        });

        SelectImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                imageChooser();

            }
        });

        TakePhotoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(AddNewItemPage.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    final String[] permissions = {Manifest.permission.CAMERA};

                    ActivityCompat.requestPermissions(AddNewItemPage.this, permissions, REQUEST_IMAGE_CAPTURE_PERMISSION);
                } else {
                    takePhoto();
                }

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (AddNewItemPage.this, ViewAllItemsPage.class);
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == REQUEST_IMAGE_CAPTURE && data !=null){
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        IVPreviewImage.setImageBitmap(bitmap);

    }

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE_PERMISSION && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            takePhoto();
        }

    }

    private void takePhoto(){

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    /*
    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
        storeReference.child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            storeReference.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                }
                            });
        }
    }

     */


/*
        cover = (findViewById(R.id.coverImage));
        fab = (findViewById(R.id.floatingActionButton));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                ImagePicker.with(this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }

        });

 */




    // this function is triggered when user
    // selects the image from the imageChooser
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);


    }

     */







        /*
                Category = (Spinner)findViewById(R.id.categorySpinner);
        adapter = new ArrayAdapter<String>(AddNewItemPage.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        spinner = (Spinner)findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewItemPage.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }


    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
*/



}





