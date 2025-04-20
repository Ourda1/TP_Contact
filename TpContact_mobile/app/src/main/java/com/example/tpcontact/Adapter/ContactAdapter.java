package com.example.tpcontact.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpcontact.R;
import com.example.tpcontact.bean.Contact;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Filterable {

    private List<Contact> contacts;
    private List<Contact> contactsFiltered; // Liste filtrée
    private Context context;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
        this.contactsFiltered = new ArrayList<>(contacts);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactsFiltered.get(position); // Utiliser la liste filtrée
        holder.nameTextView.setText(contact.getName());
        holder.numberTextView.setText(String.valueOf(contact.getNumber()));

        // Configuration du bouton d'appel
        holder.btnCall.setOnClickListener(v -> {
            String phoneNumber = String.valueOf(contact.getNumber());
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Aucune application d'appel disponible", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuration du bouton de message
        holder.btnMessage.setOnClickListener(v -> {
            String phoneNumber = String.valueOf(contact.getNumber());
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phoneNumber));

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Aucune application de messagerie disponible", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsFiltered.size(); // Utiliser la taille de la liste filtrée
    }

    // Méthode pour mettre à jour les contacts
    public void updateContacts(List<Contact> newContacts) {
        this.contacts = newContacts;
        this.contactsFiltered = new ArrayList<>(contacts);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                // Si la contrainte est vide, retourner la liste complète
                if (constraint == null || constraint.length() == 0) {
                    results.values = contacts;
                    results.count = contacts.size();
                    return results;
                }

                String filterPattern = constraint.toString().toLowerCase().trim();
                List<Contact> filteredList = new ArrayList<>();

                // Filtrer par nom ou numéro de téléphone
                for (Contact contact : contacts) {
                    if (contact.getName().toLowerCase().contains(filterPattern) ||
                            String.valueOf(contact.getNumber()).contains(filterPattern)) {
                        filteredList.add(contact);
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactsFiltered = (List<Contact>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView contactImage;
        public TextView nameTextView;
        public TextView numberTextView;
        public ImageButton btnCall;
        public ImageButton btnMessage;

        public ContactViewHolder(View itemView) {
            super(itemView);
            contactImage = itemView.findViewById(R.id.contactImage);
            nameTextView = itemView.findViewById(R.id.contactName);
            numberTextView = itemView.findViewById(R.id.contactNumber);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnMessage = itemView.findViewById(R.id.btnMessage);
        }
    }
}