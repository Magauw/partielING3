package sabouni.margaux.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilActivity extends AppCompatActivity {

    private ImageView imageProfil;

    private TextView nom;

    private TextView prenom;

    private TextView numeroTelephone;

    private TextView adress;

    private TextView linkedin;

    private Button buttonCall;

    private Button buttonMaps;

    private String adresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://android.busin.fr/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ContactClient client = retrofit.create(ContactClient.class);
        Call<List<Contact>> call;
        final String email = getIntent().getStringExtra("email");
        call = client.ListContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>>
                    response) {
                List<Contact> listContact = response.body();
                Contact contact = new Contact();
                for (Contact contacts : listContact){
                    if(contacts.getEmail().contains(email) ){
                        contact = contacts;
                    }
                }

                prenom = findViewById(R.id.textViewPrenom);
                prenom.setText(contact.getName().getFirst());

                nom = findViewById(R.id.textViewNom);
                nom.setText(contact.getName().getLast());

                imageProfil = findViewById(R.id.imageView);
                Picasso.get().load(contact.getPicture()).into(imageProfil);

                numeroTelephone = findViewById(R.id.textViewTel);
                numeroTelephone.setText(contact.getPhone());

                buttonCall = findViewById(R.id.buttonAppel);
                buttonCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String numtel = numeroTelephone.getText().toString().trim();

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numtel, null)));
                    }
                });

                adress = findViewById(R.id.textViewAdd);
                adress.setText(contact.getAddress().getStreet() +" "+ contact.getAddress().getCity()+" " + contact.getAddress().getState());
                adresse = contact.getAddress().getStreet()+"+"+contact.getAddress().getCity()+"+"+contact.getAddress().getState();

                buttonMaps = findViewById(R.id.buttonMaps);
                buttonMaps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri navigationIntentUri = Uri.parse("geo:0,0?q=" + adresse);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });

                linkedin = findViewById(R.id.textViewlinkedin);
                linkedin.setText(contact.getLinkedin());
                Linkify.addLinks(linkedin, Linkify.WEB_URLS);
                linkedin.setMovementMethod(LinkMovementMethod.getInstance());

            }
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, "Erreur connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
