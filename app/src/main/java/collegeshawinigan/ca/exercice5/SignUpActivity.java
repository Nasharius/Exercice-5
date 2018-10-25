package collegeshawinigan.ca.exercice5;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText mTextEditPass;
    TextInputEditText mTextEditEmail;
    TextInputEditText mTextEditConfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.index);
        mTextEditPass = findViewById(R.id.signup_edittextp);
        mTextEditEmail = findViewById(R.id.signup_email);
        mTextEditConfPass = findViewById(R.id.signup_edittextcp);
        ValidatesFields(mTextEditPass, false);
        ValidatesFields(mTextEditConfPass, false);
        ValidatesFields(mTextEditEmail, true);


    }

    public void ValidatesFields(final TextInputEditText txt,final boolean email) {

            txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!email) {
                        if (!hasFocus) {
                            if (txt.getText().toString().trim().length() < 8) {
                                txt.setError("Minimum of 8 characters !");
                            } else {
                                txt.setError(null);
                            }
                        }
                    } if(!hasFocus){
                        if (txt.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(txt.getText().toString()).matches()) {
                            txt.setError("Enter a valid email address");
                        }
                        else {
                            txt.setError(null);
                        }
                    }
                }
            });
    }
}