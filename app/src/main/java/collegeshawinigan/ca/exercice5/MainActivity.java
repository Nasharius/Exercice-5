package collegeshawinigan.ca.exercice5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int CODE = 1;
    Context mContext = this;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        String message = intent.getStringExtra(SignUpActivity.EXTRA_MESSAGE);
        if(message != null) {
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }


    }

    public void onClickSignUp(View v)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent,CODE);
        //Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to

        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<Integer> mSelectedItems = data.getExtras().getIntegerArrayList("items");
                String []mChoices = getResources().getStringArray(R.array.choices);
                String []mResult = new String[mSelectedItems.size()];
                for(int i = 0; i < mSelectedItems.size(); i++){
                    mResult[i] = mChoices[mSelectedItems.get(i)];
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Themes").setItems(mResult, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        }
    }
}