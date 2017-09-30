package neobis.test;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by admin on 9/30/17.
 */

public class UploadActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    Button addQ, photo;
    EditText question;
    Switch checkOrRadio;
    SegmentedGroup segmented2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        setTitle("Загрузить");
        addQ = (Button) findViewById(R.id.addQuestion);
        photo = (Button) findViewById(R.id.photo);
        question = (EditText) findViewById(R.id.text);
        photo.setOnClickListener(onClick);
        checkOrRadio = (Switch) findViewById(R.id.checOrRadiio);
        addQ.setVisibility(View.GONE);
        checkOrRadio.setVisibility(View.GONE);
        segmented2 = (SegmentedGroup)findViewById(R.id.segmented2);
        segmented2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.open:
                addQ.setVisibility(View.VISIBLE);
                checkOrRadio.setVisibility(View.GONE);
                break;
            case R.id.multiple:
                addQ.setVisibility(View.GONE);
                checkOrRadio.setVisibility(View.VISIBLE);
                checkOrRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            addQ.setVisibility(View.VISIBLE);
                        } else {

                        }
                    }
                });
                break;
            default:
                // Nothing to do
        }
    }


    public View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectImage();
        }

    };

    private void selectImage() {
        if (photo.isEnabled()) {
            final CharSequence[] items = {"Take Photo", "Choose from Library",
                    "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
            builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("Take Photo")) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                    } else if (items[item].equals("Choose from Library")) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(
                                Intent.createChooser(intent, "Select File"),
                                SELECT_FILE);
                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        } else {
            checkPermission();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 121);
        }
    }
}



