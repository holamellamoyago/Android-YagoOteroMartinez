package com.example.apilistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(@NonNull Context context, @NonNull List<Post> posts) {
        super(context, 0, posts);
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        }

        Post currentPost = posts.get(position);

        TextView titleText = listItem.findViewById(R.id.postTitleText);
        TextView bodyPreview = listItem.findViewById(R.id.postBodyPreview);
        TextView idText = listItem.findViewById(R.id.postIdText);

        titleText.setText(currentPost.getTitle());
        bodyPreview.setText(currentPost.getBody());
        idText.setText("ID: " + currentPost.getId() + " | User: " + currentPost.getUserId());

        return listItem;
    }
}
