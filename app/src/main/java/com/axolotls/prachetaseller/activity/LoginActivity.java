package com.axolotls.prachetaseller.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.axolotls.prachetaseller.R;
import com.axolotls.prachetaseller.helper.ApiConfig;
import com.axolotls.prachetaseller.helper.AppController;
import com.axolotls.prachetaseller.helper.Constant;
import com.axolotls.prachetaseller.helper.Session;
import com.axolotls.prachetaseller.helper.Utils;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText edtLoginPassword, edtLoginMobile, edtProfileOldPassword, edtProfileNewPassword, edtProfileConfirmNewPassword, edtFCode, edtforgotmobile, edtResetPass, edtResetCPass;

    Button btnLogin, btnChangePassword, btnrecover, btnResetPass;

    String from, fromto, mobile;

    LinearLayout lytlogin, lyt_update_password, lytforgot, lytResetPass;

    Session session;
    Activity activity;
    TextView tvPrivacy;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            activity = LoginActivity.this;
            session = new Session(activity);

            btnLogin = findViewById(R.id.btnlogin);
            btnChangePassword = findViewById(R.id.btnChangePassword);
            btnrecover = findViewById(R.id.btnrecover);
            btnResetPass = findViewById(R.id.btnResetPass);

            edtLoginPassword = findViewById(R.id.edtLoginPassword);
            edtLoginMobile = findViewById(R.id.edtLoginMobile);
            edtFCode = findViewById(R.id.edtFCode);
            edtforgotmobile = findViewById(R.id.edtforgotmobile);
            edtResetPass = findViewById(R.id.edtResetPass);
            edtResetCPass = findViewById(R.id.edtResetCPass);
            tvPrivacy = findViewById(R.id.tvPrivacy);

            //layouts
            lytlogin = findViewById(R.id.lytlogin);
            lyt_update_password = findViewById(R.id.lyt_update_password);
            lytforgot = findViewById(R.id.lytforgot);
            lytResetPass = findViewById(R.id.lytResetPass);

            from = getIntent().getStringExtra("from");
            fromto = getIntent().getStringExtra("fromto");
            mobile = getIntent().getStringExtra("txtmobile");

            edtProfileOldPassword = findViewById(R.id.edtProfileOldPassword);
            edtProfileNewPassword = findViewById(R.id.edtProfileNewPassword);
            edtProfileConfirmNewPassword = findViewById(R.id.edtProfileConfirmNewPassword);

            edtLoginMobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mobile, 0, 0, 0);

            edtLoginPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtLoginPassword);

            edtLoginPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);

            Utils.setHideShowPassword(edtLoginPassword);

            edtResetPass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtResetPass);

            edtResetCPass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtResetCPass);

            edtProfileOldPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtProfileOldPassword);

            edtProfileNewPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtProfileNewPassword);

            edtProfileConfirmNewPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_show, 0);
            Utils.setHideShowPassword(edtProfileConfirmNewPassword);

            setAppLocal("en");

            if (from != null) {
                switch (from) {
                    case "lyt_update_password":

                        lytlogin.setVisibility(View.GONE);
                        lytforgot.setVisibility(View.GONE);
                        lytResetPass.setVisibility(View.GONE);
                        lyt_update_password.setVisibility(View.VISIBLE);

                        setSnackBar(activity, getString(R.string.change_password_msg), getString(R.string.ok), Color.YELLOW);
                        break;
                    case "lytforgot":

                        lytlogin.setVisibility(View.GONE);
                        lytforgot.setVisibility(View.VISIBLE);
                        lytResetPass.setVisibility(View.GONE);
                        lyt_update_password.setVisibility(View.GONE);
                        break;
                    case "lytResetPass":

                        lytlogin.setVisibility(View.GONE);
                        lytforgot.setVisibility(View.GONE);
                        lytResetPass.setVisibility(View.VISIBLE);
                        lyt_update_password.setVisibility(View.GONE);

                        break;
                    case "login":
                        lytlogin.setVisibility(View.VISIBLE);
                        lytforgot.setVisibility(View.GONE);
                        lytResetPass.setVisibility(View.GONE);
                        lyt_update_password.setVisibility(View.GONE);
                        break;
                }
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            } else {
                lytlogin.setVisibility(View.VISIBLE);
                lytforgot.setVisibility(View.GONE);
                lytResetPass.setVisibility(View.GONE);
                lyt_update_password.setVisibility(View.GONE);
            }

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        if (!token.equals(session.getData(Constant.FCM_ID))) {
                            AppController.getInstance().setDeviceToken(token);
                        }
                    });

            PrivacyPolicy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAppLocal(String languageCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(languageCode.toLowerCase()));
        resources.updateConfiguration(configuration, dm);
    }


    public void OnBtnClick(View view) {
        if (AppController.isConnected(activity)) {
            int id = view.getId();

            if (id == R.id.btnlogin) {
                {
                    String mobile = edtLoginMobile.getText().toString();
                    String password = edtLoginPassword.getText().toString();

                    if (ApiConfig.CheckValidation(mobile, false, false)) {
                        edtLoginMobile.setError(getString(R.string.enter_mobile_number));
                    } else if (ApiConfig.CheckValidation(password, false, false)) {
                        edtLoginPassword.setError(getString(R.string.password_required));
                    } else if (AppController.isConnected(activity)) {

                        Map<String, String> params = new HashMap<>();
                        params.put(Constant.LOGIN, Constant.GetVal);
                        params.put(Constant.MOBILE, mobile);
                        params.put(Constant.PASSWORD, password);
                        params.put(Constant.FCM_ID, "" + AppController.getInstance().getDeviceToken());

                        ApiConfig.RequestToVolley((result, response) -> {
                            if (result) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (!jsonObject.getBoolean(Constant.ERROR)) {
                                        StartMainActivity(jsonObject.getJSONArray(Constant.DATA).getJSONObject(0));
                                    } else {
                                        setSnackBar(activity, jsonObject.getString(Constant.MESSAGE), getString(R.string.ok), Color.RED);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, activity, Constant.MAIN_URL, params, true);

                    }
                }
            }
        } else {
            setSnackBar(activity, getString(R.string.no_internet_message), getString(R.string.retry), Color.RED);
        }
    }

    public void StartMainActivity(JSONObject jsonObject) {
        try {
            new Session(activity).createUserLoginSession(
                    jsonObject.getString(Constant.ID),
                    jsonObject.getString(Constant.FCM_ID),
                    jsonObject.getString(Constant.NAME),
                    jsonObject.getString(Constant.STORE_NAME),
                    jsonObject.getString(Constant.EMAIL),
                    jsonObject.getString(Constant.PASSWORD),
                    jsonObject.getString(Constant.BALANCE),
                    jsonObject.getString(Constant.CUSTOMER_PRIVACY),
                    jsonObject.getString(Constant.LOGO),
                    jsonObject.getString(Constant.VIEW_ORDER_OTP),
                    jsonObject.getString(Constant.ASSIGN_DELIVERY_BOY),
                    jsonObject.getString(Constant.STATUS));

            invalidateOptionsMenu();

            Intent intent = new Intent(activity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setSnackBar(final Activity activity, String message, String action, int color) {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(action, view -> {
            snackbar.dismiss();
            startActivity(new Intent(activity, LoginActivity.class).putExtra(Constant.FROM, "login"));
        });
        snackbar.setActionTextColor(color);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
    }

    public void PrivacyPolicy() {
        tvPrivacy.setClickable(true);
        tvPrivacy.setMovementMethod(LinkMovementMethod.getInstance());

        String message = getString(R.string.msg_privacy_terms);
        String s2 = getString(R.string.terms_conditions);
        String s1 = getString(R.string.privacy_policy);

        final Spannable wordtoSpan = new SpannableString(message);

        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent privacy = new Intent(LoginActivity.this, WebViewActivity.class);
                privacy.putExtra("link", Constant.SELLER_POLICY);
                privacy.putExtra("title", getString(R.string.privacy_policy));
                startActivity(privacy);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                ds.isUnderlineText();
            }
        }, message.indexOf(s1), message.indexOf(s1) + s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent terms = new Intent(LoginActivity.this, WebViewActivity.class);
                terms.putExtra("link", Constant.SELLER_TERMS);
                terms.putExtra("title", getString(R.string.terms_conditions));
                startActivity(terms);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                ds.isUnderlineText();
            }
        }, message.indexOf(s2), message.indexOf(s2) + s2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPrivacy.setText(wordtoSpan);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}