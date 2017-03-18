package com.example.ravin.images_sec1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "hi";
    GridView gridView;
    Adapter adapter;
   public static ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridView=(GridView)findViewById(R.id.grid1);
        arrayList=new ArrayList();
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.ravin.images_sec1", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }

        AsyncTaskLoader asyncTaskLoader=new AsyncTaskLoader(MainActivity.this, new OnAsyncResult() {
            @Override
            public void onAsyncResult(ArrayList<String> result) {
                arrayList=result;
                Toast.makeText(MainActivity.this, "jlakdsize="+result.size(), Toast.LENGTH_SHORT).show();
                ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,arrayList);

                gridView.setAdapter(imageAdapter);
            }
        });
        asyncTaskLoader.execute();

      //  arrayList=imagelist.list();

      //  arrayList=getFilePaths();


    /*    for(int i=1;i<=arrayList.size();i++)
        {
            Log.e("item=",arrayList.get(i).toString());
        }
        */
       /* ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        gridView.setAdapter(arrayAdapter);*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getSelectedItem();

             // int ss= position
                Intent intent=new Intent(MainActivity.this,ImageDis.class);
                intent.putExtra("path",position);
                startActivity(intent);

            }
        });
    }


    public ArrayList<String> getFilePaths()
    {

        Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Images.ImageColumns.DATA};
            Cursor c = null;
            SortedSet<String> dirList = new TreeSet<String>();
            ArrayList<String> resultIAV = new ArrayList<String>();

            String[] directories = null;
            if (u != null) {
                c = managedQuery(u, projection, null, null, null);

            }

            if ((c != null) && (c.moveToFirst())) {
                do {
                    String tempDir = c.getString(0);
                    tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                    try {
                        dirList.add(tempDir);
                    } catch (Exception e) {

                    }
                }
                while (c.moveToNext());
                directories = new String[dirList.size()];
                dirList.toArray(directories);

            }

            for (int i = 0; i < dirList.size(); i++) {
                File imageDir = new File(directories[i]);
                File[] imageList = imageDir.listFiles();
                if (imageList == null)
                    continue;
                for (File imagePath : imageList) {
                    try {

                        if (imagePath.isDirectory()) {
                            imageList = imagePath.listFiles();

                        }
                        if (imagePath.getName().contains(".jpg") || imagePath.getName().contains(".JPG")
                                || imagePath.getName().contains(".jpeg") || imagePath.getName().contains(".JPEG")
                                || imagePath.getName().contains(".png") || imagePath.getName().contains(".PNG")
                                || imagePath.getName().contains(".gif") || imagePath.getName().contains(".GIF")
                                || imagePath.getName().contains(".bmp") || imagePath.getName().contains(".BMP")
                                ) {


                            String path = imagePath.getAbsolutePath();
                            resultIAV.add(path);

                        }
                    }
                    //  }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }



            return resultIAV;

    }

    public class AsyncTaskLoader extends AsyncTask<Void, Void,ArrayList<String>> {

        private Context context;
        private OnAsyncResult listener;
        private ProgressDialog progressDialog;
        private ArrayList<String> arrayList;

        public AsyncTaskLoader(Context context, OnAsyncResult listener) {
            this.context = context;
            this.listener = listener;
         //   this.arrayList = arrayList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please Wait . . .");
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);

            progressDialog.show();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {


            Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Images.ImageColumns.DATA};
            Cursor c = null;
            SortedSet<String> dirList = new TreeSet<String>();
            ArrayList<String> resultIAV = new ArrayList<String>();

            String[] directories = null;
            if (u != null) {
                c =managedQuery(u, projection, null, null, null);

            }

            if ((c != null) && (c.moveToFirst())) {
                do {
                    String tempDir = c.getString(0);
                    tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                    try {
                        dirList.add(tempDir);
                    } catch (Exception e) {

                    }
                }
                while (c.moveToNext());
                directories = new String[dirList.size()];
                dirList.toArray(directories);

            }

            for (int i = 0; i < dirList.size(); i++) {
                File imageDir = new File(directories[i]);
                File[] imageList = imageDir.listFiles();
                if (imageList == null)
                    continue;
                for (File imagePath : imageList) {
                    try {

                        if (imagePath.isDirectory()) {
                            imageList = imagePath.listFiles();

                        }
                        if (imagePath.getName().contains(".jpg") || imagePath.getName().contains(".JPG")
                                || imagePath.getName().contains(".jpeg") || imagePath.getName().contains(".JPEG")
                                || imagePath.getName().contains(".png") || imagePath.getName().contains(".PNG")
                                || imagePath.getName().contains(".gif") || imagePath.getName().contains(".GIF")
                                || imagePath.getName().contains(".bmp") || imagePath.getName().contains(".BMP")
                                ) {

                         //   if(i%2==0){

                          //  }else {
                            String path = imagePath.getAbsolutePath();
                            resultIAV.add(path);}

                        //}
                    }
                    //  }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }




            return resultIAV;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            try {

                progressDialog.setProgress(100);
                progressDialog.dismiss();

                if(!result.equals("error") && !result.contains("errors"))
                {
                    if(listener != null){
                        listener.onAsyncResult(result);
                    }
                }
                else {
                    if(listener != null){
                        // listener.onAsyncResult("Error");
                    }
                }

            }catch (Exception e)
            {
                Toast.makeText(context, "Slow Connection", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
