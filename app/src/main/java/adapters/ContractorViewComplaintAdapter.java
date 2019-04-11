package adapters;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import util.Complaint;

public class ContractorViewComplaintAdapter extends RecyclerView.Adapter<ContractorViewComplaintAdapter.ViewHolder> {


    private Context context;
    private List<Complaint> complaintList;


    public ContractorViewComplaintAdapter(Context context, List<Complaint> complaintList)
    {
        this.context=context;
        this.complaintList=complaintList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contractor_complaint_layout , viewGroup, false);

        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Complaint current=complaintList.get(i);
        viewHolder.complaint_id.setText("ID: "+current.getID());
        viewHolder.complaint_title.setText("TITLE: "+current.getTitle());
        viewHolder.complaint_domain.setText("Domain: "+current.getDomain());
        viewHolder.complaint_contractor.setText("Contractor: "+current.getContractor());
        viewHolder.complaint_bid.setText("Best Bid: "+((current.getBid_amt()==-1)?"None":"₹"+current.getBid_amt()));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView complaint_id;
        TextView complaint_title;
        TextView complaint_domain;
        TextView complaint_contractor;
        TextView complaint_bid;
        Button accept_bid_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            complaint_id=(TextView)itemView.findViewById(R.id.rep_complaint_id);
            complaint_title=(TextView)itemView.findViewById(R.id.rep_complaint_title);
            complaint_domain=(TextView)itemView.findViewById(R.id.rep_complaint_domain);
            complaint_contractor=(TextView)itemView.findViewById(R.id.rep_complaint_contractor);
            complaint_bid=(TextView)itemView.findViewById(R.id.rep_complaint_bid);
            accept_bid_button=(Button)itemView.findViewById(R.id.rep_accept_bid_button);
            //TODO implement accept bid button backend
        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            Log.e("HELLO", "HELLO");
            //do nothing
        }
    }
}
