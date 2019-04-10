package adapters;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.NewsDetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.ViewNewsActivity;

import java.util.List;

import util.News;

public class ViewNewsAdapter extends RecyclerView.Adapter<ViewNewsAdapter.ViewHolder> {


    private Context context;
    private List<News> newsList;
    Dialog alert;


    public ViewNewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_layout, viewGroup, false);

        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        News current = newsList.get(i);
        viewHolder.comp_title.setText("TITLE: " + current.getTitle());
        viewHolder.comp_date.setText("Date: " + current.getDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView comp_title;

        TextView comp_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            comp_title = (TextView) itemView.findViewById(R.id.news_title_text);
            comp_date = (TextView) itemView.findViewById(R.id.news_title_date);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            final AlertDialog.Builder alert_builder=new AlertDialog.Builder(context);
            alert_builder.setTitle(newsList.get(position).getTitle());
            alert_builder.setMessage(newsList.get(position).getDescription());
            alert_builder.setPositiveButton(R.string.button1,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            alert.cancel();
                        }
                    });

            alert=alert_builder.create();
            alert.show();

            /*Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("title", newsList.get(position).getTitle());
            intent.putExtra("description", newsList.get(position).getDescription());
            intent.putExtra("date", newsList.get(position).getDate());
            context.startActivity(intent);*/

        }

    }
}