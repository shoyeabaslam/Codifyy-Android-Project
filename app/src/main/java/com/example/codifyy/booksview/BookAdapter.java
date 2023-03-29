package com.example.codifyy.booksview;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codifyy.BuildConfig;
import com.example.codifyy.R;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.holder> {
    private URL url = null;
    private String fileName;
    private File fileTest;
    Context context;
    List<AddBooksInfo> list;

    public BookAdapter(Context context, List<AddBooksInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_cover,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        AddBooksInfo add = list.get(position);
        holder.bookTitle.setText(add.getTitle());
        Glide.with(context).load(add.getBook_cover_url()).into(holder.bookcover);


        fileTest = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+
                add.getName()+".pdf");
        if(fileTest.exists()){
            holder.file_check.setVisibility(View.VISIBLE);

        }



        holder.downlodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(add.getPdf_url())));
//                Toast.makeText(context,"Download Pdf...",Toast.LENGTH_LONG).show();
                //first initializing...
                initViews(list.get(holder.getAdapterPosition()).getPdf_url(),list.get(holder.getAdapterPosition()).getName());
                fileViewMethod();
            }
        });
        holder.buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!add.getBuy_url().equals("")) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(add.getBuy_url())));
                    Toast.makeText(context, "Buy...", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "No link Available", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView bookcover,file_check;
        TextView bookTitle;
        CardView downlodbtn,buybtn;
        public holder(@NonNull View itemView) {
            super(itemView);
            bookcover = itemView.findViewById(R.id.bookcover);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            downlodbtn = itemView.findViewById(R.id.downloadbutton);
            buybtn = itemView.findViewById(R.id.buybutton);
            file_check = itemView.findViewById(R.id.checkFileExistance);
        }
    }

    //.............................initializing the pdf
    private void initViews(String filepath,String name) {
        try {
            url = new URL(filepath);
//            fileName = url.getPath();
//            fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
            fileName = name+".pdf";
        } catch (MalformedURLException e) {
            Toast.makeText(context, "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //-----------calling the FileViewingMethod-----------------------------
    private void fileViewMethod() {
        fileTest = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+fileName);
        if(fileTest.exists()){
            File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+fileName);
            Uri uri= FileProvider.getUriForFile(Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID + ".provider", file);
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(uri,"application/pdf");
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(i);
        }
        else if(!fileTest.exists()){
            if(!isNetworkConnected()){
                Toast.makeText(context, "can't Download No Internet Connection", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Downloading...", Toast.LENGTH_LONG).show();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url + ""));
                request.setTitle(fileName);
                request.setMimeType("application/pdf");
                request.allowScanningByMediaScanner();
                request.setAllowedOverMetered(true);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                DownloadManager dm = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                }
                dm.enqueue(request);
            }
        }
        else{
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }
}
