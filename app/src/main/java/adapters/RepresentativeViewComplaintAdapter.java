package adapters;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.myapplication.RepresentativeViewComplaintActivity;

import java.util.List;

import util.Complaint;

public class RepresentativeViewComplaintAdapter extends RecyclerView.Adapter<RepresentativeViewComplaintAdapter.ViewHolder> {


    private Context context;
    private List<Complaint> complaintList;
    private Databasehelper DBHelper;


    public RepresentativeViewComplaintAdapter(Context context, List<Complaint> complaintList)
    {
        DBHelper=new Databasehelper(context);
        this.context=context;
        this.complaintList=complaintList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.representative_complaint_layout , viewGroup, false);

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
        TextView assigned_message;
        Button describe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            complaint_id=(TextView)itemView.findViewById(R.id.rep_complaint_id);
            complaint_title=(TextView)itemView.findViewById(R.id.rep_complaint_title);
            complaint_domain=(TextView)itemView.findViewById(R.id.rep_complaint_domain);
            complaint_contractor=(TextView)itemView.findViewById(R.id.rep_complaint_contractor);
            complaint_bid=(TextView)itemView.findViewById(R.id.rep_complaint_bid);
            accept_bid_button=(Button)itemView.findViewById(R.id.rep_accept_bid_button);
            assigned_message=(TextView)itemView.findViewById(R.id.rep_assigned_message);
            describe=(Button)itemView.findViewById(R.id.description_button);
            accept_bid_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog;
                    AlertDialog.Builder dialog_builder=new AlertDialog.Builder(context);
                    dialog_builder.setTitle("Confirmation");
                    dialog_builder.setMessage("Are you sure? You will not be allowed to retract later");
                    dialog_builder.setCancelable(false);
                    dialog_builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Complaint current=complaintList.get(getAdapterPosition());
                            DBHelper.setAssigned(current);
                            accept_bid_button.setVisibility(View.GONE);
                            assigned_message.setVisibility(View.VISIBLE);
                        }
                    });

                    dialog_builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    dialog=dialog_builder.create();
                    dialog.show();

                }
            });
            //TODO implement accept bid button backend

            describe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog;

                    AlertDialog.Builder dialog_builder=new AlertDialog.Builder(context);
                    dialog_builder.setTitle("Description");
                    dialog_builder.setMessage(complaintList.get(getAdapterPosition()).getDescription());

                    dialog_builder.setCancelable(false);
                    dialog_builder.setPositiveButton(R.string.button1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    dialog=dialog_builder.create();
                    dialog.show();

                }
            });
        }

        @Override
        public void onClick(View v) {
            //do nothing
        }
    }
}