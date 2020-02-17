package sabouni.margaux.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.Source;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactViewHolder extends RecyclerView.ViewHolder{

    private TextView nomContact;

    private TextView prenomContact;

    private ImageView photoContact;

    private TextView emailContact;

    private TextView telContact;



    public ContactViewHolder(@NonNull View itemView ) {
        super(itemView);

        nomContact = itemView.findViewById(R.id.textViewNomList);
        prenomContact = itemView.findViewById(R.id.textViewPrenom);
        photoContact = itemView.findViewById(R.id.imageViewContact);
        emailContact = itemView.findViewById(R.id.textViewMail);
        telContact = itemView.findViewById(R.id.textViewTel);

    }

    void display(final Contact contact){
        nomContact.setText(contact.getName().getLast());
        prenomContact.setText(contact.getName().getFirst());
        emailContact.setText(contact.getEmail());
        telContact.setText(contact.getPhone());
        Picasso.get().load(contact.getPicture()).into(photoContact);
    }

    public void bind(final Contact contact, final ContactItemClickListener contactItemClickListener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactItemClickListener.onContactClick(contact);
            }
        });
    }

}

