package com.example.user.moviesearchapp.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.moviesearchapp.R;
import com.example.user.moviesearchapp.model.Search;
import com.example.user.moviesearchapp.MainActivity;
import com.example.user.moviesearchapp.util.ImageDownloader;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.SearchViewHolder> {
   private List<Search> searchList;

    // Constructors
    public MovieAdapter(MainActivity mainActivity, List<Search> searchList) {
        this.searchList = searchList;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item_movie, null);
            return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bindViewHolder(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchList == null ? 0 : searchList.size();
    }

    public void setItems(List<Search> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    public void setItem(
            int index,
            List<Search> searchList
    ) {
        this.searchList = searchList;

        notifyItemChanged(index);
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        public final ImageView posters;
        public final TextView years;

        SearchViewHolder(View itemView) {
            super(itemView);

            posters = itemView.findViewById(R.id.iv_movie);
            years = itemView.findViewById(R.id.tv_movie);
        }

        public void bindViewHolder(Search search) {
            years.setText(search.getYear());

            if (!TextUtils.isEmpty(search.getPoster())) {
                ImageDownloader.download(search.getPoster(), posters);
            }
        }
    }

}


