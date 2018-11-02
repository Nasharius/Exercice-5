package collegeshawinigan.ca.exercice5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.exercice5.MESSAGE";

    TextInputEditText mTextEditPass;
    TextInputEditText mTextEditEmail;
    TextInputEditText mTextEditConfPass;
    ArrayList<String> mSelectedTheme;
    static int mCount;
    Button mSend;
    Button mCancel;
    Toast showToast;
    Context mContext = this;
    boolean mPass,mConf,mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign Up");
        setContentView(R.layout.activity_sign_up);
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.index);
        final String mChoices[] = getResources().getStringArray(R.array.choices);

        mTextEditPass = findViewById(R.id.signup_edittextp);
        mTextEditEmail = findViewById(R.id.signup_email);
        mTextEditConfPass = findViewById(R.id.signup_edittextcp);
        mPass = ValidatesFields(mTextEditPass, false);
        mConf = ValidatesFields(mTextEditConfPass, false);
        mEmail = ValidatesFields(mTextEditEmail, true);
        mSend = findViewById(R.id.signup_send);
        mCancel = findViewById(R.id.signup_cancel);
        showToast = new Toast(SignUpActivity.this);
        mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
        mSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                mPass = ValidatesFields(mTextEditPass, false);
                mConf = ValidatesFields(mTextEditConfPass, false);
                mEmail = ValidatesFields(mTextEditEmail, true);
                if(mPass && mEmail && mConf) {

                   final ArrayList<Integer> mSelectedItem = new ArrayList<Integer>();

                    mCount = 0;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

                    builder.setTitle("Choose a theme")
                    .setMultiChoiceItems(mChoices, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id, boolean isChecked) {
                            if( isChecked){
                                if(mCount <= 3)
                                    mSelectedItem.add(id);
                                    mCount++;
                            }else if(mSelectedItem.contains(id)){

                                    mSelectedItem.remove(Integer.valueOf(id));
                                    mCount--;
                            }

                        }
                    }).setPositiveButton("Save themes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            String selectedIndex = "";
                            for(Integer i : mSelectedItem){
                                selectedIndex += i + ", ";
                                System.out.println("ICI " + selectedIndex + mSelectedItem.size());
                            }
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("items", mSelectedItem);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).show();
                }else{
                    Toast.makeText(mContext, "You have some errors in the above fields !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean ValidatesFields(final TextInputEditText txt,final boolean email) {

            txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!email) {
                        if (!hasFocus) {
                            if (txt.getText().toString().trim().length() < 8) {
                                txt.setError("Minimum of 8 characters !");
                            } else //if (txt.getText().toString() == mTextEditPass.getText().toString()) {
                                txt.setError(null);

                            /*}else if(!(txt.getText().toString() == mTextEditPass.getText().toString())){
                                txt.setError("Password do not match !");
                            }*/
                        }
                    } else if (email) {
                        if (!hasFocus) {
                            if (txt.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(txt.getText().toString()).matches()) {
                                txt.setError("Enter a valid email address");
                            } else {
                                txt.setError(null);
                            }
                        }
                    }
                }
            });

            if (txt.getError() == null && !(txt.getText().toString().isEmpty())){
                return true;
            }else{
                return false;
            }
    }
}