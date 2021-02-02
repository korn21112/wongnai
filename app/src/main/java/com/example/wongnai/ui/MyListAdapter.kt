package com.example.wongnai.ui

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.loadsvgimage.Utils.fetchSvg
import com.example.wongnai.R
import java.net.URL


class MyListAdapter(private val context: Activity, private val title: List<String>, private val description: List<String>, private val imgsrc: List<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = title[position]
        // image in your imageview
        Log.i("URL",imgsrc[position])
        if(imgsrc[position]!="https://cdn.coinranking.com/B1N19L_dZ/bnb.svg"&&
            imgsrc[position]!="https://cdn.coinranking.com/4bpYKqV4X/AAVE.png"&&
            imgsrc[position]!="https://cdn.coinranking.com/c2WntZSPs/snx-synthetix.png"&&
            imgsrc[position]!="https://cdn.coinranking.com/B1_TDu9Dm/VEN.svg"&&
            imgsrc[position]!="https://cdn.coinranking.com/eKKejWkdo/sushiswap.png"&&
            imgsrc[position]!="https://cdn.coinranking.com/XPU7TeCYD/New-CEL.png"&&
            imgsrc[position]!="https://cdn.coinranking.com/rJrKiS_uZ/zec.svg"&&
            imgsrc[position]!="https://cdn.coinranking.com/S0C6Cw2-w/avax-avalanche.png")
            fetchSvg(context, imgsrc[position], imageView)// this method will load svg
        else
            DownLoadImageTask(imageView).execute(imgsrc[position])
        subtitleText.text = description[position]

        return rowView
    }
}

private class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
            null
        }
    }
    override fun onPostExecute(result: Bitmap?) {
        if(result!=null){
            // Display the downloaded image into image view
            Toast.makeText(imageView.context,"download success",Toast.LENGTH_SHORT).show()
            imageView.setImageBitmap(result)
        }else{
            Toast.makeText(imageView.context,"Error downloading", Toast.LENGTH_SHORT).show()
        }
    }
}

