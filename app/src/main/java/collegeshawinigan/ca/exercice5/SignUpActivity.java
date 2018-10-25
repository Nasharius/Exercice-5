package collegeshawinigan.ca.exercice5;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.exercice5.MESSAGE";

    TextInputEditText mTextEditPass;
    TextInputEditText mTextEditEmail;
    TextInputEditText mTextEditConfPass;
    Button mSend;
    Context mContext = this;
    boolean mPass,mConf,mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.index);

        mTextEditPass = findViewById(R.id.signup_edittextp);
        mTextEditEmail = findViewById(R.id.signup_email);
        mTextEditConfPass = findViewById(R.id.signup_edittextcp);
        mPass = ValidatesFields(mTextEditPass, false);
        mConf = ValidatesFields(mTextEditConfPass, false);
        mEmail = ValidatesFields(mTextEditEmail, true);
        mSend = findViewById(R.id.signup_send);

        mSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                mPass = ValidatesFields(mTextEditPass, false);
                mConf = ValidatesFields(mTextEditConfPass, false);
                mEmail = ValidatesFields(mTextEditEmail, true);
                if(mPass && mEmail && mConf) {
                    Intent myIntent = new Intent(mContext,
                            MainActivity.class);
                    myIntent.putExtra(EXTRA_MESSAGE,"Your account has been successfully created");
                    startActivity(myIntent);
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