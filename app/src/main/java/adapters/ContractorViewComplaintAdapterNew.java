package adapters;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Databasehelper;
import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

import util.Complaint;

public class ContractorViewComplaintAdapterNew extends RecyclerView.Adapter<ContractorViewComplaintAdapterNew.ViewHolder> {


    private Context context;
    private List<Complaint> complaintList;
    private Databasehelper DBHelper;

    public ContractorViewComplaintAdapterNew(Context context, List<Complaint> complaintList)
    {
        this.context=context;
        this.complaintList=complaintList;
        this.DBHelper=new Databasehelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contractor_complaint_layout_new , viewGroup, false);

        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Complaint current=complaintList.get(i);
        viewHolder.complaint_id.setText("ID: "+current.getID());
        viewHolder.complaint_title.setText("TITLE: "+current.getTitle());
        viewHolder.complaint_domain.setText("Domain: "+current.getDomain());
        viewHolder.complaint_bid.setText("Tender Amount: ₹"+current.getBid_amt());

        if(current.getStatus_description().equals("Completed"))
        {
            viewHolder.completed_text.setVisibility(View.VISIBLE);
            viewHolder.complete_button.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.completed_text.setVisibility(View.GONE);
            viewHolder.complete_button.setVisibility(View.VISIBLE);
        }

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
        Button complete_button;
        TextView completed_text;
        Button describe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            complaint_id=(TextView)itemView.findViewById(R.id.con_complaint_id);
            complaint_title=(TextView)itemView.findViewById(R.id.con_complaint_title);
            complaint_domain=(TextView)itemView.findViewById(R.id.con_complaint_domain);
            complaint_bid=(TextView)itemView.findViewById(R.id.con_complaint_bid);
            complete_button=(Button)itemView.findViewById(R.id.con_completed_button);
            completed_text=(TextView)itemView.findViewById(R.id.con_completed_message);
            describe=(Button)itemView.findViewById(R.id.description_button);

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

            complete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog;
                    AlertDialog.Builder dialog_builder=new AlertDialog.Builder(context);
                    dialog_builder.setTitle("Confirmation");
                    dialog_builder.setMessage("Are you sure? You will not be allowed to change later");
                    dialog_builder.setCancelable(false);
                    dialog_builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Complaint current=complaintList.get(getAdapterPosition());
                            DBHelper.setCompleted(current);
                            complete_button.setVisibility(View.GONE);
                            completed_text.setVisibility(View.VISIBLE);
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

        }

        @Override
        public void onClick(View v) {
            //do nothing
        }
    }
}
