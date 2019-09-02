package com.eram.manager.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.eram.manager.R;
import com.eram.manager.api.ICallApi;
import com.eram.manager.ui.login.LoginViewModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback implements AppConstants {
    private final ICallApi iCallApi;
    private final LoginViewModel loginViewModel;

    // You should use the CancellationSignal method whenever your app can no longer process user input, for example when your app goes
    // into the background. If you don’t use this method, then other apps will be unable to access the touch sensor, including the lockscreen!//

    private CancellationSignal cancellationSignal;
    private Context context;

    public FingerprintHandler(Context mContext, ICallApi mICallApi, LoginViewModel mLoginViewModel) {
        context = mContext;
        iCallApi = mICallApi;
        loginViewModel = mLoginViewModel;
    }

    //Implement the startAuth method, which is responsible for starting the fingerprint authentication process//
    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    //onAuthenticationError is called when a fatal error has occurred. It provides the error code and error message as its parameters//
    public void onAuthenticationError(int errMsgId, CharSequence errString) {

        //I’m going to display the results of fingerprint authentication as a series of toasts.
        //Here, I’m creating the message that’ll be displayed if an error occurs//
        Toast.makeText(context, "تأیید اعتبار با خطا مواجه شد\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    //onAuthenticationFailed is called when the fingerprint doesn’t match with any of the fingerprints registered on the device//
    public void onAuthenticationFailed() {
        Toast.makeText(context, "تأیید اعتبار ناموفق بود", Toast.LENGTH_LONG).show();
    }

    @Override
    //onAuthenticationHelp is called when a non-fatal error has occurred. This method provides additional information about the error,
    //so to provide the user with as much feedback as possible I’m incorporating this information into my toast//
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "تائید اعتبار نیاز به \n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    //onAuthenticationSucceeded is called when a fingerprint has been successfully matched to one of the fingerprints stored on the user’s device//
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
//        Toast.makeText(context, "موفقیت آمیز بود!", Toast.LENGTH_LONG).show();
        try {
            String passwordE = TextEncrypter.MD5String(loginViewModel.getDataManager().getUsername() + loginViewModel.getDataManager().getPassword());
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(REQUEST_KEY_PASSWORD_LOGIN, passwordE)
                    .addFormDataPart(REQUEST_KEY_USERNAME_LOGIN, loginViewModel.getDataManager().getUsername())
                    .addFormDataPart(REQUEST_KEY_APPLICATION_CODE_LOGIN, REQUEST_NOOR_PAYMENT)
                    .build();
            if (LOGTRUE)
                Log.d("mPARAMS login::::  ", requestBody.toString());
//            loginViewModel.login(iCallApi, context, requestBody);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

}

