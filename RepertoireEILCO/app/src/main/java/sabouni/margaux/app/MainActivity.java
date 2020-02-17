package sabouni.margaux.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://android.busin.fr/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ContactClient client = retrofit.create(ContactClient.class);
        Call<List<Contact>> call;
        call = client.ListContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>>
                    response) {
                List<Contact> listContact = response.body();
                myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapter = new ContactAdapter(listContact, new ContactItemClickListener() {
                    @Override
                    public void onContactClick(Contact contact) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, ProfilActivity.class);
                        intent.putExtra("email",contact.getEmail());
                        startActivity(intent);
                    }
                });
                myRecyclerView.setAdapter(adapter);


            }
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur connexion", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aPropos:
                openDialog();
                return true;
            case R.id.action_search :
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openDialog(){
        AProposDialog aProposDialog = new AProposDialog();
        aProposDialog.show(getSupportFragmentManager(), "Auteur");
    }
}
