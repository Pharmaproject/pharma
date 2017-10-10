package in.optho.opthoremedies.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import in.optho.opthoremedies.Bitmapview;
import in.optho.opthoremedies.Database.ProductDatabaseHelper;
import in.optho.opthoremedies.Models.Product;
import in.optho.opthoremedies.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Design1 extends Fragment {

    private ImageView opl1;
    private ImageView brand1;
    private ImageView Science1;
    private ImageView cartoon1;
    private ImageView indication1;
    private ImageView cpl1;
    ProductDatabaseHelper db;

    private byte[] brand;
    private byte[] openpunch;
    private byte[] graphic;
    private byte[] carton;
    private byte[] indication;
    private byte[] description;
    private byte[] closepunch;
    private byte[] customicon;
    Bitmapview bit;

    Product product;
    public Design1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new ProductDatabaseHelper(getActivity());
        bit = new Bitmapview();
        Bundle bundle = getArguments();
        product = bundle.getParcelable("PRODUCT");
        int id = product.getId();
        brand =db.getBrand(id);
        openpunch = db.getOpenpunch(id);
        graphic = db.getGraphic(id);
        carton = db.getCarton(id);
        indication = db.getIndication(id);
        description= db.getDesc(id);
        closepunch= db.getClosepunch(id);
        customicon  = db.getCustomicon(id);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_design1, container, false);
        Context c= getActivity().getApplicationContext();


        opl1 = (ImageView) view.findViewById(R.id.opl1);
        brand1 = (ImageView) view.findViewById(R.id.brand1);
        Science1 = (ImageView) view.findViewById(R.id.Science1);
        cartoon1 = (ImageView) view.findViewById(R.id.cartoon1);
        indication1 = (ImageView) view.findViewById(R.id.indication1);
        cpl1 = (ImageView) view.findViewById(R.id.cpl1);


        RequestOptions ro = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_backspace)
                //           .override(256, 140)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()
                .fitCenter();

        Glide.with(this)
                .load(brand)
                .apply(ro)

                .into(brand1);

     /*   bit.Bitmapview(brand1,brand,c);
        bit.Bitmapview(opl1,brand,c);
        bit.Bitmapview(Science1,brand,c);
        bit.Bitmapview(cartoon1,brand,c);
        bit.Bitmapview(indication1,brand,c);
        bit.Bitmapview(cpl1,brand,c);
        bit.Bitmapview(brand1,brand,c);

*/


        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(product.getName());


    }

}
