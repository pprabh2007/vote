package adapters;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ViewUserActivity;


import java.util.List;

import util.User;

public class ViewUserAdapter extends RecyclerView.Adapter<ViewUserAdapter.ViewHolder> {


    private Context context;
    private List<User> userList;


    public ViewUserAdapter(Context context, List<User> userList)
    {
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_layout , viewGroup, false);

        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        User current=userList.get(i);
        viewHolder.user_name.setText("Name: "+current.getName());
        viewHolder.user_id.setText("ID: "+current.getVoterID());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView user_name;
        TextView user_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            user_name=(TextView)itemView.findViewById(R.id.voter_name1);
            user_id=(TextView)itemView.findViewById(R.id.voter_serial);
        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            Intent intent=new Intent(context, ViewUserActivity.class);
            intent.putExtra("Name", userList.get(position).getName());
            intent.putExtra("VoterID", userList.get(position).getVoterID());
            intent.putExtra("DOB", userList.get(position).getDate());
            context.startActivity(intent);

        }
    }
}
