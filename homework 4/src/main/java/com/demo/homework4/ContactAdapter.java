package com.demo.homework4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<DataContact> dataContacts;
    private OnContactClickListener onContactClickListener;

    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        this.onContactClickListener = onContactClickListener;
    }

    public ContactAdapter(ArrayList<DataContact> dataContacts) {
        this.dataContacts = dataContacts;
    }

    interface OnContactClickListener {
        void onContactClick (int position);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(dataContacts.get(position));

    }

    @Override
    public int getItemCount() {
        return dataContacts != null ? dataContacts.size() : 0;
    }

     class ContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView textName;
        private TextView editPhoneAddress;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            textName = itemView.findViewById(R.id.textName);
            editPhoneAddress = itemView.findViewById(R.id.editPhoneAddress);
        }

        void bind(DataContact dataContact) {

            textName.setText(dataContact.getName());
            editPhoneAddress.setText(dataContact.getContact());
            switch (dataContact.getInfoType()){
                case Phone:
                    avatar.setImageResource(R.drawable.ic_baseline_contact_phone_24);
                    break;
                case Mail:
                    avatar.setImageResource(R.drawable.ic_baseline_contact_mail_24);
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(onContactClickListener != null){
                        onContactClickListener.onContactClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}


