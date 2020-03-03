/*package AWSPackage;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient

public final class AWSMobileClient
            extends java.lang.Object
            implements AWSCredentialsProvider
//The AWSMobileClient provides client APIs and building blocks for developers who want to create user authentication experiences. This includes declarative methods for performing authentication actions, a simple “drop-in auth” UI for performing common tasks, automatic token and credentials management, and state tracking with notifications for performing workflows in your application when users have authenticated. The following demonstrates a simple sample usage inside of MainActivity.java onCreate method.
            AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
        public void onResult(UserStateDetails userStateDetails) {
            switch (userStateDetails.getUserState()) {
                case SIGNED_IN:
                    break;
                case SIGNED_OUT:
                    try {
                        AWSMobileClient.getInstance().showSignIn(MainActivity.this);
                    } catch (Exception e) {
                        Log.e("TAG", "", e);
                    }
                    break;
                default:
                    Log.w("Unhandled state see UserState for a list of states");
                    break;
            }
        }
    })
}
}
*/