package com.example.apilistapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {
    private TextView detailPostId;
    private TextView detailUserId;
    private TextView detailTitle;
    private TextView detailBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Post Details");
        setSupportActionBar(toolbar);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        detailPostId = findViewById(R.id.detailPostId);
        detailUserId = findViewById(R.id.detailUserId);
        detailTitle = findViewById(R.id.detailTitle);
        detailBody = findViewById(R.id.detailBody);

        // Get post from intent
        Post post = (Post) getIntent().getSerializableExtra("post");

        if (post != null) {
            displayPostDetails(post);
        }
    }

    private void displayPostDetails(Post post) {
        detailPostId.setText(getString(R.string.post_id) + " " + post.getId());
        detailUserId.setText(getString(R.string.post_user_id) + " " + post.getUserId());
        detailTitle.setText(post.getTitle());
        detailBody.setText(post.getBody());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
