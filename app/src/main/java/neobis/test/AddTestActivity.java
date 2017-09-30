package neobis.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by admin on 9/30/17.
 */

public class AddTestActivity extends AppCompatActivity {
    ListView listView;
    Button addTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtest);
        listView = (ListView)findViewById(R.id.listView);
        addTest = (Button)findViewById(R.id.addTest);
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTestActivity.this, UploadActivity.class);
                startActivity(intent);
                /*public void onAdd(View view) {
                    if(catSpinner.getAdapter() != null) {
                        if (!subName.getText().toString().isEmpty() && !imagePath.getText().toString().isEmpty()) {
                            if (checkSubcategoryExists()) {
                                return;
                            }
                            AdapterListSubCategory adapterlist = ((AdapterListSubCategory) listOfSubCat.getAdapter());
                            subCategories.add(new SubCategory(subName.getText().toString(), imagePath.getText().toString()));
                            expand();

                            adapterlist.notifyDataSetChanged();
                            subName.setText("");
                            imagePath.setText("");
                        } else {
                            showMessage("Имеются пустые поля");
                        }
                    }else{
                        showMessage("выберите категорию");
                    }
                }*/
            }
        });

    }

}
