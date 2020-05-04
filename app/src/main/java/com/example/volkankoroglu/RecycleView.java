package com.example.volkankoroglu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RecycleView extends AppCompatActivity {
    private Button btn;
    private RecyclerView rv;
    private AppCompatEditText veri;
    private TextView bas, uTv;
    private RecyclerView.Adapter adapRv;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        calistir();
    }

    private void calistir() {
        veri = findViewById(R.id.ilkEt);
        bas = findViewById(R.id.baslikTv);
        uTv = findViewById(R.id.urlTv);
        btn = findViewById(R.id.btn);
        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        List<String> dizi = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            dizi.add("Veri " + i);
        }
        adapRv = new adaptor(dizi);
        rv.setAdapter(adapRv);
        basınca();
    }
    private void basınca() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GithubSearchQuery();
            }
        });
    }
    public class adaptor extends RecyclerView.Adapter<adaptor.ViewHolder> {
        private List<String> values;

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView txtBas;
            TextView txtAcik;
            View layout;
            ViewHolder(View v) {
                super(v);
                layout = v;
                img = v.findViewById(R.id.resim);
                txtBas = v.findViewById(R.id.baslik);
                txtAcik = v.findViewById(R.id.aciklama);
            }
        }
        adaptor(List<String> myDataset) {
            values = myDataset;
        }
        @Override
        public adaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.rvsatir_layout, parent, false);
            ViewHolder tutucu = new ViewHolder(v);
            return tutucu;
        }
        @Override
        public void onBindViewHolder(ViewHolder connect, final int position) {
            final String name = values.get(position);
            connect.txtBas.setText(name);

            final String description = "Açıklama: " + name;

            connect.txtAcik.setText(description);

            connect.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            connect.txtBas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDetail = new Intent(getApplicationContext(), DetaySayfasi.class);
                    toDetail.putExtra("HEADER", name);
                    toDetail.putExtra("DESCRIPTION", description);
                    startActivity(toDetail);
                }
            });
        }

        @Override
        public int getItemCount() {
            return values.size();
        }

    }

    @SuppressLint("SetTextI18n")
    private void GithubSearchQuery() {
        String githubQuery = veri.getText().toString();
        URL githubSearchUrl = VeriClass.buildUrl(githubQuery);
        uTv.setText("URL: " + githubSearchUrl.toString());

        String githubSearchResult = null;

        try {
            githubSearchResult = VeriClass.getResponseFromHttpUrl(githubSearchUrl);
            bas.setText(githubSearchResult);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Hata! \n\n" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
