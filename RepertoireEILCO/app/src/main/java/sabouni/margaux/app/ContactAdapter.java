package sabouni.margaux.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable {

    private List<Contact> listContact;

    private  ContactItemClickListener contactItemClickListener;

    private List<Contact> listFilterContact ;


    public ContactAdapter(List<Contact> listContact, ContactItemClickListener contactItemClickListener){
        this.listContact = listContact;
        this.contactItemClickListener = contactItemClickListener;
        this.listFilterContact = new ArrayList<>(listContact);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,
                parent, false);

        return new ContactViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.display(listContact.get(position));
        Contact contact = this.listContact.get(position);
        holder.bind(contact,contactItemClickListener);
    }
    @Override
    public int getItemCount() {
        return listContact.size();
    }


    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private  Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> listeContactFiltre = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                listeContactFiltre.addAll(listFilterContact);
            } else {
                String filterPatern = constraint.toString().toLowerCase().trim();

                for(Contact contact : listFilterContact){
                    if(contact.getName().getLast().toLowerCase().contains(filterPatern) || contact.getName().getFirst().toLowerCase().contains(filterPatern)){
                        listeContactFiltre.add(contact);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listeContactFiltre;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listContact.clear();
            listContact.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
