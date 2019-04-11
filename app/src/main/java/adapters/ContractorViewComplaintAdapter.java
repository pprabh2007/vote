package adapters;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Databasehelper;
import com.example.myapplication.R;

import java.util.List;

import util.Complaint;
import util.User;

public class ContractorViewComplaintAdapter extends RecyclerView.Adapter<ContractorViewComplaintAdapter.ViewHolder> {


    private Context context;
    private List<Complaint> complaintList;
    private Databasehelper DBHelper;
    private User THIS_USER_OBJECT;

    public ContractorViewComplaintAdapter(Context context, List<Complaint> complaintList, User THIS_USER_OBJECT)
    {
        this.THIS_USER_OBJECT=THIS_USER_OBJECT;
        this.DBHelper=new Databasehelper(context);
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
        Button make_bid_button;
        TextView con_bid_made;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            complaint_id=(TextView)itemView.findViewById(R.id.con_complaint_id);
            complaint_title=(TextView)itemView.findViewById(R.id.con_complaint_title);
            complaint_domain=(TextView)itemView.findViewById(R.id.con_complaint_domain);
            complaint_contractor=(TextView)itemView.findViewById(R.id.con_complaint_contractor);
            complaint_bid=(TextView)itemView.findViewById(R.id.con_complaint_bid);
            con_bid_made=(TextView)itemView.findViewById(R.id.con_bidded_message);
            make_bid_button=(Button)itemView.findViewById(R.id.con_place_bid_button);
            //TODO implement accept bid button backend
            make_bid_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog;

                    AlertDialog.Builder dialog_builder=new AlertDialog.Builder(context);
                    dialog_builder.setTitle("Bidding Amount (in ₹)");

                    LayoutInflater inflater= LayoutInflater.from(context);
                    View dialog_builder_view=inflater.inflate(R.layout.bidding_dialog_layout, null);
                    dialog_builder.setView(dialog_builder_view);
                    dialog_builder.setCancelable(false);

                    final EditText bidding_amount_edittext=dialog_builder_view.findViewById(R.id.bidding_amount_input);

                    dialog_builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                                int newAmount=Integer.parseInt(bidding_amount_edittext.getText().toString());
                                Complaint THIS_COMPLAINT=complaintList.get(getAdapterPosition());
                                DBHelper.updateBid(THIS_COMPLAINT, THIS_USER_OBJECT ,newAmount);
                                complaint_bid.setVisibility(View.GONE);
                                complaint_contractor.setVisibility(View.GONE);
                                con_bid_made.setVisibility(View.VISIBLE);
                        }
                    });

                    dialog_builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
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

            int position=getAdapterPosition();
            Log.e("HELLO", "HELLO");
            //do nothing
        }
    }
}