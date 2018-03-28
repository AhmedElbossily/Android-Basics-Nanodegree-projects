package elbossily.guardiannews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmed on 3/2/2018.
 */

public class NewsAdapter extends ArrayAdapter<NewsData> {
    public NewsAdapter(@NonNull Context context, @NonNull List<NewsData> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsData currentNews = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);

        // Topic Title
        TextView titleTextView = convertView.findViewById(R.id.title_id);
        titleTextView.setText(currentNews.getTitleName());
        //Section Name
        TextView sectionTextView = convertView.findViewById(R.id.section_id);
        sectionTextView.setText(currentNews.getSection());
        //author name
        TextView authorTextView = convertView.findViewById(R.id.author_id);
        authorTextView.setText(currentNews.getAuthorName());
        //date
        TextView dateTextView = convertView.findViewById(R.id.date_id);
        dateTextView.setText(currentNews.getPublicationDate());

        return convertView;
    }
}
