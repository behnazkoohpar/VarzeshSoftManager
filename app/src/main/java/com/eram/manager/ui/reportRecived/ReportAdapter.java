package com.eram.manager.ui.reportRecived;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.eram.manager.R;
import com.eram.manager.data.model.api.DebtorAmount;
import com.eram.manager.utils.CommonUtils;
import com.eram.manager.utils.NumberFormatter;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    public List<DebtorAmount.Result> stList;
    public static Context context;
    private ReportAdapter.OnItemClickListener mListener;

    public ReportAdapter(List<DebtorAmount.Result> SlistS) {
        stList = SlistS;
    }

    public interface OnItemClickListener {
        void onOpenClick(int position);

        void onCallTelClick(int position);

        void onCallTelHomeClick(int position);
    }

    public void setOnitemclickListener(ReportAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView title, number, price;
        public CardView card_view;
        public Button select;

        public ViewHolder(final View itemView, final ReportAdapter.OnItemClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            number = (TextView) itemView.findViewById(R.id.number);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }

    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        ReportAdapter.ViewHolder viewHolder = new ReportAdapter.ViewHolder(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportAdapter.ViewHolder viewHolder, final int position) {
        try {
            viewHolder.title.setText(stList.get(position).getOperationTitle());
            viewHolder.number.setText(stList.get(position).getOperationCount());
            viewHolder.price.setText(NumberFormatter.format(Long.parseLong(stList.get(position).getTotalAmount())));

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.data_receive_error), null, null);
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
