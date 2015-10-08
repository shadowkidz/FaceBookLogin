package skyace.testloginpage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by L335A11 on 7/10/2015.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        gettingHashKey();
    }
    public void gettingHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("skyace.testloginpage",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature: info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
