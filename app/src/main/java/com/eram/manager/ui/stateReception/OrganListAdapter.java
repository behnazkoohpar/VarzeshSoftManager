package com.eram.manager.ui.stateReception;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.eram.manager.R;
import com.eram.manager.data.model.api.OrganizationUnit;

import java.util.List;

public class OrganListAdapter extends RecyclerView.Adapter<OrganListAdapter.ViewHolder> {
    private List<OrganizationUnit.Result> stList;
    public static Context context;
    private ProgressDialog prgDialog;
    private OnItemClickListener listener;

    public OrganListAdapter(List<OrganizationUnit.Result> SlistS) {
        this.stList = SlistS;
    }

    public interface OnItemClickListener {
        void onClick(int position, String title, String id);
    }

    public void setOnitemclickListener(OnItemClickListener onitemclickListener) {
        listener = onitemclickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.organName);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }


    public OrganListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organ, (ViewGroup) null);
        OrganListAdapter.ViewHolder viewHolder = new OrganListAdapter.ViewHolder(itemLayoutView);
        prgDialog = new ProgressDialog(context);
        prgDialog.setMessage("لطفا منتظر بمانید ...");
        prgDialog.setCancelable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final OrganListAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.title.setText(this.stList.get(position).getName());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        String value = viewHolder.title.getText().toString();
                        listener.onClick(position, value, stList.get(position).getID());
                    }
                }
            }
        });

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }

}
