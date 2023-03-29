package com.example.codifyy.notesview.AddingNotesPackage;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.BuildConfig;
import com.example.codifyy.R;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.holder> {
    private URL url = null;
    private String fileName;
    private File fileTest;
    Context context;
    List<AddListOfNotes> list;

    public Adapter2(Context context, List<AddListOfNotes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_view,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.notes.setText(list.get(position).getNotes());

        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context,PdfViewActivity.class);
//                intent.putExtra("newChild",list.get(holder.getAdapterPosition()).getChild());
//                intent.putExtra("child2",list.get(holder.getAdapterPosition()).getNotes());
//                context.startActivity(intent);
                AddListOfNotes add = list.get(holder.getAdapterPosition());
                initViews(add.getUrl(),add.getTitle());
//                fileViewMethod();

                // Testing for sharing....
                File outputFile = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS)+"/"+add.getTitle()+".pdf");
                Uri uri= FileProvider.getUriForFile(Objects.requireNonNull(context),
                        BuildConfig.APPLICATION_ID + ".provider", outputFile);;
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                context.startActivity(share);


            }
        });
//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddListOfNotes add = list.get(holder.getAdapterPosition());
//                initViews(add.getUrl(),add.getTitle());
//                fileViewMethod();
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView notes;
        ImageView pdf;
        public holder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutId);
            notes = itemView.findViewById(R.id.noteName);
            pdf = itemView.findViewById(R.id.pdfNotes);
        }
    }







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
