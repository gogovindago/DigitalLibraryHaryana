package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.MyLoaders;

public class SignupActivity extends BaseActivity {
    private MyLoaders myLoaders;
    String refreshedToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        myLoaders = new MyLoaders(getApplicationContext());
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        CSPreferences.putBolean(SignupActivity.this, "skiplogin", false);

    }


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }
}