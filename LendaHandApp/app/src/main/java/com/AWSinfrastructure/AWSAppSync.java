package com.AWSinfrastructure;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

import javax.annotation.Nonnull;

import type.CreateBlogInput;

public class AWSAppSync extends AppCompatActivity {

    private AWSAppSyncClient AppSync; //AWS APP Sync Client
    private AppSyncSubscriptionCall subscriptionWatcher;


    public void initialize() {
        AppSync = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
    }
    //AWS runMutation methods (move to new class later on?)
    public void runMutation(){
        CreateBlogInput createBlogInput = CreateBlogInput.builder().
                name("Use AppSync").
                id("Realtime and Offline").
                build();

        AppSync.mutate(CreateBlogMutation.builder().input(createBlogInput).build())
                .enqueue(mutationCallback);
    }

    private GraphQLCall.Callback<CreateBlogMutation.Data> mutationCallback = new GraphQLCall.Callback<CreateBlogMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateBlogMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };

    //AWS runQuery methods (move later?)

    public void runQuery(){
        AppSync.query(ListBlogsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(blogsCallback);
    }

    private GraphQLCall.Callback<ListBlogsQuery.Data> blogsCallback = new GraphQLCall.Callback<ListBlogsQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListBlogsQuery.Data> response) {
            Log.i("Results", response.data().listBlogs().items().toString());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("ERROR", e.toString());
        }
    };

    //AWS subscribe methods

    private void subscribe(){
        OnCreateBlogSubscription subscription = OnCreateBlogSubscription.builder().build();
        subscriptionWatcher = AppSync.subscribe(subscription);
        subscriptionWatcher.execute(subCallback);
    }

    private AppSyncSubscriptionCall.Callback subCallback = new AppSyncSubscriptionCall.Callback() {
        @Override
        public void onResponse(@Nonnull Response response) {
            Log.i("Response", response.data().toString());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

        @Override
        public void onCompleted() {
            Log.i("Completed", "Subscription completed");
        }
    };
}
