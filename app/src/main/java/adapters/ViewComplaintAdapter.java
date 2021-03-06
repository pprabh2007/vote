package adapters;



import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ComplaintDetailsAcitivity;
import com.example.myapplication.Databasehelper;
import com.example.myapplication.R;
import com.example.myapplication.ViewComplaintActivity;

import org.w3c.dom.Text;

import java.util.List;

import util.Complaint;
import util.User;

import static android.view.View.GONE;

public class ViewComplaintAdapter extends RecyclerView.Adapter<ViewComplaintAdapter.ViewHolder> {


    private Context context;
    private List<Complaint> complaintList;
    private Databasehelper DBHelper;
    private User THIS_USER_OBJECT;

    public ViewComplaintAdapter(Context context, List<Complaint> complaintList, User THIS_USER_OBJECT)
    {
        this.context=context;
        this.complaintList=complaintList;
        this.DBHelper=new Databasehelper(context);
        this.THIS_USER_OBJECT=THIS_USER_OBJECT;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint_layout , viewGroup, false);

        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Complaint current=complaintList.get(i);
        viewHolder.comp_title.setText("TITLE: "+current.getTitle());
        viewHolder.comp_id.setText("ID: "+current.getID());
        viewHolder.comp_date.setText("Date: "+current.getDate());
        viewHolder.comp_domain.setText("Domain: "+current.getDomain());
        viewHolder.comp_status.setText("Status: "+current.getStatus_description());

        if(current.hasUserUpvote(THIS_USER_OBJECT))
        {
            viewHolder.upvote_button.setVisibility(View.GONE);
            viewHolder.upvote_message.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolder.upvote_button.setVisibility(View.VISIBLE);
            viewHolder.upvote_message.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView comp_title;
        TextView comp_id;
        TextView comp_date;
        TextView comp_domain;
        TextView comp_status;
        Button upvote_button;
        TextView upvote_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            comp_title=(TextView)itemView.findViewById(R.id.complaint_title);
            comp_id=(TextView)itemView.findViewById(R.id.complaint_id);
            comp_date=(TextView)itemView.findViewById(R.id.complaint_date);
            comp_domain=(TextView)itemView.findViewById(R.id.complaint_domain);
            comp_status=(TextView)itemView.findViewById(R.id.complaint_status);
            upvote_button=(Button)itemView.findViewById(R.id.upvote_button);
            upvote_message=(TextView)itemView.findViewById(R.id.upvote_message);
            upvote_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DBHelper.upvoteIssue(complaintList.get(getAdapterPosition()), THIS_USER_OBJECT);
                    upvote_button.setVisibility(GONE);
                    upvote_message.setVisibility(View.VISIBLE);
                }
            });

        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            Intent intent=new Intent(context, ComplaintDetailsAcitivity.class);
            intent.putExtra("title", complaintList.get(position).getTitle());
            intent.putExtra("description", complaintList.get(position).getDescription());
            intent.putExtra("id", complaintList.get(position).getID());
            intent.putExtra("status", complaintList.get(position).getStatus_description());
            intent.putExtra("upvotes", complaintList.get(position).getUpvotes());
            intent.putExtra("date", complaintList.get(position).getDate());
            context.startActivity(intent);

        }
    }
}
