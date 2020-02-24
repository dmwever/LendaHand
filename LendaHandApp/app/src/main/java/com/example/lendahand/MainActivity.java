package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

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
import com.google.android.material.button.MaterialButton;

import javax.annotation.Nonnull;

import type.CreateBlogInput;

public class MainActivity extends AppCompatActivity {

    private AWSAppSyncClient AppSync; //AWS APP Sync Client

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AWS client creation
        AppSync = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        runMutation();
        runQuery();
        subscribe();


        addTemporaryButtons();
    }

    private void addTemporaryButtons() {

        //Adding button to Sign Up A Volunteer
        //STEP 1: Add reference to button using R.id
        MaterialButton signupVolunteer = findViewById(R.id.signup_a_volunteer);

        //STEP 2: Set onClickListener for YOUR button
        signupVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //STEP 3: Create Intent for your class
                Intent volunteerSignupScreen = new Intent(v.getContext(), Volunteer_Signup.class);
                //STEP 4: Start your Activity
                startActivity(volunteerSignupScreen);
            }
        });


        //Adding button to Create Service Opportuntity
        MaterialButton createServiceOp = findViewById(R.id.create_service_opportunity);
        createServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpGenInfo.class);
                startActivity(createServiceOpScreen);

            }
        });

        //Adding button to Create Service Organization
        MaterialButton createServiceOrg = (MaterialButton) findViewById(R.id.create_service_org);
        createServiceOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createServiceOrgScreen = new Intent(v.getContext(), org_signup1.class);
                startActivity(createServiceOrgScreen);

            }
        });
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

    private AppSyncSubscriptionCall subscriptionWatcher;

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
