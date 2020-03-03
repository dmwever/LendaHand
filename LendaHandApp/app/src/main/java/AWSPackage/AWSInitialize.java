/*package AWSPackage;

import android.util.Log;

import com.amazonaws.amplify.generated.graphql.CreateBlogMutation;
import com.amazonaws.amplify.generated.graphql.ListBlogsQuery;
import com.amazonaws.amplify.generated.graphql.OnCreateBlogSubscription;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AppSyncSubscriptionCall;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.amazonaws.mobile.client.AWSMobileClient

public class AWSInitialize {

    private AWSMobileClient AppSync; //AWS APP Sync Client

    AWSMobileClient.getInstance().addUserStateListener(new UserStateListener() {
        @Override
        public void onUserStateChanged(UserStateDetails userStateDetails) {
            switch (userStateDetails.getUserState()){
                case GUEST:
                    Log.i("userState", "user is in guest mode");
                    break;
                case SIGNED_OUT:
                    Log.i("userState", "user is signed out");
                    break;
                case SIGNED_IN:
                    Log.i("userState", "user is signed in");
                    break;
                case SIGNED_OUT_USER_POOLS_TOKENS_INVALID:
                    Log.i("userState", "need to login again");
                    break;
                case SIGNED_OUT_FEDERATED_TOKENS_INVALID:
                    Log.i("userState", "user logged in via federation, but currently needs new tokens");
                    break;
                default:
                    Log.e("userState", "unsupported");
            }
        }
    });

}
*/